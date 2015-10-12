package org.jrw;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RpnCalculatorTest {
    @Test
    public void problem_9_2_validSimpleString() {
        String expr = "9,2,+";
        assertEquals(11, (int) RpnCalculator.evaluate(expr));
    }

    @Test
    public void problem_9_2_validComplexString() {
        String expr = "9,2,+,3,*,4,-1,-,*";
        assertEquals(165, (int) RpnCalculator.evaluate(expr));
    }

    @Test
    public void problem_9_2_validWithDecimals() {
        String expr = "9.3,2.1,-";
        assertEquals(7.2, RpnCalculator.evaluate(expr), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void problem_9_2_invalidOperator() {
        String invalidExpr = "2,2,#";
        RpnCalculator.evaluate(invalidExpr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void problem_9_2_invalidSequence() {
        String invalidExpr = "2,-";
        RpnCalculator.evaluate(invalidExpr);
    }
}
