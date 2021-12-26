package edu.umb.cs680.hw14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class APFSTest {
    private static APFS apfs;
    private static ApfsDirectory root;

    @BeforeAll
    public static void setUp() {
        apfs = APFS.getInstance();
        root = (ApfsDirectory)apfs.initFileSystem("apfsSystem", 100);
    }

    @Test
    public void apfsSingletonTest() {
        APFS apfs2 = APFS.getInstance();
        assertSame(apfs, apfs2);
    }

    private String[] apfsDirectoryToStringArray(ApfsDirectory directory) {
        String hashcode;
        if (directory.getParent() == null) {
            hashcode = "null";
        } else {
            hashcode = String.valueOf(directory.getParent().hashCode());
        }
        String[] res = {hashcode, directory.getName(), String.valueOf(directory.getSize()),
                directory.getCreationTime().toString(), directory.getOwner(), directory.getLastModifiedTime().toString()};
        // System.out.println(res);
        return res;
    }

    @Test
    public void initFileSystemTest() {
        String[]  expected = {"null", "Root", "0", "2020-01-01T00:00", "System", "2020-01-01T00:00"};
        assertArrayEquals(expected, apfsDirectoryToStringArray(root));
    }

    @Test
    public void getRootDirTest() {
        ApfsDirectory root2 = apfs.getRootDir();
        assertSame(root, root2);
    }
}
