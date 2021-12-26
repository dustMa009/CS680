package edu.umb.cs680.hw11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class CarTest {
    private static Car car1, car2, car3, car4;

    @BeforeAll
    public static void setUp() {
        car1 = new Car("ModelA", "make5", 1000, 2020, 9000.5f);
        car2 = new Car("ModelB","make8", 2000, 2010, 8000.0f);
        car3 = new Car("ModelC","make9", 1000, 2010, 8000.0f);
        car4 = new Car("ModelD","make10", 2000, 2021, 10000.0f);
    }

    public String[] carToStringArray(Car c) {
        String[] res = {c.getModel(), c.getMake(), String.valueOf(c.getMileage()),
        String.valueOf(c.getYear()), String.valueOf(c.getPrice()), String.valueOf(c.getDominationCount())};
        return res;
    }

    @Test
    public void constructorTest() {
        String[] expected1 = {"ModelA", "make5", "1000", "2020", "9000.5", "0"};
        String[] expected2 = {"ModelB", "make8", "2000", "2010", "8000.0", "0"};
        assertArrayEquals(expected1, carToStringArray(car1));
        assertArrayEquals(expected2, carToStringArray(car2));
    }

    @Test
    public void getModelTest() {
        assertEquals("ModelA", car1.getModel());
        assertEquals("ModelB", car2.getModel());
    }

    @Test
    public void getMakeTest() {
        assertEquals("make5", car1.getMake());
        assertEquals("make8", car2.getMake());
    }

    @Test
    public void getMileageTest() {
        assertEquals(1000, car1.getMileage());
        assertEquals(2000, car2.getMileage());
    }

    @Test
    public void getYearTest() {
        assertEquals(2020, car1.getYear());
        assertEquals(2010, car2.getYear());
    }

    @Test
    public void getPriceTest() {
        assertEquals(9000.5f, car1.getPrice());
        assertEquals(8000.0f, car2.getPrice());
    }

    @Test
    public void dominationCountTest() {
        ArrayList<Car> usedCars = new ArrayList<>();
        usedCars.add(car1);
        usedCars.add(car2);
        usedCars.add(car3);
        usedCars.add(car4);
        for (Car c : usedCars) {
            c.setDominationCount(usedCars);
        }
        assertEquals(1, car1.getDominationCount());
        assertEquals(1, car2.getDominationCount());
        assertEquals(0, car3.getDominationCount());
        assertEquals(3, car4.getDominationCount());
    }
}
