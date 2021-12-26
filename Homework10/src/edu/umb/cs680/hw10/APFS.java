package edu.umb.cs680.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem{
    private static APFS fs = null;
    private APFS() {};
    public static APFS getInstance() {
        if (fs == null) {
            fs = new APFS();
        }
        return fs;
    }

    @Override
    protected FSElement createDedaultRoot() {
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 0, 0);
        return new ApfsDirectory(null, "Root", 0, time, "System", time);
    }

    public ApfsDirectory getRootDir() {
        return (ApfsDirectory)this.rootDirs.getFirst();
    }
}
