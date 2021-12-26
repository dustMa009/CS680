package edu.umb.cs680.hw10.apfs.util;

import edu.umb.cs680.hw10.ApfsDirectory;
import edu.umb.cs680.hw10.ApfsFile;
import edu.umb.cs680.hw10.ApfsLink;
import edu.umb.cs680.hw10.ApfsVisitor;

import java.util.LinkedList;

public class ApfsFileCrawlingVisitor implements ApfsVisitor {
    private LinkedList<ApfsFile> files;

    public ApfsFileCrawlingVisitor() {
        files = new LinkedList<>();
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
        files.add(file);
    }

    public LinkedList<ApfsFile> getFiles() {
        return this.files;
    }
}
