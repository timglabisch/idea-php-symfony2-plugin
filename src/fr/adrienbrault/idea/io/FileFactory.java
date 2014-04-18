package fr.adrienbrault.idea.io;

import com.intellij.openapi.project.Project;
import fr.adrienbrault.idea.io.File.Chained;
import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.io.File.Logable;
import fr.adrienbrault.idea.io.File.Restful;

import java.io.File;

public class FileFactory {

    public static IFile create(File file) {
        return new Chained(new IFile[]{
                new Logable(new Filesystem(file))
        });
    }

    public static IFile create(String path, Project project) {
        return new Chained(new IFile[]{
            new Logable(new Restful(path)),
            new Logable(new Filesystem(FileUtil.normalizeFilesystemPath(path, project)))
        });
    }

    public static IFile create(String path) {
        return new Chained(new IFile[]{
            new Logable(new Restful(path)),
            new Logable(new Filesystem(path))
        });
    }
}
