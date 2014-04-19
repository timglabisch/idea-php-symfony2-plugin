package fr.adrienbrault.idea.io.File;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.php.lang.psi.PhpPsiElementFactory;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.util.PsiElementUtils;

import java.io.*;
import java.nio.charset.Charset;

public abstract class Abstract implements IFile {

    @Override
    public PsiFile toPsiFile(Project project) {

        /**
         * Todo:Check the file type to use the PsiElementFactory for the right Filetype
         */

        try {
            return PhpPsiElementFactory.createPsiFileFromText(project, this.getContents());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(this.getContents().getBytes(Charset.forName("UTF-8")));
        return is;
    }
}
