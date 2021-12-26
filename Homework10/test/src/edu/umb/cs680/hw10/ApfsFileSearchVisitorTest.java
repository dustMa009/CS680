package edu.umb.cs680.hw10;

import edu.umb.cs680.hw10.apfs.util.ApfsFileSearchVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApfsFileSearchVisitorTest {
    private static APFS apfs;
    private static ApfsFile a, b, c, d;
    private static ApfsDirectory root, applications, home, code;
    private static ApfsLink y;

    @BeforeAll
    public static void setUp() {
        apfs = TestFixtureInitializer.createAPFS();
        root = apfs.getRootDir();
        applications = (ApfsDirectory)root.getChildren().get(0);
        home = (ApfsDirectory)root.getChildren().get(1);
        a = (ApfsFile)applications.getChildren().get(0);
        code = (ApfsDirectory)home.getChildren().get(1);
        y = (ApfsLink)code.getChildren().get(0);
        b = (ApfsFile)home.getChildren().get(2);
        c = (ApfsFile)code.getChildren().get(1);
        d = (ApfsFile)code.getChildren().get(2);
    }

    @Test
    public void constructorTest() {
        ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor("a");
        assertEquals(0, visitor.getFoundFiles().size());
    }

    @Test
    public void visitLinkX() {
        ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor("a");
        y.accept(visitor);
        assertEquals(0, visitor.getFoundFiles().size());
    }

    @Test
    public void visitFileCA() {
        ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor("a");
        b.accept(visitor);
        assertEquals(0, visitor.getFoundFiles().size());
        a.accept(visitor);
        assertEquals(1, visitor.getFoundFiles().size());
        ApfsFile[] expected = {a};
        int i = 0;
        for (ApfsFile f : visitor.getFoundFiles()) {
            assertSame(expected[i], f);
            i++;
        }
    }

    @Test
    public void visitDirectoryHome() {
        ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor("b");
        home.accept(visitor);
        assertEquals(1, visitor.getFoundFiles().size());
        assertSame(b, visitor.getFoundFiles().get(0));
    }

    @Test
    public void visitDirectoryRoot() {
        ApfsFileSearchVisitor visitor = new ApfsFileSearchVisitor("d");
        home.accept(visitor);
        assertEquals(1, visitor.getFoundFiles().size());
        assertSame(d, visitor.getFoundFiles().get(0));
    }
}
