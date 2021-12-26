package edu.umb.cs680.hw10;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsElement {
    private ApfsElement target;
    public ApfsLink(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, ApfsElement target,
                    String owner, LocalDateTime lastModifiedTime) {
        super(parent, name, size, creationTime, owner, lastModifiedTime);
        this.target = target;
        this.size = 0;
    }

    @Override
    boolean isDirectory() {
        return false;
    }

    public ApfsElement getTarget() { return target; }

    @Override
    public void accept(ApfsVisitor visitor) {
        visitor.visit(this);
    }
}
