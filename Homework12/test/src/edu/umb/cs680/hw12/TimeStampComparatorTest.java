package edu.umb.cs680.hw12;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TimeStampComparatorTest {
    private static APFS apfs;
    private static ApfsDirectory root;
    private static ApfsDirectory applications;
    private static ApfsDirectory home;
    private static ApfsDirectory code;
    private static ApfsFile a, b, c, d;
    private static ApfsLink x, y;

    @BeforeAll
    public static void setUp() {
        apfs = TestFixtureInitializer2.createAPFS();
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

    @Test
    public void appendChildTest() {
        LocalDateTime time = LocalDateTime.of(2020, 12,25,6,6);
        ApfsFile f = new ApfsFile(root, "f", 5, time, "userF", time);
        ApfsElement[] old_children = {home, applications};
        ApfsElement[] new_children = {f, home, applications};
        int i = 0;
        for (ApfsElement e : root.getChildren(new TimeStampComparator())) {
            assertSame(old_children[i], e);
            i++;
        }
        assertEquals(2, root.getChildren(new TimeStampComparator()).size());
        root.appendChild(f);
        i = 0;
        for (ApfsElement e : root.getChildren(new TimeStampComparator())) {
            assertSame(new_children[i], e);
            i++;
        }
        assertEquals(3, root.getChildren(new TimeStampComparator()).size());
        root.removeChild(f);
        i = 0;
        for (ApfsElement e : root.getChildren(new TimeStampComparator())) {
            assertSame(old_children[i], e);
            i++;
        }
        assertEquals(2, root.getChildren(new TimeStampComparator()).size());
    }

    @Test
    public void getChildrenTest() {
        ApfsElement[] expected = {y, d, c};
        int i = 0;
        for (ApfsElement e : code.getChildren(new TimeStampComparator())) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(3, code.getChildren(new TimeStampComparator()).size());
    }

    @Test
    public void getSubDirectoriesTest() {
        ApfsElement[] expected = {code};
        int i = 0;
        for (ApfsElement e : home.getSubDirectories(new TimeStampComparator())) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(1, home.getSubDirectories(new TimeStampComparator()).size());
    }

    @Test
    public void getFilesTest() {
        ApfsElement[] expected = {d, c};
        int i = 0;
        for (ApfsElement e : code.getFiles(new TimeStampComparator())) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(2, code.getFiles(new TimeStampComparator()).size());
    }
}
