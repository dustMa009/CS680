package edu.umb.cs680.hw11;

import java.util.Comparator;

public class MileComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getMileage()- o2.getMileage();
    }
}