package edu.umb.cs680.hw14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class ApfsLinkTest {
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
        x = (ApfsLink)home.getChildren().get(2);
        code = (ApfsDirectory)home.getChildren().get(1);
        b = (ApfsFile)home.getChildren().get(0);
        y = (ApfsLink)code.getChildren().get(2);
        c = (ApfsFile)code.getChildren().get(0);
        d = (ApfsFile)code.getChildren().get(1);
    }

    private String[] apfsLinkToString(ApfsLink link) {
        String[] res = {String.valueOf(link.getParent().hashCode()), link.getName(), String.valueOf(link.getSize()),
                link.getCreationTime().toString(), link.getOwner(), link.getLastModifiedTime().toString(), String.valueOf(link.getTarget().hashCode())};
        return res;
    }

    @Test
    public void constructorTest() {
        String[] expected_x = {String.valueOf(home.hashCode()), "x", "0", "2021-09-10T10:11",
                "userX", "2021-09-10T10:11", String.valueOf(applications.hashCode())};
        String[] expected_y = {String.valueOf(code.hashCode()), "y", "0", "2020-02-03T04:05",
                "userY", "2021-05-03T07:10", String.valueOf(a.hashCode())};
        assertArrayEquals(expected_x, apfsLinkToString(x));
        assertArrayEquals(expected_y, apfsLinkToString(y));
    }

    @Test
    public void isDirectoryTest() {
        assertFalse(x.isDirectory());
        assertFalse(y.isDirectory());
    }

    @Test
    public void getNameTest() {
        assertEquals("x", x.getName());
        assertEquals("y", y.getName());
    }

    @Test
    public void getSizeTest() {
        assertEquals(0, x.getSize());
        assertEquals(0, y.getSize());
    }

    @Test
    public void getCreationTime() {
        assertEquals("2021-09-10T10:11", x.getCreationTime().toString());
        assertEquals("2020-02-03T04:05", y.getCreationTime().toString());
    }

    @Test
    public void getParentTest() {
        assertSame(home, x.getParent());
        assertSame(code, y.getParent());
    }

    @Test
    public void setNameTest() {
        assertEquals("x", x.getName());
        x.setName("apple");
        assertEquals("apple", x.getName());
        x.setName("x");
        assertEquals("x", x.getName());
    }

    @Test
    public void getOwnerTest() {
        assertEquals("userX", x.getOwner());
        assertEquals("userY", y.getOwner());
    }

    @Test
    public void getLastModifiedTimeTest() {
        assertEquals("2021-09-10T10:11", x.getLastModifiedTime().toString());
        assertEquals("2021-05-03T07:10", y.getLastModifiedTime().toString());
    }

    @Test
    public void setLastModifiedTimeTest() {
        LocalDateTime new_time = LocalDateTime.of(2021, 12,25,0,5);
        LocalDateTime old_time = LocalDateTime.of(2021, 9, 10, 10, 11);
        assertEquals(old_time.toString(), x.getLastModifiedTime().toString());
        x.setLastModifiedTime(new_time);
        assertEquals(new_time.toString(), x.getLastModifiedTime().toString());
        x.setLastModifiedTime(old_time);
        assertEquals(old_time.toString(), x.getLastModifiedTime().toString());
    }

    @Test
    public void getTargetTest() {
        assertSame(applications, x.getTarget());
        assertSame(a, y.getTarget());
    }
}
