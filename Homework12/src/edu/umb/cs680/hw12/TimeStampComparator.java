package edu.umb.cs680.hw12;

import java.util.Comparator;

public class TimeStampComparator implements Comparator<ApfsElement> {
    @Override
    public int compare(ApfsElement e1, ApfsElement e2) {
        return e1.getLastModifiedTime().compareTo(e2.getLastModifiedTime());
    }
}
