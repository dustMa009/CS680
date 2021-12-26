package edu.umb.cs680.hw09;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsElement {

    public ApfsFile(FSElement parent, String name, int size, LocalDateTime creationTime,
                    String owner, LocalDateTime lastModifiedTime) {
        super(parent, name, size, creationTime, owner, lastModifiedTime);
    }

    @Override
    boolean isDirectory() {
        return false;
    }
}
