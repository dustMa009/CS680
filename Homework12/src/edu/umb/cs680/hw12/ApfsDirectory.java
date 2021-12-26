package edu.umb.cs680.hw12;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

    public LinkedList<ApfsElement> getChildren(Comparator<ApfsElement> comp) {
        Collections.sort(children, comp);
        return children;
    }

    public LinkedList<ApfsElement> getChildren() {
        Collections.sort(children, (ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()));
        return children;
    }

    public void appendChild(ApfsElement child) {
        this.children.add(child);
        Collections.sort(children, (ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()));
    }

    public void removeChild(ApfsElement child) {
        this.children.remove(child);
    }

    public int countChildren() { return children.size(); }

    public LinkedList<ApfsDirectory> getSubDirectories(Comparator<ApfsElement> comp) {
        LinkedList<ApfsDirectory> subDirectories = new LinkedList<ApfsDirectory>();
        for (FSElement child : this.children) {
            if (child.isDirectory()) {
                subDirectories.add((ApfsDirectory) child);
            }
        }
        Collections.sort(subDirectories, comp);
        return subDirectories;
    }

    public LinkedList<ApfsDirectory> getSubDirectories() {
        LinkedList<ApfsDirectory> subDirectories = new LinkedList<ApfsDirectory>();
        for (FSElement child : this.children) {
            if (child.isDirectory()) {
                subDirectories.add((ApfsDirectory)child);
            }
        }
        Collections.sort(subDirectories, (ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()));
        return subDirectories;
    }

    public LinkedList<ApfsFile> getFiles(Comparator<ApfsElement> comp) {
        LinkedList<ApfsFile> files = new LinkedList<ApfsFile>();
        for (FSElement child : this.children) {
            if (child instanceof ApfsFile) {
                files.add((ApfsFile) child);
            }
        }
        Collections.sort(files, comp);
        return files;
    }

    public LinkedList<ApfsFile> getFiles() {
        LinkedList<ApfsFile> files = new LinkedList<ApfsFile>();
        for (FSElement child : this.children) {
            if (child instanceof ApfsFile) {
                files.add((ApfsFile) child);
            }
        }
        Collections.sort(files, (ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()));
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
