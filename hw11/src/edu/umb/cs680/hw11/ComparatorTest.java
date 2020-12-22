package edu.umb.cs680.hw11;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorTest {

    private static Car car1 = new Car("a1", "b", 5000, 2015, 5000);
    private static Car car2 = new Car("a2", "b", 2000, 2016, 1000);
    private static Car car3 = new Car("a3", "b", 3000, 2017, 4000);
    private static Car car4 = new Car("a4", "b", 1000, 2018, 3000);
    private static Car car5 = new Car("a5", "b", 4000, 2019, 2000);


    @Test
    public void mileComparatorTest(){
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        Car[] expected = {car4, car2, car3, car5, car1};
        Collections.sort(cars, new MileComparator());
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void priceComparatorTest(){
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        Car[] expected = {car2, car5, car4, car3, car1};
        Collections.sort(cars, new PriceComparator());
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void yearComparatorTest(){
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        Car[] expected = {car1, car2, car3, car4, car5};
        Collections.sort(cars, new YearComparator());
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void paretoComparatorTest(){
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        for (Car x : cars) {
            x.setDominateCount(cars);
        }
        Car[] expected = {car2, car4, car5, car3, car1};
        Collections.sort(cars, new ParetoComparator());
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
    }
}
