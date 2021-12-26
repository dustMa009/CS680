package edu.umb.cs680.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children = new LinkedList<FSElement>();
    }

    @Override
    boolean isDirectory() {
        return true;
    }

    public LinkedList<FSElement> getChildren() {
        return children;
    }

    public void appendChild(FSElement child) {
        this.children.add(child);
    }

    public void removeChild(FSElement child) {
        this.children.remove(child);
    }

    public int countChildren() { return children.size(); }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<Directory>();
        for (FSElement child : this.children) {
            if (child.isDirectory()) {
                subDirectories.add((Directory) child);
            }
        }
        int a = 0;
        a += 1;
        return subDirectories;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<File>();
        for (FSElement child : this.children) {
            if (!child.isDirectory()) {
                files.add((File) child);
            }
        }
        return files;
    }

    // Instead of implementing getTotalSize(), I overrided getSize() method to calculate total disk consumption under current directory. 
    @Override
    public int getSize() {    
        int total_size = 0;
        for (FSElement child : children) {
            total_size += child.getSize();
        }
        this.size = total_size;
        return this.size;
    }
}
