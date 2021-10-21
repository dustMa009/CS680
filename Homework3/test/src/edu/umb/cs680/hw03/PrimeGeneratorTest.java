package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PrimeGeneratorTest {
    @Test
    public void primes10to30(){
        PrimeGenerator gen = new PrimeGenerator(10, 30);
        gen.generatePrimes();
        Long[] expectedPrimes = {11L, 13L, 17L, 19L, 23L, 29L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }
    @Test
    public void primes32to36() {
        PrimeGenerator gen = new PrimeGenerator(32, 36);
        gen.generatePrimes();
        Long[] expectedPrimes = {};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }
    @Test
    public void primes50to100() {
        PrimeGenerator gen = new PrimeGenerator(50, 100);
        gen.generatePrimes();
        Long[] expectedPrimes = {53L, 59L, 61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L};
        assertArrayEquals(expectedPrimes, gen.getPrimes().toArray());
    }
    @Test
    public void primesMinus10to10() {
        try {
            PrimeGenerator gen = new PrimeGenerator(-10, 10);
            fail("Negative inputs.");
        } catch (RuntimeException e) {
            assertEquals("Wrong input values: from=-10 to=10", e.getMessage());
        }
    }
    @Test
    public void primes100to1() {
        try {
            PrimeGenerator gen = new PrimeGenerator(100, 1);
            fail("to is bigger than from.");
        } catch (RuntimeException e) {
            assertEquals("Wrong input values: from=100 to=1", e.getMessage());
        }
    }
}
