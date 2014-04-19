package fr.adrienbrault.idea.io;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;

import java.io.IOException;
import java.io.InputStream;

public interface IFile {

    String getContents() throws IOException;
    String getPath();

    boolean exists();
    public InputStream getInputStream() throws IOException;

    public IFile getParentFile();
    public boolean isDirectory();

    public Long lastModified();

    public String getAbsolutePath();

    public PsiFile toPsiFile(Project p);

}
