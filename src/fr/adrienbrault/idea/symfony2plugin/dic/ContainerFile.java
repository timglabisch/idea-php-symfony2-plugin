package fr.adrienbrault.idea.symfony2plugin.dic;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Tag;
import fr.adrienbrault.idea.io.FileFactory;
import fr.adrienbrault.idea.io.IFile;
import org.jetbrains.annotations.Nullable;

import java.io.File;

@Tag("container_file")
public class ContainerFile {

    private String path;

    public ContainerFile() {
    }

    public ContainerFile(String path) {
        this.path = path;
    }

    @Attribute("path")
    public String getPath() {
        return path;
    }

    public boolean exists(Project project) {
        if (!FileUtil.isAbsolute(this.path)) {
            return VfsUtil.findRelativeFile(this.path, project.getBaseDir()) != null;
        }

        File file = new File(this.path);
        return file.exists();
    }

    @Nullable
    public IFile getFile(Project project) {
        return FileFactory.create(this.path, project);
    }

    public void setPath(String path) {
        this.path = path;
    }

}
