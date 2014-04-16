package fr.adrienbrault.idea.symfony2plugin.profiler;

import com.intellij.openapi.project.Project;
import fr.adrienbrault.idea.io.FileFactory;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;
import org.jetbrains.annotations.Nullable;

public class ProfilerUtil {

    @Nullable
    public static IFile findProfilerCsv(Project project) {

        Symfony2ProjectComponent symfony2ProjectComponent = project.getComponent(Symfony2ProjectComponent.class);
        for(IFile file: symfony2ProjectComponent.getContainerFiles()) {
            if(file.exists()) {
                IFile translationRootPath = FileFactory.create(file.getParentFile().getPath() + "/profiler/index.csv", project);
                if (translationRootPath.exists()) {
                    return translationRootPath;
                }
            }
        }

        return null;
    }

}
