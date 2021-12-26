package edu.umb.cs680.hw10;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class ApfsFileTest {
    private static APFS apfs;
    private static ApfsDirectory root;
    private static ApfsDirectory applications;
    private static ApfsDirectory home;
    private static ApfsDirectory code;
    private static ApfsFile a;
    private static ApfsFile b;
    private static ApfsFile c;
    private static ApfsFile d;

    @BeforeAll
    public static void setUp() {
        apfs = TestFixtureInitializer.createAPFS();
        root = apfs.getRootDir();
        applications = (ApfsDirectory)root.getChildren().get(0);
        home = (ApfsDirectory)root.getChildren().get(1);
        a = (ApfsFile)applications.getChildren().get(0);
        code = (ApfsDirectory)home.getChildren().get(1);
        b = (ApfsFile)home.getChildren().get(2);
        c = (ApfsFile)code.getChildren().get(1);
        d = (ApfsFile)code.getChildren().get(2);
    }

    private String[] apfsFileToString(ApfsFile file) {
        String[] res = {String.valueOf(file.getParent().hashCode()), file.getName(), String.valueOf(file.getSize()),
                file.getCreationTime().toString(), file.getOwner(), file.getLastModifiedTime().toString()};
        return res;
    }

    @Test
    public void constructorTest() {
        String[] a_expected = {String.valueOf(applications.hashCode()), "a", "5", "2021-05-03T07:10", "userA", "2021-09-10T10:11"};
        String[] c_expected = {String.valueOf(code.hashCode()), "c", "8", "2021-05-03T07:10", "userC", "2021-12-03T01:09"};
        assertArrayEquals(a_expected, apfsFileToString(a));
        assertArrayEquals(c_expected, apfsFileToString(c));
    }

    @Test
    public void isDirectoryTest() {
        assertFalse(b.isDirectory());
        assertFalse(d.isDirectory());
    }

    @Test
    public void getNameTest() {
        assertEquals("a", a.getName());
        assertEquals("b", b.getName());
    }

    @Test
    public void getSizeTest() {
        assertEquals(8, c.getSize());
        assertEquals(11, d.getSize());
    }

    @Test
    public void getParent() {
        assertSame(applications, a.getParent());
        assertSame(code, d.getParent());
    }

    @Test
    public void setNameTest() {
        assertEquals("b", b.getName());
        b.setName("Butter");
        assertEquals("Butter", b.getName());
        b.setName("b");
        assertEquals("b", b.getName());
    }

    @Test
    public void getOwnerTest() {
        assertEquals("userC", c.getOwner());
        assertEquals("userD", d.getOwner());
    }

    @Test
    public void getCreationTimeTest() {
        assertEquals("2021-05-03T07:10", b.getCreationTime().toString());
        assertEquals("2020-02-03T04:05", d.getCreationTime().toString());
    }

    @Test
    public void getLastModifiedTimeTest() {
        assertEquals("2021-09-10T10:11", a.getLastModifiedTime().toString());
        assertEquals("2021-12-03T01:09", c.getLastModifiedTime().toString());
    }

    @Test
    public void setLastModifiedTimeTest() {
        LocalDateTime new_time = LocalDateTime.of(2021, 12,25,0,5);
        LocalDateTime old_time = LocalDateTime.of(2021, 9, 10, 10, 11);
        assertEquals(old_time.toString(), a.getLastModifiedTime().toString());
        a.setLastModifiedTime(new_time);
        assertEquals(new_time.toString(), a.getLastModifiedTime().toString());
        a.setLastModifiedTime(old_time);
        assertEquals(old_time.toString(), a.getLastModifiedTime().toString());
    }
}
