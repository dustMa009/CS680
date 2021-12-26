package edu.umb.cs680.hw10;

import edu.umb.cs680.hw10.apfs.util.ApfsCountingVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApfsCountingVisitorTest {
    private static APFS apfs;
    private static ApfsDirectory root, applications, home;
    private static ApfsFile a, b;
    private static ApfsLink x;

    @BeforeAll
    public static void setUp() {
        apfs = TestFixtureInitializer.createAPFS();
        root = apfs.getRootDir();
        applications = (ApfsDirectory)root.getChildren().get(0);
        home = (ApfsDirectory)root.getChildren().get(1);
        a = (ApfsFile)applications.getChildren().get(0);
        b = (ApfsFile)home.getChildren().get(2);
        x = (ApfsLink)home.getChildren().get(0);
    }

    public int[] apfsCountingVisitorToIntArray(ApfsCountingVisitor visitor) {
        int[] res = {visitor.getDirNum(), visitor.getFileNum(), visitor.getLinkNum()};
        return res;
    }

    @Test
    public void constructorTest() {
        ApfsCountingVisitor visitor = new ApfsCountingVisitor();
        assertArrayEquals(new int[] {0, 0, 0}, apfsCountingVisitorToIntArray(visitor));
    }

    @Test
    public void visitorTestOnLinkX() {
        ApfsCountingVisitor visitor = new ApfsCountingVisitor();
        x.accept(visitor);
        assertArrayEquals(new int[] {0, 0, 1}, apfsCountingVisitorToIntArray(visitor));
    }

    @Test
    public void visitorTestOnFileAB() {
        ApfsCountingVisitor visitor = new ApfsCountingVisitor();
        a.accept(visitor);
        assertArrayEquals(new int[] {0, 1, 0}, apfsCountingVisitorToIntArray(visitor));
        b.accept(visitor);
        assertArrayEquals(new int[] {0, 2, 0}, apfsCountingVisitorToIntArray(visitor));
    }

    @Test
    public void visitorTestOnDirectoryApplicationHome() {
        ApfsCountingVisitor visitor = new ApfsCountingVisitor();
        applications.accept(visitor);
        assertArrayEquals(new int[] {1, 1, 0}, apfsCountingVisitorToIntArray(visitor));
        home.accept(visitor);
        assertArrayEquals(new int[] {3, 4, 2}, apfsCountingVisitorToIntArray(visitor));
    }

    @Test
    public void visitorTestOnRoot() {
        ApfsCountingVisitor visitor = new ApfsCountingVisitor();
        root.accept(visitor);
        assertArrayEquals(new int[] {4, 4, 2}, apfsCountingVisitorToIntArray(visitor));
    }
}
