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
        return this;
    }

    public double getValue(int scale) {
        long denominator = Math.round(Math.pow(10, scale));
        double epsilon = Math.pow(10, -1 * scale * 2);
        int multiplier = currentValue > 0.0f ? 1 : -1;
        double floor = Math.floor(Math.abs(currentValue) * denominator + 0.5 + epsilon);
        if (floor == 0.0) {
            return floor;
        } else {
            return (multiplier * floor) / denominator;
        }
    }

    public double getValue() {
        return currentValue;
    }

}
