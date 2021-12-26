package edu.umb.cs680.hw10.apfs.util;

import edu.umb.cs680.hw10.ApfsDirectory;
import edu.umb.cs680.hw10.ApfsFile;
import edu.umb.cs680.hw10.ApfsLink;
import edu.umb.cs680.hw10.ApfsVisitor;

public class ApfsCountingVisitor implements ApfsVisitor {
    private int dirNum, fileNum, linkNum;

    public ApfsCountingVisitor() {
        dirNum = 0;
        fileNum = 0;
        linkNum = 0;
    }

    @Override
    public void visit(ApfsLink link) {
        linkNum++;
    }

    @Override
    public void visit(ApfsDirectory directory) {
        dirNum++;
    }

    @Override
    public void visit(ApfsFile file) {
        fileNum++;
    }

    public int getDirNum() {
        return dirNum;
    }

    public int getFileNum() {
        return fileNum;
    }

    public int getLinkNum() {
        return linkNum;
    }
}
