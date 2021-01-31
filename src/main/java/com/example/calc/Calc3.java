package com.example.calc;

public class Calc3 {

    private static long PRECISION = 10_000;
    private static long OVERFLOW = 1000;

    private long currentValue;
    private String op;

    private Calc3(double currentValue) {
        this.currentValue = convertLong(currentValue);
        op = "" + currentValue;
    }

    public static Calc3 init(double value) {
        return new Calc3(value);
    }

    public Calc3 add(double value) {
        currentValue += convertLong(value);
        op += " + " + value;
        return this;
    }

    public Calc3 subtract(double value) {
        currentValue -= convertLong(value);
        op += " - " + value;
        return this;
    }

    public Calc3 multiply(double value) {
        currentValue = Math.round(Math.floor(currentValue * value));
        op = "((" + op + ")" + " * " + value + ")";
        return this;
    }

    public Calc3 divide(double value) {
        long base = convertLong(value);
        double interim = (currentValue * 1.0) / base;
        currentValue = convertLong(interim);
        op = "((" + op + ")" + " / " + value + ")";
        return this;
    }

    public double getValue() {
        return (Math.round((currentValue * 1.0) / OVERFLOW)) * 1.0 / PRECISION;
    }

    public String getOp() {
        return op;
    }

    public long convertLong(double val) {
        return Math.round(val * PRECISION * OVERFLOW);
    }

    public long convertLong(double val, int multiplier) {
        return Math.round(val * multiplier);
    }

}
