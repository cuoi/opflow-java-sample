package com.devebot.opflow.sample.business;

import com.devebot.opflow.sample.models.FibonacciOutputItem;
import java.util.Random;

/**
 *
 * @author drupalex
 */
public class FibonacciGenerator {
    private int n;
    private int c = 0;
    private long f = 0, f_1 = 0, f_2 = 0;
    private int m;
    private int M;
    
    public FibonacciGenerator(int number) {
        this(number, 0);
    }
    
    public FibonacciGenerator(int number, int max) {
        this(number, 0, max);
    }
    
    public FibonacciGenerator(int number, int min, int max) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        if (number > 50) {
            throw new IllegalArgumentException();
        }
        this.n = number;
        this.m = min;
        this.M = max;
    }
    
    public boolean next() {
        if (0 <= this.m && this.m < this.M) {
            int d = random(this.m, this.M);
            if (d > 0) {
                try {
                    Thread.sleep(d);
                } catch(InterruptedException ie) {}
            }
        }
        if (c >= n) return false;
        if (++c < 2) {
            f = c;
        } else {
            f_2 = f_1; f_1 = f; f = f_1 + f_2;
        }
        return true;
    }
    
    public FibonacciOutputItem result() {
        return new FibonacciOutputItem(f, c, n);
    }
    
    public FibonacciOutputItem finish() {
        while(next()) {}
        return result();
    }
    
    private static final Random RANDOM = new Random();
    
    private static int random(int min, int max) {
        return RANDOM.nextInt(max + 1 - min) + min;
    }
}
