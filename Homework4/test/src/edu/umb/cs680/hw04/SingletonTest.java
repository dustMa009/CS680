package edu.umb.cs680.hw04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SingletonTest {
    @Test
    public void getNonNullInstance() {
        Singleton x = Singleton.getInstance();
        assertNotNull(x);
    }

    @Test
    public void verifyTwoSingletonInstances() {
        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();
        assertEquals(x.hashCode(), y.hashCode());
        assertEquals(x, y);
        assertSame(x, y);
    }
}