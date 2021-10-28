package edu.umb.cs680.hw06;

import java.time.LocalDateTime;

public class TestFixtureInitializer {
    public static FileSystem createFS() {
        FileSystem fs = FileSystem.getInstance();
        fs.clearRootDirs();
        LocalDateTime time1 = LocalDateTime.of(2020, 10, 30, 11, 59);
        LocalDateTime time2 = LocalDateTime.of(2021,5,3,7,10);
        LocalDateTime time3 = LocalDateTime.of(2021, 9, 10, 10, 11);
        Directory root = new Directory(null, "root", 1, time1);
        Directory applications = new Directory(root, "applications", 2, time2);
        File a = new File(applications, "a", 5, time3);
        Directory home = new Directory(root, "home", 3, time2);
        Directory code = new Directory(home, "code", 3, time2);
        File b = new File(home, "b", 6, time3);
        File c = new File(code, "c", 8, time3);
        File d = new File(code, "d", 11, time3);
        fs.appendRootDir(root);
        root.appendChild(applications);
        root.appendChild(home);
        applications.appendChild(a);
        home.appendChild(code);
        home.appendChild(b);
        code.appendChild(c);
        code.appendChild(d);
        return fs;
    }
}

