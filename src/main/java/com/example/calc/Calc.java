package com.example.calc;

import com.example.CalcSpec;

public class Calc implements CalcSpec {

    private double currentValue;

    private Calc(double currentValue) {
        this.currentValue = currentValue;
    }

    public static Calc init(double value) {
        return new Calc(value);
    }

    public Calc add(double value) {
        currentValue += value;
        return this;
    }

    public Calc subtract(double value) {
        currentValue -= value;
        return this;
    }

    public Calc multiply(double value) {
        currentValue *= value;
        return this;
    }

    public Calc divide(double value, int precision) {
        currentValue = currentValue / value;
        currentValue = getValue(precision);
        return this;
    }

    public double getValue(int precision) {
        long denominator = Math.round(Math.pow(10, precision));
        return Math.floor(currentValue * denominator + 0.5) / denominator;
    }

}
