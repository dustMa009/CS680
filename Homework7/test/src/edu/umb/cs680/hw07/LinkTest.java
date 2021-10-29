package edu.umb.cs680.hw07;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {
    private static FileSystem fs;
    private static Directory applications;
    private static Directory home;
    private static File a;
    private static Link x;
    private static Directory code;
    private static Link y;

    @BeforeAll
    public static void setUp() {
        fs = TestFixtureInitializer.createFS();
        Directory root = fs.getRootDirs().get(0);
        applications = (Directory)root.getChildren().get(0);
        home = (Directory)root.getChildren().get(1);
        a = (File)applications.getChildren().get(0);
        x = (Link)home.getChildren().get(0);
        code = (Directory)home.getChildren().get(1);
        y = (Link)code.getChildren().get(0);
    }

    private String[] linkToString(Link x) {
        String[] linkInfo = {String.valueOf(x.getParent().hashCode()), x.getName(), String.valueOf(x.getSize()),
        x.getCreationTime().toString(), String.valueOf(x.getTarget().hashCode())};
        return linkInfo;
    }

    @Test
    public void linkConstructorTestOnXY() {
        String[] expect_x = {String.valueOf(home.hashCode()), "x", "0", "2020-02-03T04:05", String.valueOf(applications.hashCode())};
        String[] expect_y = {String.valueOf(code.hashCode()), "y", "0", "2020-10-30T11:59", String.valueOf(a.hashCode())};
        assertArrayEquals(expect_x, linkToString(x));
        assertArrayEquals(expect_y, linkToString(y));
    }

    @Test
    public void isDirectoryTestOnXY() {
        assertFalse(x.isDirectory());
        assertFalse(y.isDirectory());
    }

    @Test
    public void getNameTestOnXY() {
        assertEquals("x", x.getName());
        assertEquals("y", y.getName());
    }

    @Test
    public void getSizeTestOnXY() {
        assertEquals(0, x.getSize());
        assertEquals(0, y.getSize());
    }

    @Test
    public void getCreationTimeOnXY() {
        assertEquals("2020-02-03T04:05", x.getCreationTime().toString());
        assertEquals("2020-10-30T11:59", y.getCreationTime().toString());
    }

    @Test
    public void getParentOnXY() {
        assertEquals(home.hashCode(), x.getParent().hashCode());
        assertEquals(code.hashCode(), y.getParent().hashCode());
    }

    @Test
    public void setNameTestOnXY() {
        assertEquals("x", x.getName());
        x.setName("apple");
        assertEquals("apple", x.getName());
        x.setName("x");
    }

    @Test
    public void getTargetOnXY() {
        assertSame(applications, x.getTarget());
        assertSame(a, y.getTarget());
    }
}
