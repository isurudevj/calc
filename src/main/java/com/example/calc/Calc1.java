package com.example.calc;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calc1 {

    private BigDecimal currentValue;

    public Calc1(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public static Calc1 init(double value) {
        return new Calc1(new BigDecimal(String.valueOf(value)));
    }

    public Calc1 add(double value) {
        currentValue = new BigDecimal(String.valueOf(value)).add(currentValue);
        return this;
    }

    public Calc1 subtract(double value) {
        currentValue = currentValue.subtract(new BigDecimal(String.valueOf(value)));
        return this;
    }

    public Calc1 multiply(double value) {
        currentValue = new BigDecimal(String.valueOf(value)).multiply(currentValue);
        return this;
    }

    public Calc1 divide(double value) {
        currentValue = new BigDecimal(String.valueOf(currentValue))
                .divide(new BigDecimal(String.valueOf(value)), 4, RoundingMode.HALF_UP);
        return this;
    }

    public double getValue() {
        return Double.parseDouble(currentValue.setScale(4, RoundingMode.HALF_UP).toString());
    }

}
