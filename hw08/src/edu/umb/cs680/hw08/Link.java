package edu.umb.cs680.hw08;

import java.time.LocalDateTime;

public class Link extends FSElement{
    private FSElement target;
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target){
        super(parent, name, size, creationTime);
        this.size = 0;
        this.target = target;
    }

    @Override
    public int getSize() {
        return 0;
    }

    public boolean isDirectory() {
        return false;
    }

    public void setTarget(FSElement target){
        this.target = target;
    }

    public FSElement getTarget(){
        return this.target;
    }
}