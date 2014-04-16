package fr.adrienbrault.idea.io;


import com.intellij.openapi.project.Project;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static boolean isAncestor(IFile a, IFile b) {
        return a.getAbsolutePath().startsWith(b.getAbsolutePath());
    }

    public static String normalizeFilesystemPath(String path, Project p) {
        if(path.startsWith("/"))
            return path;

        return p.getBasePath() + "/" + path;
    }
}
