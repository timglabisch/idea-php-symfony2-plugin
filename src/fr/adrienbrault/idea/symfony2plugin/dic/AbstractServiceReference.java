package fr.adrienbrault.idea.symfony2plugin.dic;


import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import fr.adrienbrault.idea.symfony2plugin.stubs.ContainerCollectionResolver;
import fr.adrienbrault.idea.symfony2plugin.util.PhpElementsUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractServiceReference extends PsiPolyVariantReferenceBase<PsiElement> {

    protected String serviceId;
    protected boolean useIndexedServices = false;
    protected boolean usePrivateServices = true;

    public AbstractServiceReference(PsiElement psiElement) {
        super(psiElement);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<ResolveResult> resolveResults = new ArrayList<ResolveResult>();

        ContainerCollectionResolver.ServiceCollector collector = new ContainerCollectionResolver.ServiceCollector(getElement().getProject());
        collector.addCollectorSource(ContainerCollectionResolver.Source.COMPILER);

        if(this.useIndexedServices) {
            collector.addCollectorSource(ContainerCollectionResolver.Source.INDEX);
        }

        // Return the PsiElement for the class corresponding to the serviceId
        String serviceClass = collector.resolve(serviceId);
        if (serviceClass == null) {
            return resolveResults.toArray(new ResolveResult[resolveResults.size()]);
        }

        resolveResults.addAll(PhpElementsUtil.getClassInterfaceResolveResult(getElement().getProject(), serviceClass));
        return resolveResults.toArray(new ResolveResult[resolveResults.size()]);
    }

    @NotNull
    @Override
    public Object[] getVariants() {

        List<LookupElement> results = new ArrayList<LookupElement>();

        ContainerCollectionResolver.ServiceCollector collector = new ContainerCollectionResolver.ServiceCollector(getElement().getProject());
        collector.addCollectorSource(ContainerCollectionResolver.Source.COMPILER);

        if(this.useIndexedServices) {
            collector.addCollectorSource(ContainerCollectionResolver.Source.INDEX);
        }

        for(ContainerService containerService: collector.collect()) {

            // dont attach private services; if configured
            if(containerService.isPrivate() && !usePrivateServices) {
                continue;
            }

            results.add(new ServiceStringLookupElement(containerService));
        }

        return results.toArray();
    }
}
