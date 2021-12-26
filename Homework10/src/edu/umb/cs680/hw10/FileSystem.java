package edu.umb.cs680.hw10;

import java.util.LinkedList;

public abstract class FileSystem {
    protected String name;
    protected int capacity;
    protected int id;
    protected LinkedList<FSElement> rootDirs;

    public FSElement initFileSystem(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.rootDirs = new LinkedList<FSElement>();
        FSElement root = createDedaultRoot();
        if (root.isDirectory() && capacity >= root.getSize()) {
            setRoot(root);
            this.id = root.hashCode();
            return root;
        } else {
            return null;
        }
    }

    protected abstract FSElement createDedaultRoot();
    protected void setRoot(FSElement root) { rootDirs.add(root); }
    protected int getCapacity() { return this.capacity; }
    protected int getId() { return this.id; }
    protected String getName() { return this.name; }
}

