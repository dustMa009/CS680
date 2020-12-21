package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PrimeGeneratorTest {

    @Test
    public void primeGenerateFrom1to12(){
        PrimeGenerator a = new PrimeGenerator(10L ,25L);
        Long[] expectedPrimes = {11L, 13L, 17L, 19L, 23L};
        a.generatePrimes();
        Long[] actualPrimes = a.getPrimes().toArray(new Long[0]);
        assertArrayEquals(expectedPrimes, actualPrimes);
    }
    
    @Test
    public void primeGenerateFromN1To1TryCatch(){
        try{
            PrimeGenerator cut = new PrimeGenerator(-1L ,1L);
            fail("invalid input values");
        }
        catch (RuntimeException ex){
            assertEquals("invalid input values", ex.getMessage());
        }
    }
}
