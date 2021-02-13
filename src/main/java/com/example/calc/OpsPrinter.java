package com.example.calc;

import com.example.CalcSpec;

public class OpsPrinter implements CalcSpec {

    private final Calc calc;
    private String ops;

    public OpsPrinter(Calc calc, int precision) {
        this.calc = calc;
        ops = String.valueOf(calc.getValue(precision));
    }

    @Override
    public CalcSpec add(double value) {
        ops = "(" + ops + ") + " + value;
        calc.add(value);
        return this;
    }

    @Override
    public CalcSpec subtract(double value) {
        ops = "(" + ops + ") - " + value;
        calc.subtract(value);
        return this;
    }

    @Override
    public CalcSpec multiply(double value) {
        ops = "(" + ops + ") * " + value;
        calc.multiply(value);
        return this;
    }

    @Override
    public CalcSpec divide(double value, int precision) {
        ops = "(" + ops + ") / " + value;
        calc.divide(value, precision);
        return this;
    }

    @Override
    public double getValue(int precision) {
        return calc.getValue(precision);
    }

    @Override
    public double getValue() {
        return calc.getValue();
    }

    @Override
    public String toString() {
        return ops;
    }
}
