package edu.umb.cs680.hw10;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsElement {

    public ApfsFile(FSElement parent, String name, int size, LocalDateTime creationTime,
                    String owner, LocalDateTime lastModifiedTime) {
        super(parent, name, size, creationTime, owner, lastModifiedTime);
    }

    @Override
    public void accept(ApfsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    boolean isDirectory() {
        return false;
    }
}
