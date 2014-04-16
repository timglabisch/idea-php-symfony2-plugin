package fr.adrienbrault.idea.symfony2plugin.profiler;

import com.intellij.openapi.vfs.VfsUtil;
import fr.adrienbrault.idea.io.FileFactory;
import fr.adrienbrault.idea.io.IFile;
import fr.adrienbrault.idea.symfony2plugin.profiler.dict.ProfilerRequest;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ProfilerIndex {

    private IFile file;

    public ProfilerIndex(IFile file) {
        this.file = file;
    }

    public ArrayList<ProfilerRequest> getRequests() {
        ArrayList<ProfilerRequest> list = new ArrayList<ProfilerRequest>();

        String trennzeichen = ",";

        try {
            for(String readString : this.file.getContents().split(System.getProperty("line.separator"))) {
                list.add(new ProfilerRequest(readString.split(trennzeichen), this));
            }

        } catch (IOException e) {

        }


        return list;
    }

    public String getPath(ProfilerRequest profilerRequest) {
        String[] hash = profilerRequest.getHash().split("(?<=\\G.{2})");

        return hash[2] + "/" + hash[1] + "/" + profilerRequest.getHash();

    }

    @Nullable
    public IFile getFile(ProfilerRequest profilerRequest) {
        String path = this.getPath(profilerRequest);

        IFile file = FileFactory.create(this.file.getParentFile().getAbsolutePath() + "/" + path);

        if(!file.exists()) {
            return null;
        }

        return file;
    }

    @Nullable
    public ProfilerRequest getRequestOnHash(String hash) {
        for(ProfilerRequest profilerRequest :this.getRequests()) {
            if(profilerRequest.getHash().equals(hash)) {
                return profilerRequest;
            }
        }

        return null;
    }

    @Nullable
    public String getContent(ProfilerRequest profilerRequest) {
        IFile file = this.getFile(profilerRequest);
        if(file == null) {
            return  null;
        }

        String content = "";

        try {
           content = file.getContents();
        } catch (IOException e) {
        }

        return content;
    }

}
