package edu.umb.cs680.hw01;

import java.util.LinkedList;

public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();

    public PrimeGenerator(Long from, Long to) {
        if (from > 0 && from <= to) {
            this.from = from;
            this.to = to;
        } else {
            throw new RuntimeException("invalid input values");
        }
    }

    public static boolean isPrime(Long n) {
        if (n < 2) {
            return false;
        } else {
            for (long i = 2L; i * i <= n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
    public void generatePrimes() {
        for (long i = this.from; i <= this.to; i++) {
            if (isPrime(i)) {
                this.primes.add(i);
            }
        }
    }

    public LinkedList<Long> getPrimes(){
        return this.primes;
    };
}
