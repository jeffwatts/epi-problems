package org.jrw;

public class ParityChecker {
    private static final int MASK = 0xFFFF;
    private static final int[] LOOKUP_TABLE = new int[0x10000];

    static {
        // populate lookup table
        for (int i = 0; i < LOOKUP_TABLE.length; i++) {
            LOOKUP_TABLE[i] = bitCount(i);
        }
    }

    /**
     * This implements exercise 5.1 (bit parity calculation).
     * Since all Java primitives are signed, the tests and primitive types
     * used in the implementation differ from the C++ solution in the book.
     */
    public static byte getParity(long input) {
        int totalCount = LOOKUP_TABLE[(int) input & MASK];
        totalCount += LOOKUP_TABLE[(int) (input >> 16) & MASK];
        totalCount += LOOKUP_TABLE[(int) (input >> 32) & MASK];
        totalCount += LOOKUP_TABLE[(int) (input >> 48) & MASK];
        return totalCount % 2 == 0 ? (byte) 0 : (byte) 1;
    }

    private static int bitCount(long input) {
        int oneCount = 0;
        while (input != 0) {
            long rightmostOne = input & ~(input - 1);
            input ^= rightmostOne;
            oneCount++;
        }
        return oneCount;
    }
}
