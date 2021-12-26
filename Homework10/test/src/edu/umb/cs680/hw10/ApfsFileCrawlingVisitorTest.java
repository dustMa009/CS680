package edu.umb.cs680.hw10;

import edu.umb.cs680.hw10.apfs.util.ApfsCountingVisitor;
import edu.umb.cs680.hw10.apfs.util.ApfsFileCrawlingVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApfsFileCrawlingVisitorTest {
    private static APFS apfs;
    private static ApfsFile a, b, c, d;
    private static ApfsDirectory root, applications, home, code;
    private static ApfsLink x;

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
        c = (ApfsFile)code.getChildren().get(1);
        d = (ApfsFile)code.getChildren().get(2);
    }

    @Test
    public void constructorTest() {
        ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
        assertEquals(0, visitor.getFiles().size());
    }

    @Test
    public void visitLinkX() {
        ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
        x.accept(visitor);
        assertEquals(0, visitor.getFiles().size());
    }

    @Test
    public void visitFileAB() {
        ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
        // visit File a
        a.accept(visitor);
        assertEquals(1, visitor.getFiles().size());
        ApfsElement[] expected_a = {a};
        int i = 0;
        for (ApfsElement e : visitor.getFiles()) {
            assertSame(expected_a[i], e);
            i++;
        }
        // visite File b
        b.accept(visitor);
        assertEquals(2, visitor.getFiles().size());
        ApfsElement[] expected_b = {a, b};
        i = 0;
        for (ApfsElement e : visitor.getFiles()) {
            assertSame(expected_b[i], e);
            i++;
        }
    }

    @Test
    public void visitDirectoryCode() {
        ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
        code.accept(visitor);
        assertEquals(2, visitor.getFiles().size());
        ApfsElement[] expected = {c, d};
        int i = 0;
        for (ApfsElement e : visitor.getFiles()) {
            assertSame(expected[i], e);
            i++;
        }
    }

    @Test
    public void visitDirectoryRoot() {
        ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
        root.accept(visitor);
        assertEquals(4, visitor.getFiles().size());
        ApfsElement[] expected = {a, c, d, b};
        int i = 0;
        for (ApfsElement e : visitor.getFiles()) {
            assertSame(expected[i], e);
            i++;
        }
    }
}
