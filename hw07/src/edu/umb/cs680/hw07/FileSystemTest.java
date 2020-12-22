package edu.umb.cs680.hw07;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {
    private String[] dirToStringArray(LinkedList<Directory> directories) {
        String[] diretories_name = new String[directories.size()];
        int i = 0;
        for (Directory directory : directories){
            diretories_name[i] = directory.getName();
            i ++;
        }
        return diretories_name;
    }

    @Test
    void getFileSystem() {
        FileSystem instance1 = FileSystem.getFileSystem();
        FileSystem instance2 = FileSystem.getFileSystem();
        assertSame(instance1, instance2);
    }


    @Test
    void getRootDirS() {
        Directory root = new Directory(null, "Root", 100, LocalDateTime.now());
        FileSystem fileSystem = FileSystem.getFileSystem();
        fileSystem.appendRootDirs(root);
        String[] expected = {"Root"};
        String[] actual = dirToStringArray(fileSystem.getRootDirS());
        assertArrayEquals(expected, actual);


    }

}