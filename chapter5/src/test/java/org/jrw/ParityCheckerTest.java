package org.jrw;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParityCheckerTest {
    private static final long SIXTEEN_BITS = 0xFFFF;
    private static final long TWELVE_BITS = 0xFFF;
    private static final long FIFTEEN_BITS = 0x7FFF;

    @Test
    public void oddNumberOfBits() {
        long[] inputs = new long[] {
                1, 22, 0x1FFF,
                (FIFTEEN_BITS << 48) | (FIFTEEN_BITS << 32) | FIFTEEN_BITS << 16 | 3
        };

        for (long input : inputs) {
            assertEquals("Expected 1 but was 0 for input " + input,
                    1, ParityChecker.getParity(input));
        }
    }

    @Test
    public void evenNumberOfBits() {
        long[] inputs = new long[] {
                0, 3, TWELVE_BITS,
                SIXTEEN_BITS, SIXTEEN_BITS << 16, SIXTEEN_BITS << 32, TWELVE_BITS << 48,
                (TWELVE_BITS << 48) | (SIXTEEN_BITS << 32) | (SIXTEEN_BITS << 16) | SIXTEEN_BITS,
                (1L << 61) | (4L << 32),
                (3L << 60) | (5L << 33) | 23L
        };

        for (long input : inputs) {
            assertEquals("Expected 0 but was 1 for input " + input,
                    0, ParityChecker.getParity(input));
        }
    }
}