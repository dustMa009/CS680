package edu.umb.cs680.hw08;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {
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

    private String[] directoryToString(Directory d) {
        String[] directoryInfo = {String.valueOf(d.getParent().hashCode()),
                d.getName(), String.valueOf(d.getSize()), d.getCreationTime().toString()};
        return directoryInfo;
    }

    @Test
    public void constructerTest() {
        String[] expected = {String.valueOf(root.hashCode()), "home", "25", "2021-05-03T07:10"};
        assertArrayEquals(expected, directoryToString(home));
    }

    @Test
    public void isBooleanTest() {
        assertTrue(root.isDirectory());
        assertTrue(code.isDirectory());
    }

    @Test
    public void getNameTest() {
        assertEquals("applications", applications.getName());
        assertEquals("code", code.getName());
    }

    @Test
    public void getSizeTest() {
        assertEquals(19, code.getSize());
        assertEquals(30, root.getSize());
    }

    @Test
    public void getCreationTimeTest() {
        assertEquals("2020-10-30T11:59", root.getCreationTime().toString());
        assertEquals("2021-05-03T07:10", home.getCreationTime().toString());
    }

    @Test
    public void getParentTest() {
        assertSame(null, root.getParent());
        assertSame(root, home.getParent());
        assertSame(home, code.getParent());
    }

    @Test
    public void setNameTestOnApplications() {
        assertEquals("applications", applications.getName());
        applications.setName("apple");
        assertEquals("apple", applications.getName());
        applications.setName("applications");
    }

    @Test
    public void getChildrenTestOnApplicationsCode() {
        FSElement[] expected_1 = {applications, home};
        int i = 0;
        for (FSElement e : root.getChildren()) {
            assertSame(expected_1[i], e);
            i++;
        }
        assertEquals(2, root.getChildren().size());
        i = 0;
        FSElement[] expected_2 = {x, code, b};
        for (FSElement e : home.getChildren()) {
            assertSame(expected_2[i], e);
            i++;
        }
        assertEquals(3, home.getChildren().size());
    }

    @Test
    public void appendChildrenTestOnCode() {
        LocalDateTime time = LocalDateTime.of(2018, 9, 11, 20, 9);
        Directory photos = new Directory(code, "photos", 9, time);
        FSElement[] expected_before = {y, c, d};
        FSElement[] expected_after = {y, c, d, photos};
        int i = 0;
        for (FSElement e : code.getChildren()) {
            assertSame(expected_before[i], e);
            i++;
        }
        code.appendChild(photos);
        i = 0;
        for (FSElement e : code.getChildren()) {
            assertSame(expected_after[i], e);
            i++;
        }
        code.removeChild(photos);
    }

    @Test
    public void removeChildTestOnHome() {
        LocalDateTime time = LocalDateTime.of(2019, 1, 1, 10, 10);
        File car = new File(home, "car", 9, time);
        FSElement[] expected_before = {x, code, b, car};
        FSElement[] expected_after = {x, code, b};
        home.appendChild(car);
        int i = 0;
        for (FSElement e : home.getChildren()) {
            assertSame(expected_before[i], e);
            i++;
        }
        home.removeChild(car);
        i = 0;
        for (FSElement e : home.getChildren()) {
            assertSame(expected_after[i], e);
            i++;
        }
    }

    @Test
    public void countChildrenTestOnRootApplicationsCode() {
        assertEquals(1, applications.countChildren());
        assertEquals(2, root.countChildren());
        assertEquals(3, code.countChildren());
    }

    @Test
    public void getSubDirectoriesTestOnRoot() {
        Directory[] expected = {applications, home};
        int i = 0;
        for (FSElement e : root.getSubDirectories()) {
            assertSame(expected[i], e);
            i++;
        }
    }

    @Test
    public void getFilesTestOnHome() {
        File[] expected = {b};
        int i = 0;
        for (FSElement e : home.getFiles()) {
            assertSame(expected[i], e);
            i++;
        }
    }
}
