package fr.adrienbrault.idea.io.File;

import com.intellij.openapi.diagnostic.Logger;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;

import java.io.IOException;
import java.io.InputStream;

public class Decorated implements IFile {

    protected IFile file;

    @Override
    public String getContents() throws IOException {
        return this.file.getContents();
    }

    @Override
    public String getPath() {
        return this.file.getPath();
    }

    @Override
    public boolean exists() {
        return this.file.exists();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.file.getInputStream();
    }

    @Override
    public IFile getParentFile() {
        return this.file.getParentFile();
    }

    @Override
    public boolean isDirectory() {
        return this.file.isDirectory();
    }

    @Override
    public Long lastModified() {
        return this.file.lastModified();
    }

    @Override
    public String getAbsolutePath() {
        return this.file.getAbsolutePath();
    }
}
