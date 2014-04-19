package fr.adrienbrault.idea.io.File;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.util.PsiElementUtils;

import java.io.*;
import java.nio.charset.Charset;

public abstract class Abstract implements IFile {

    @Override
    public VirtualFile toVirtualFile(Project project) {
        try {
            File f = FileUtil.createTempFile(this.getPath(), ".php");
            FileUtil.copy(this.getInputStream(), new FileOutputStream(f));
            return VfsUtil.findFileByIoFile(f, false);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public PsiFile toPsiFile(Project project) {
        return PsiElementUtils.virtualFileToPsiFile(project, this.toVirtualFile(project));
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(this.getContents().getBytes(Charset.forName("UTF-8")));
        return is;
    }
}
