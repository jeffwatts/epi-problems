package org.jrw;

public class BitTwiddler {

  public int bitCount(long input) {
    int oneCount = 0;
    while (input != 0) {
      long rightmostOne = input & ~(input - 1);
      input ^= rightmostOne;
      oneCount++;
    }
    return oneCount;
  }

  public long remainderModPowerOfTwo(long dividend, long powerOfTwo) {
    return dividend & (powerOfTwo - 1);
  }

  public boolean isPowerOfTwo(long input) {
    return input > 0 && bitCount(input) == 1;
  }

  public long rightPropagateOnes(long input) {
    long rightmostOne = input & ~(input - 1);
    long rightPropagated = rightmostOne - 1;
    if (rightPropagated < 0) {
      return 0;
    }
    return input | rightPropagated;
  }
}
