package edu.umb.cs680.hw12;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement {
    protected String owner;
    protected LocalDateTime lastModifiedTime;
    public ApfsElement(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime
            lastModifiedTime) {
        super(parent, name, size, creationTime);
        this.owner = owner;
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getOwner() {
        return this.owner;
    }

    public LocalDateTime getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime newTime) {
        this.lastModifiedTime = newTime;
    }

    public abstract void accept(ApfsVisitor visitor);
}
