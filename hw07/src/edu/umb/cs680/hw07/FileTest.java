package edu.umb.cs680.hw07;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    private static LocalDateTime today = LocalDateTime.now();
    private static Directory root = new Directory(null, "Root", 100, today);
    private static Directory dir1 = new Directory(root, "dir1", 100, today);
    private static Directory dir2 = new Directory(root, "dir2", 100, today);
    private static Directory dir3 = new Directory(dir2, "dir3", 100, today);
    private static File file1 = new File(dir1, "file1", 10, today);
    private static File file2 = new File(dir2, "file2", 10, today);
    private static File file3 = new File(dir3, "file3", 10, today);
    private String[] fileToStringArray(File d){
        String[] dirInfo = {
                String.valueOf(d.isDirectory()),
                d.getName(),
                Integer.toString(d.getSize()),
                d.getCreationTime().toString(),
                (d.getParent() == null) ? null : d.getParent().getName(),
        };
        return dirInfo;
    }
    @BeforeAll
    private static void init(){
        root.appendChild(dir1);
        root.appendChild(dir2);
        dir1.appendChild(file1);
        dir2.appendChild(dir3);
        dir2.appendChild(file2);
        dir3.appendChild(file3);
    }

    @Test
    public void verifyFileA(){
        String[] expected = {"false", "file1", "10", today.toString(), "dir1"};
        String[] actual = fileToStringArray(file1);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileB(){
        String[] expected = {"false", "file2", "10", today.toString(), "dir2"};
        String[] actual = fileToStringArray(file2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileC(){
        String[] expected = {"false", "file3", "10", today.toString(), "dir3"};
        String[] actual = fileToStringArray(file3);
        assertArrayEquals(expected, actual);
    }

}