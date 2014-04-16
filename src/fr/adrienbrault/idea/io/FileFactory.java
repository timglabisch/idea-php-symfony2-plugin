package fr.adrienbrault.idea.io;

import com.intellij.openapi.project.Project;
import fr.adrienbrault.idea.io.File.Filesystem;
import fr.adrienbrault.idea.io.File.Logable;

import java.io.File;

public class FileFactory {

    public static IFile create(File file) {
        return new Logable(new Filesystem(file));
    }

    public static IFile create(String path, Project project) {
        return new Logable(new Filesystem(FileUtil.normalizeFilesystemPath(path, project)));
    }

    public static IFile create(String path) {
        return new Logable(new Filesystem(path));
    }
}
