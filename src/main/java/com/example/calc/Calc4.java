package com.example.calc;

public class Calc4 {

    private double currentValue;
    private final int precision = 10_000;
    private final int overflow = 10_0000;

    public Calc4(double currentValue) {
        this.currentValue = cleanDouble(currentValue);
    }

    public static Calc4 init(double value) {
        return new Calc4(value);
    }

    public Calc4 add(double value) {
        currentValue += cleanDouble(value);
        return this;
    }

    public Calc4 subtract(double value) {
        currentValue -= cleanDouble(value);
        return this;
    }

    public Calc4 multiply(double value) {
        currentValue *= cleanDouble(value);
        return this;
    }

    public Calc4 divide(double value) {
        currentValue = currentValue / value;
        return this;
    }

    public double getValue() {
        double val1 = Math.floor(
                ((Math.round(currentValue * precision * overflow) * 1.0) / overflow) + 0.5
        );

        return (Math.round(val1 * 1.0) * 1.0) / precision;
    }

    public double cleanDouble(double val) {
        return val;
    }


}
