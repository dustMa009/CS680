package edu.umb.cs680.hw08;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class LinkTest {
    private static LocalDateTime time = LocalDateTime.now();
    private static Directory root = new Directory(null, "root", 0, time);
    private static Directory applications = new Directory(root, "applications", 0, time);
    private static Directory home = new Directory(root, "home", 0, time);
    private static File a = new File(applications, "a", 20, time);
    private static File b = new File(applications, "b", 5, time);
    private static Link x = new Link(home, "x", 0, time, applications);
    private static File c = new File(home, "c", 10, time);
    private static File d = new File(home, "d", 10, time);
    private static Directory code = new Directory(home, "code", 0, time);
    private static Link y = new Link(code, "y", 0, time, b);
    private static File e = new File(code, "e", 10, time);
    private static File f = new File(code, "f", 10, time);
    private String[] fsToString(FSElement f) {
        String[] dirInfo = {
                String.valueOf(f.isDirectory()),
                f.getName(),
                Integer.toString(f.getSize()),
                f.getCreationTime().toString(),
                (d.getParent() == null) ? null : f.getParent().getName()
        };
        return dirInfo;
    }

    @BeforeAll
    public static void initalization(){
        root.appendChild(home);
        root.appendChild(applications);
        applications.appendChild(a);
        applications.appendChild(b);
        home.appendChild(x);
        home.appendChild(c);
        home.appendChild(d);
        home.appendChild(code);
        code.appendChild(y);
        code.appendChild(e);
        code.appendChild(f);
    }

    @Test
    public void linkX(){
        String[] expected = {"true", "applications", "0", time.toString(), "root"};
        String [] actual = fsToString(x.getTarget());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void linkY(){
        String[] expected= {"false", "b", "5", time.toString(), "applications"};
        String [] actual = fsToString(y.getTarget());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getTotalSize(){
        int expected = 65;
        int actual = root.getTotalSize();
        assertEquals(expected, actual);
    }
}
