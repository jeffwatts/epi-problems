package org.jrw;

import java.util.EmptyStackException;
import java.util.Stack;

public class RpnCalculator {
    private static final String NUMBER = "^-?\\d+(\\.\\d+)?$";

    /**
     * This solves problem 9.2, which evaluates the given comma-separated string as a
     * reverse-polish notation (RPN) expression
     *
     * @param rpnExpression a comma-separated list of numbers or arithmetic operations (+,-,*,/),
     *                      to be evaluated as a RPN expression, evaluated as if entered
     *                      left-to-right
     * @return the result of evaluating the passed in RPN expression (at the top of the
     *                      current eval stack)
     */
    public static double evaluate(String rpnExpression) {
        String[] parts = rpnExpression.split(",");
        Stack<Double> elements = new Stack<>();
        for (String element : parts) {
            if (element.matches(NUMBER)) {
                Double number = Double.valueOf(element);
                elements.push(number);
            } else {
                // the element is not a number, and therefore should be an operator
                Operator operator = createOperation(element);
                try {
                    Double secondArg = elements.pop();
                    Double firstArg = elements.pop();
                    elements.push(operator.evaluate(firstArg, secondArg));
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("invalid argument sequence", e);
                }
            }
        }
        return elements.pop();
    }

    private static Operator createOperation(String operationString) {
        switch (operationString) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            case "/":
                return new Division();
            default:
                throw new IllegalArgumentException("unknown operator: " + operationString);
        }
    }

    private static interface Operator {
        double evaluate(double firstArg, double secondArg);
    }

    private static class Addition implements Operator {
        @Override
        public double evaluate(double firstArg, double secondArg) {
            return firstArg + secondArg;
        }
    }

    private static class Subtraction implements Operator {
        @Override
        public double evaluate(double firstArg, double secondArg) {
            return firstArg - secondArg;
        }
    }

    private static class Multiplication implements Operator {
        @Override
        public double evaluate(double firstArg, double secondArg) {
            return firstArg * secondArg;
        }
    }

    private static class Division implements Operator {
        @Override
        public double evaluate(double firstArg, double secondArg) {
            return firstArg / secondArg;
        }
    }
}
