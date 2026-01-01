package com.aeither.store.calculator.core.impl;

import com.aeither.store.calculator.core.CalculatorOperation;
import org.springframework.stereotype.Component;

@Component
public class Addition implements CalculatorOperation {
    @Override
    public String getSymbol() {
        return "plus";
    }

    @Override
    public double apply(double a, double b) {
        return a + b;
    }
}

@Component
class Subtraction implements CalculatorOperation {
    @Override
    public String getSymbol() {
        return "minus";
    }

    @Override
    public double apply(double a, double b) {
        return a - b;
    }
}

@Component
class Multiplication implements CalculatorOperation {
    @Override
    public String getSymbol() {
        return "multiply";
    }

    @Override
    public double apply(double a, double b) {
        return a * b;
    }
}

@Component
class Division implements CalculatorOperation {
    @Override
    public String getSymbol() {
        return "divide";
    }

    @Override
    public double apply(double a, double b) {
        if (b == 0)
            throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}
