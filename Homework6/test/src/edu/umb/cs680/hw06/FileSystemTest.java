package edu.umb.cs680.hw06;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    private static FileSystem fs;
    private static Directory root;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        root = fs.getRootDirs().get(0);
    }

    @Test
    public void singletonTest() {
        FileSystem fs2 = FileSystem.getInstance();
        assertSame(fs, fs2);
    }

    @Test
    public void getRoorDirTest() {
        Directory[] expected = {root};
        int i = 0;
        for (Directory e : fs.getRootDirs()) {
            assertSame(expected[0], e);
            i++;
        }
    }

    @Test
    public void addRootDirTest() {
        LocalDateTime time = LocalDateTime.of(2020,1,5,9,10);
        Directory new_root = new Directory(null, "new_root", 6, time);
        Directory[] expected_1 = {root};
        Directory[] expected_2 = {root, new_root};
        int i = 0;
        for (Directory e : fs.getRootDirs()) {
            assertSame(expected_1[i], e);
            i++;
        }
        fs.appendRootDir(new_root);
        i = 0;
        for (Directory e : fs.getRootDirs()) {
            assertSame(expected_2[i], e);
            i++;
        }
        fs.removeRootDir(new_root);
    }

    @Test
    public void removeRootDirTest() {
        LocalDateTime time = LocalDateTime.of(2020,1,5,9,10);
        Directory new_root = new Directory(null, "new_root", 6, time);
        Directory[] expected_1 = {root, new_root};
        Directory[] expected_2 = {root};
        fs.appendRootDir(new_root);
        int i = 0;
        for (Directory e : fs.getRootDirs()) {
            assertSame(expected_1[i], e);
            i++;
        }
        fs.removeRootDir(new_root);
        i = 0;
        for (Directory e : fs.getRootDirs()) {
            assertSame(expected_2[i], e);
            i++;
        }
    }

    @Test
    public void clearRootDirTest() {
        Directory[] before = {root};
        int i = 0;
        for (Directory d : fs.getRootDirs()) {
            assertSame(before[i], d);
            i++;
        }
        fs.clearRootDirs();
        assertEquals(0, fs.getRootDirs().size());
        fs.appendRootDir(root);
    }
}
