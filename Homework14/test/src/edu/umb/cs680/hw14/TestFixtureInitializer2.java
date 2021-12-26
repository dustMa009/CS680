package edu.umb.cs680.hw14;

import java.time.LocalDateTime;

public class TestFixtureInitializer2 {
    public static APFS createAPFS() {
        APFS apfs = APFS.getInstance();
        LocalDateTime time1 = LocalDateTime.of(2020, 2,3,4,5);
        LocalDateTime time2 = LocalDateTime.of(2020, 10, 30, 11, 59);
        LocalDateTime time3 = LocalDateTime.of(2021,5,3,7,10);
        LocalDateTime time4 = LocalDateTime.of(2021, 9, 10, 10, 11);
        LocalDateTime time5 = LocalDateTime.of(2021, 12,3,1,9);
        ApfsDirectory root = (ApfsDirectory)apfs.initFileSystem("Apfs", 100);
        ApfsDirectory applications = new ApfsDirectory(root, "applications", 2, time2, "userApplications", time4);
        ApfsDirectory home = new ApfsDirectory(root, "home", 3, time2, "user2Home", time3);
        ApfsDirectory code = new ApfsDirectory(home, "code", 3, time3,"userCode", time5);
        ApfsFile a = new ApfsFile(applications, "a", 5, time3, "userA", time4);
        ApfsFile b = new ApfsFile(home, "b", 6, time3, "userB", time4);
        ApfsFile c = new ApfsFile(code, "c", 8, time3, "userC", time5);
        ApfsFile d = new ApfsFile(code, "d", 11, time1, "userD", time4);
        ApfsLink x = new ApfsLink(home, "x", 10, time4, applications, "userX", time4);
        ApfsLink y = new ApfsLink(code, "y", 9, time1, a, "userY", time3);

        root.appendChild(applications);
        root.appendChild(home);
        applications.appendChild(a);
        home.appendChild(x);
        home.appendChild(code);
        home.appendChild(b);
        code.appendChild(y);
        code.appendChild(c);
        code.appendChild(d);
        return apfs;
    }
}
