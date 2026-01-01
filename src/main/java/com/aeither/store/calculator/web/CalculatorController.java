package com.aeither.store.calculator.web;

import com.aeither.store.calculator.application.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calculator")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping
    public String index() {
        return "calculator";
    }

    @PostMapping("/calculate")
    @ResponseBody
    public String calculate(@RequestParam double a, @RequestParam double b, @RequestParam String op) {
        try {
            return String.valueOf(calculatorService.calculate(a, b, op));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
