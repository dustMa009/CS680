package edu.umb.cs680.hw10.apfs.util;

import edu.umb.cs680.hw10.ApfsDirectory;
import edu.umb.cs680.hw10.ApfsFile;
import edu.umb.cs680.hw10.ApfsLink;
import edu.umb.cs680.hw10.ApfsVisitor;

import java.util.LinkedList;

public class ApfsFileSearchVisitor implements ApfsVisitor {
    private String fileName;
    private LinkedList<ApfsFile> foundFiles;

    public ApfsFileSearchVisitor(String name) {
        this.fileName = name;
        this.foundFiles = new LinkedList<>();
    }

    @Override
    public void visit(ApfsLink link) {
        return;
    }

    @Override
    public void visit(ApfsDirectory directory) {
        return;
    }

    @Override
    public void visit(ApfsFile file) {
        if (file.getName().equals(fileName)) {
            foundFiles.add(file);
        }
    }

    public LinkedList<ApfsFile> getFoundFiles() {
        return foundFiles;
    }
}
