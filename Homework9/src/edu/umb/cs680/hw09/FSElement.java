package edu.umb.cs680.hw09;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected FSElement parent;

    public FSElement(FSElement parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    abstract boolean isDirectory();

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public FSElement getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }
}
