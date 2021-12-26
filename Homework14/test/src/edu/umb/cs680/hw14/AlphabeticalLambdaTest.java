package edu.umb.cs680.hw14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AlphabeticalLambdaTest {
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
        LocalDateTime time = LocalDateTime.of(2021, 12,25,6,6);
        ApfsFile f = new ApfsFile(root, "f", 5, time, "userF", time);
        ApfsElement[] old_children = {applications, home};
        ApfsElement[] new_children = {applications, f, home};
        int i = 0;
        for (ApfsElement e : root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(old_children[i], e);
            i++;
        }
        assertEquals(2, root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
        root.appendChild(f);
        i = 0;
        for (ApfsElement e : root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(new_children[i], e);
            i++;
        }
        assertEquals(3, root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
        root.removeChild(f);
        i = 0;
        for (ApfsElement e : root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(old_children[i], e);
            i++;
        }
        assertEquals(2, root.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
    }

    @Test
    public void getChildrenTest() {
        ApfsElement[] expected = {c, d, y};
        int i = 0;
        for (ApfsElement e : code.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(3, code.getChildren((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
    }

    @Test
    public void getSubDirectoriesTest() {
        ApfsElement[] expected = {code};
        int i = 0;
        for (ApfsElement e : home.getSubDirectories((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(1, home.getSubDirectories((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
    }

    @Test
    public void getFilesTest() {
        ApfsElement[] expected = {c, d};
        int i = 0;
        for (ApfsElement e : code.getFiles((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName()))) {
            assertSame(expected[i], e);
            i++;
        }
        assertEquals(2, code.getFiles((ApfsElement e1, ApfsElement e2) -> e1.getName().compareTo(e2.getName())).size());
    }
}
