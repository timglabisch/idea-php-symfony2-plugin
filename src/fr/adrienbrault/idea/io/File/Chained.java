package fr.adrienbrault.idea.io.File;

import com.intellij.openapi.diagnostic.Logger;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.Symfony2ProjectComponent;

import java.io.IOException;
import java.io.InputStream;

public class Chained extends Decorated {

    public Chained(IFile[] files) {
        for(IFile file : files) {
            if(file.exists()) {
                this.file = file;
                break;
            }
        }

        if(this.file == null) {
            this.file = new Void();
        }
    }


}
