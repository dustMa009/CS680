package edu.umb.cs680.hw10;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class ApfsDirectoryTest {
    private static APFS apfs;
    private static ApfsDirectory root;
    private static ApfsDirectory applications;
    private static ApfsDirectory home;
    private static ApfsDirectory code;
    private static ApfsFile a, b, c, d;
    private static ApfsLink x, y;

    @BeforeAll
    public static void setUp() {
        apfs = TestFixtureInitializer.createAPFS();
        root = apfs.getRootDir();
        applications = (ApfsDirectory)root.getChildren().get(0);
        home = (ApfsDirectory)root.getChildren().get(1);
        a = (ApfsFile)applications.getChildren().get(0);
        x = (ApfsLink)home.getChildren().get(0);
        code = (ApfsDirectory)home.getChildren().get(1);
        b = (ApfsFile)home.getChildren().get(2);
        y = (ApfsLink)code.getChildren().get(0);
        c = (ApfsFile)code.getChildren().get(1);
        d = (ApfsFile)code.getChildren().get(2);
    }

    private String[] apfsDirectoryToString(ApfsDirectory directory) {
        String hashcode;
        if (directory.getParent() == null) {
            hashcode = "null";
        } else {
            hashcode = String.valueOf(directory.getParent().hashCode());
        }
        String[] res = {hashcode, directory.getName(), String.valueOf(directory.getSize()),
                directory.getCreationTime().toString(), directory.getOwner(), directory.getLastModifiedTime().toString()};
        return res;
    }

    @Test
    public void constructorTest() {
        String[] expected_root = {"null", "Root", "30", "2020-01-01T00:00", "System", "2020-01-01T00:00"};
        String[] expected_applications = {String.valueOf(root.hashCode()), "applications",
                "5", "2020-10-30T11:59", "userApplications", "2021-05-03T07:10"};
        assertArrayEquals(expected_root, apfsDirectoryToString(root));
        assertArrayEquals(expected_applications, apfsDirectoryToString(applications));
    }

    @Test
    public void isDirectoryTest() {
        assertTrue(home.isDirectory());
        assertTrue(code.isDirectory());
    }

    @Test
    public void getNameTest() {
        assertEquals("Root", root.getName());
        assertEquals("home", home.getName());
    }

    @Test
    public void getSizeTest() {
        assertEquals(5, applications.getSize());
        assertEquals(19, code.getSize());
    }

    @Test
    public void getCreationTimeTest() {
        assertEquals("2020-01-01T00:00", root.getCreationTime().toString());
        assertEquals("2021-05-03T07:10", code.getCreationTime().toString());
    }

    @Test
    public void getParentTest() {
        assertSame(root, home.getParent());
        assertSame(home, code.getParent());
    }

    @Test
    public void setNameTest() {
        assertEquals("home", home.getName());
        home.setName("Tree");
        assertEquals("Tree", home.getName());
        home.setName("home");
        assertEquals("home", home.getName());
    }

    @Test
    public void getOwnerTest() {
        assertEquals("System", root.getOwner());
        assertEquals("userApplications", applications.getOwner());
    }

    @Test
    public void getLastModifiedTimeTest() {
        assertEquals("2021-09-10T10:11", home.getLastModifiedTime().toString());
        assertEquals("2021-05-03T07:10", applications.getLastModifiedTime().toString());
    }

    @Test
    public void setLastModifiedTimeTest() {
        LocalDateTime new_time = LocalDateTime.of(2021, 12,25,0,5);
        LocalDateTime old_time = LocalDateTime.of(2021, 12,3,1,9);
        assertEquals(old_time.toString(), code.getLastModifiedTime().toString());
        code.setLastModifiedTime(new_time);
        assertEquals(new_time.toString(), code.getLastModifiedTime().toString());
        code.setLastModifiedTime(old_time);
        assertEquals(old_time.toString(), code.getLastModifiedTime().toString());
    }

    @Test
    public void getChildrenTest() {
        ApfsElement[] expected_applications = {a};
        ApfsElement[] expected_home = {x, code, b};
        int i = 0;
        for (ApfsElement e : applications.getChildren()) {
            assertSame(expected_applications[i], e);
            i++;
        }
        assertEquals(1, applications.getChildren().size());
        i = 0;
        for (ApfsElement e : home.getChildren()) {
            assertSame(expected_home[i], e);
            i++;
        }
        assertEquals(3, home.getChildren().size());
    }

    @Test
    public void appendAndRemoveChildTest() {
        LocalDateTime time = LocalDateTime.of(2021, 12,25,5,5);
        ApfsLink m = new ApfsLink(code, "m", 0, time, b, "userM", time);
        ApfsElement[] old_code_childrens = {y, c, d};
        ApfsElement[] new_code_childrens = {y, c, d, m};
        int i = 0;
        for (ApfsElement e : code.getChildren()) {
            assertSame(old_code_childrens[i], e);
            i++;
        }
        assertEquals(3, code.getChildren().size());
        code.appendChild(m);
        i = 0;
        for (ApfsElement e : code.getChildren()) {
            assertSame(new_code_childrens[i], e);
            i++;
        }
        assertEquals(4, code.getChildren().size());
        code.removeChild(m);
        i = 0;
        for (ApfsElement e : code.getChildren()) {
            assertSame(old_code_childrens[i], e);
            i++;
        }
        assertEquals(3, code.getChildren().size());
    }

    @Test
    public void countChildrenTest() {
        assertEquals(2, root.countChildren());
        assertEquals(3, home.countChildren());
    }

    @Test
    public void getSubDirectoriesTestOnRoot() {
        ApfsDirectory[] expected = {applications, home};
        int i = 0;
        for (ApfsDirectory d : root.getSubDirectories()) {
            assertSame(expected[i], d);
            i++;
        }
        assertEquals(2, root.getSubDirectories().size());
    }

    @Test
    public void getFilesTestOnCode() {
        ApfsFile[] expected = {c, d};
        int i = 0;
        for (ApfsFile f : code.getFiles()) {
            assertSame(expected[i], f);
            i++;
        }
        assertEquals(2, code.getFiles().size());
    }
}
