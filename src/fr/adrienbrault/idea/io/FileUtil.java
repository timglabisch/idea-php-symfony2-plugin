package fr.adrienbrault.idea.io;


import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static boolean isAncestor(IFile a, IFile b) {
        return a.getAbsolutePath().startsWith(b.getAbsolutePath());
    }
}
