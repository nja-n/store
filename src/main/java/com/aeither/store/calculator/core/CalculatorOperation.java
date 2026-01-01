package com.aeither.store.calculator.core;

public interface CalculatorOperation {
    String getSymbol();

    double apply(double a, double b);
}
