package edu.umb.cs680.hw11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertSame;

public class MileageComparatorTest {
    private static Car car1, car2, car3, car4, car5;
    private static ArrayList<Car> usedCars;

    @BeforeAll
    public static void setUp() {
        car1 = new Car("ModelA", "make5", 1200, 2012, 9000.5f);
        car2 = new Car("ModelB","make8", 2500, 2018, 8500.0f);
        car3 = new Car("ModelC","make9", 1000, 2010, 8000.0f);
        car4 = new Car("ModelD","make10", 2000, 2021, 10000.0f);
        car5 = new Car("ModelE","make3", 800, 2009, 6000.0f);
        usedCars = new ArrayList<Car>();
        usedCars.add(car1);
        usedCars.add(car2);
        usedCars.add(car3);
        usedCars.add(car4);
        usedCars.add(car5);
    }

    @Test
    public void sortWithMilege() {
        Car[] expected = {car5, car3, car1, car4, car2};
        int i = 0;
        Collections.sort(usedCars, new MileageComparator<Car>());
        for (Car c : usedCars) {
            assertSame(expected[i], c);
            i++;
        }
    }
}