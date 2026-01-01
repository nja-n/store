package com.aeither.store.calculator.application;

import com.aeither.store.calculator.core.CalculatorOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final List<CalculatorOperation> operations;

    public double calculate(double a, double b, String operatorSymbol) {
        return operations.stream()
                .filter(op -> op.getSymbol().equals(operatorSymbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported operator: " + operatorSymbol))
                .apply(a, b);
    }
}
