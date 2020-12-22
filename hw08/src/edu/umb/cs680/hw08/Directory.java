package edu.umb.cs680.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Iterator;

public class Directory extends FSElement{
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, size, creationTime);
        this.size = 0;
    }
    private LinkedList<FSElement> children = new LinkedList<FSElement>();
    public LinkedList<FSElement> getChildren(){
        return this.children;
    }
    public void appendChild(FSElement child){
        children.add(child);
    }

    public int countChildren(){
        return children.size();
    }

    private LinkedList<File> files = new LinkedList<File>();

    public LinkedList<File> getFiles(){
        return this.files;
    }

    public boolean isDirectory(){
        return true;
    }

    public int getTotalSize(){
        int total = this.size;
        if (this.children.size() == 0){
            return total;
        }
        else{
            Iterator <FSElement> fs = getChildren().iterator();
            while (fs.hasNext()){
                FSElement next = fs.next();
                if (next.isDirectory()){
                    total += ((Directory)next).getTotalSize();
                }
                else{
                    total += next.getSize();
                }
            }
            return total;
        }
    }
}
