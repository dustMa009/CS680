package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileTest {
    private static FileSystem fs;
    private static Directory root;
    private static Directory applications;
    private static Directory home;
    private static Directory code;
    private static File a, b, c, d;
    private static Link x, y;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        root = fs.getRootDirs().get(0);
        applications = (Directory)root.getChildren().get(0);
        a = (File)applications.getChildren().get(0);
        home = (Directory)root.getChildren().get(1);
        x = (Link)home.getChildren().get(0);
        code = (Directory)home.getChildren().get(1);
        b = (File)home.getChildren().get(2);
        y = (Link)code.getChildren().get(0);
        c = (File)code.getChildren().get(1);
        d = (File)code.getChildren().get(2);
    }

    private String[] fileToArray(File file) {
        String[] fileInfo = {file.getName(), String.valueOf(file.getSize()), file.getCreationTime().toString()};
        return fileInfo;
    }

    @Test
    public void isDirectoryTest() {
        assertFalse(a.isDirectory());
    }

    @Test
    public void fileConstructorTest() {
        String[] expected1 = {"a", "5", "2021-09-10T10:11"};
        assertSame(applications, a.getParent());
        assertArrayEquals(expected1, fileToArray(a));
    }

    @Test
    public void getNameTest() {
        assertEquals("c", c.getName());
    }

    @Test
    public void getSizeTest() {
        assertEquals(8, c.getSize());
    }

    @Test
    public void getCreationTimeTest() {
        assertEquals("2021-09-10T10:11", c.getCreationTime().toString());
    }

    @Test
    public void getParentTest() {
        assertSame(code, c.getParent());
    }

    @Test
    public void setNameTest() {
        c.setName("star");
        assertEquals("star", c.getName());
        c.setName("c");
    }
}
