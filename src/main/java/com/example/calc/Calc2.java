package com.example.calc;

public class Calc2 {
    private double currentValue;

    public Calc2(double currentValue) {
        this.currentValue = cleanDouble(currentValue);
    }

    public static Calc2 init(double value) {
        return new Calc2(value);
    }

    public Calc2 add(double value) {
        currentValue += cleanDouble(value);
        return this;
    }

    public Calc2 subtract(double value) {
        currentValue -= cleanDouble(value);
        return this;
    }

    public Calc2 multiply(double value) {
        currentValue *= cleanDouble(value);
        return this;
    }

    public Calc2 divide(double value) {
        double tempVal = currentValue /= cleanDouble(value);
        currentValue = cleanDouble(tempVal);
        return this;
    }

    public double getValue() {
        return cleanDouble(currentValue);
    }

    public double cleanDouble(double val) {
        return Math.round(val * 10_000) / 10_000.0;
    }


}
