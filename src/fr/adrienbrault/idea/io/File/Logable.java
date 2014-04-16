package fr.adrienbrault.idea.io.File;

import com.intellij.openapi.diagnostic.Logger;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;

import java.io.IOException;
import java.io.InputStream;

public class Logable extends Decorated {

    public Logable(IFile file) {
        this.file = file;
        this.getLogger().info("File " + file.getPath() + " is loaded.");
    }

    private Logger getLogger() {
        return Symfony2ProjectComponent.getLogger();
    }

    @Override
    public String getContents() throws IOException {
        try {
            return this.file.getContents();
        } catch (IOException e) {
            this.getLogger().error("IOExcepion when trying to load file "+ this.file.getPath());
            throw e;
        }
    }

    @Override
    public boolean exists() {

        if(this.file.exists()) {
           return true;
        }

        this.getLogger().error("File "+ this.file.getPath() + " doesn't exists.");
        return false;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        try {
            return this.file.getInputStream();
        } catch (IOException e) {
            this.getLogger().error("IOExcepion when trying to get InputStream of file "+ this.file.getPath());
            throw e;
        }

    }

}
