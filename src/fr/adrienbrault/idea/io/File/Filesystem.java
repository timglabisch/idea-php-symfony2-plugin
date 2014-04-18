package fr.adrienbrault.idea.io.File;

import fr.adrienbrault.idea.io.FileFactory;
import fr.adrienbrault.idea.io.IFile;

import java.io.*;
import java.nio.charset.Charset;

public class Filesystem implements IFile {

    protected String path;

    public Filesystem(String path) {
        this.path = path.replace("file://", "");
    }

    public Filesystem(File file) {
        this.path = file.getPath();
    }

    @Override
    public String getContents() throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(new File(this.path)));
        String str;
        while ((str = in.readLine()) != null) {
            content.append(str).append(System.getProperty("line.separator"));
        }
        in.close();

        return content.toString();
    }

    @Override
    public boolean exists() {
        File filehandle = new File(this.path);
        return filehandle.exists() && filehandle.canRead();
    }

    public String getPath() {
        return this.path;
    }

    public InputStream getInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(this.getContents().getBytes(Charset.forName("UTF-8")));
        return is;
    }

    public IFile getParentFile() {
        File parentFile = (new File(this.path)).getParentFile();
        return FileFactory.create(parentFile.getPath());
    }

    public boolean isDirectory() {
        return (new File(this.path)).isDirectory();
    }

    public Long lastModified() {
        return (new File(this.path)).lastModified();
    }

    public String getAbsolutePath() {
        return (new File(this.path)).getAbsolutePath();
    }
}
