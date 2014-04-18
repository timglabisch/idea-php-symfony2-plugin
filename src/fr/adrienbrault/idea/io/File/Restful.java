package fr.adrienbrault.idea.io.File;

import fr.adrienbrault.idea.io.FileFactory;
import fr.adrienbrault.idea.io.IFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Restful implements IFile {

    protected String path;

    public Restful(String path) {
        this.path = path;
    }

    private String fetchUrl(String path) throws IOException {

        path = "http://symfony.l/app_dev.php/phpstormplugin/resource/" + path;

        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if(connection.getResponseCode() != 200) {
            return null;
        }

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\n');
        }
        rd.close();
        return response.toString().trim();
    }

    @Override
    public String getContents() throws IOException {
        return fetchUrl(this.getPath());
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public boolean exists() {
        try {
            return this.fetchUrl(this.getPath()) != null;
        } catch(IOException e) {
            return false;
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(this.getContents().getBytes(Charset.forName("UTF-8")));
        return is;
    }

    @Override
    public IFile getParentFile() {
        Path p = Paths.get(this.getPath());
        return FileFactory.create(p.getParent().toString());
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public Long lastModified() {
        return 0l;
    }

    @Override
    public String getAbsolutePath() {
        return this.getPath();
    }
}
