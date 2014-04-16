package fr.adrienbrault.idea.io;

import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.io.File.Logable;

import java.io.File;

public class FileFactory {

    public static IFile create(File file) {
        return new Logable(new Filesystem(file));
    }

    public static IFile create(String path) {
        return new Logable(new Filesystem(path));
    }
}
