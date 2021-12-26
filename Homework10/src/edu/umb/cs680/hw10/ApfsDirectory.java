package edu.umb.cs680.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ApfsDirectory extends ApfsElement {
    private LinkedList<ApfsElement> children;

    public ApfsDirectory(ApfsElement parent, String name, int size, LocalDateTime creationTime, String
            owner, LocalDateTime lastModifiedTime) {
        super(parent, name, size, creationTime, owner, lastModifiedTime);
        children = new LinkedList<ApfsElement>();
    }

    @Override
    boolean isDirectory() {
        return true;
    }

    public LinkedList<ApfsElement> getChildren() {
        return children;
    }

    public void appendChild(ApfsElement child) {
        this.children.add(child);
    }

    public void removeChild(ApfsElement child) {
        this.children.remove(child);
    }

    public int countChildren() { return children.size(); }

    public LinkedList<ApfsDirectory> getSubDirectories() {
        LinkedList<ApfsDirectory> subDirectories = new LinkedList<ApfsDirectory>();
        for (FSElement child : this.children) {
            if (child.isDirectory()) {
                subDirectories.add((ApfsDirectory) child);
            }
        }
        return subDirectories;
    }

    public LinkedList<ApfsFile> getFiles() {
        LinkedList<ApfsFile> files = new LinkedList<ApfsFile>();
        for (FSElement child : this.children) {
            if (child instanceof ApfsFile) {
                files.add((ApfsFile) child);
            }
        }
        return files;
    }

    @Override
    public int getSize() {
        int total_size = 0;
        for (ApfsElement child : children) {
            total_size += child.getSize();
        }
        this.size = total_size;
        return this.size;
    }

    @Override
    public void accept(ApfsVisitor visitor) {
        visitor.visit(this);
        for (ApfsElement e : children) {
            e.accept(visitor);
        }
    }
}
