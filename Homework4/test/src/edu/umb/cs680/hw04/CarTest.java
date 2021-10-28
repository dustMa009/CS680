package edu.umb.cs680.hw04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CarTest {
    private String eo1 = System.getProperty("line.separator");

    private String[] CarToStringArray(Car c) {
        String[] res = {c.getMake(), c.getModel(), String.valueOf(c.getYear())};
        return res;
    }

    @Test
    public void verifyCarEqualityWithMakeModelYear() {
        Car firstCar = new Car("abc", "tesla", 9000, 2010, 1500f);
        Car secondCar = new Car("abc", "tesla", 7000, 2010, 2000f);
        String[] expected = {"abc", "tesla", "2010"};
        assertArrayEquals(expected, CarToStringArray(firstCar));
        assertArrayEquals(CarToStringArray(firstCar), CarToStringArray(secondCar));
    }
}
