package fr.adrienbrault.idea.io.File;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;

import java.io.IOException;
import java.io.InputStream;

public class Void implements IFile {

    @Override
    public String getContents() throws IOException {
        throw new IOException();
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        throw new IOException();
    }

    @Override
    public IFile getParentFile() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public Long lastModified() {
        return null;
    }

    @Override
    public String getAbsolutePath() {
        return null;
    }

    @Override
    public VirtualFile toVirtualFile(Project project) {
        return null;
    }

    @Override
    public PsiFile toPsiFile(Project project) {
        return null;
    }

}
