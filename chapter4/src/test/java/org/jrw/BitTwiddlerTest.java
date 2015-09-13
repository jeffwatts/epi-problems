package org.jrw;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class BitTwiddlerTest {
  private BitTwiddler underTest;

  @Before
  public void setUp() {
    underTest = new BitTwiddler();
  }

  @Test
  public void testBitCount() {
    assertEquals(0, underTest.bitCount(0));
    assertEquals(1, underTest.bitCount(1));

    // 127 = 01111111
    assertEquals(7, underTest.bitCount(127));

    assertEquals(31, underTest.bitCount(Integer.MAX_VALUE));

    assertEquals(1, underTest.bitCount(Integer.MAX_VALUE + 1L));

    assertEquals(63, underTest.bitCount(Long.MAX_VALUE));
  }

  @Test
  public void testRemainderModPowerOfTwo() {
    assertEquals(0L, underTest.remainderModPowerOfTwo(28, 2));
    assertEquals(9L, underTest.remainderModPowerOfTwo(73, 64));
    assertEquals(9L, underTest.remainderModPowerOfTwo(73, 16));

    long power = 2L * 2L * 2L * 2L * 2L * 2L * 2L * 2L * 2L * 2L;
    assertEquals(Integer.MAX_VALUE % power, underTest.remainderModPowerOfTwo(Integer.MAX_VALUE, power));
  }

  @Test
  public void testRightPropagateOnes() {
    assertEquals(0, underTest.rightPropagateOnes(0));
    assertEquals(1, underTest.rightPropagateOnes(1));
    assertEquals(255, underTest.rightPropagateOnes(128));
    assertEquals(((long) Integer.MAX_VALUE) + Integer.MAX_VALUE + 1L, underTest.rightPropagateOnes(Integer.MAX_VALUE + 1L));

    // 40 = 00101000 -> 00101111 = 47
    assertEquals(47, underTest.rightPropagateOnes(40));
  }

  @Test
  public void testIsPowerOfTwo() {
    assertTrue(underTest.isPowerOfTwo(1));
    assertTrue(underTest.isPowerOfTwo(256));
    assertTrue(underTest.isPowerOfTwo(2L * (Integer.MAX_VALUE + 1L)));

    assertFalse(underTest.isPowerOfTwo(0));
    assertFalse(underTest.isPowerOfTwo(3));
    assertFalse(underTest.isPowerOfTwo(Long.MAX_VALUE));
  }
}
