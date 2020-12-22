package edu.umb.cs680.hw07;

import java.util.LinkedList;

public class FileSystem {
    private FileSystem(){};
    private static FileSystem system = null;
    public static FileSystem getFileSystem(){
        if (system == null){
            system = new FileSystem(){};
        }
        return system;
    }
    private LinkedList<Directory> rootDirs = new LinkedList<Directory>();

    public LinkedList<Directory> getRootDirS(){
        return this.rootDirs;
    }

    public void appendRootDirs(Directory root){
        rootDirs.add(root);
    }
}