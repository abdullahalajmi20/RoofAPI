package com.store.utilities;

import java.util.Random;

/**
 *
 * @author Abdullah
 */
public class RandomString {
    private static final char[] symbols = new char[36];
    private static final char[] numbers = new char[10];
    
    static {
      for (int idx = 0; idx < 10; ++idx)
        symbols[idx] = (char) ('0' + idx);
      for (int idx = 10; idx < 36; ++idx)
        symbols[idx] = (char) ('a' + idx - 10);
      
      for (int idx = 0; idx < 10; ++idx)
        numbers[idx] = (char) ('0' + idx);
      
    }

    private final Random random = new Random();

    private final char[] buf;

    /**
     *
     * @param length
     */
    public RandomString(int length) {
      if (length < 1)
        throw new IllegalArgumentException("length < 1: " + length);
      buf = new char[length];
    }

    /**
     *
     * @return
     */
    public String nextString() {
      for (int idx = 0; idx < buf.length; ++idx) 
        buf[idx] = symbols[random.nextInt(symbols.length)];
      return new String(buf);
    }
    
    /**
     *
     * @return
     */
    public String nextInteger() {
      for (int idx = 0; idx < buf.length; ++idx) 
        buf[idx] = numbers[random.nextInt(numbers.length)];
      return new String(buf);
    }
    
	  
}
