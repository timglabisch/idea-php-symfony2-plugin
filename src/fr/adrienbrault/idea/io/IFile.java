package fr.adrienbrault.idea.io;

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

}
