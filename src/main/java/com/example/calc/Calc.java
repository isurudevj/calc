package com.example.calc;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calc {

    private BigDecimal currentValue;

    public Calc(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public static Calc init(double value) {
        return new Calc(new BigDecimal(String.valueOf(value)));
    }

    public Calc add(double value) {
        currentValue = new BigDecimal(String.valueOf(value)).add(currentValue);
        return this;
    }

    public Calc subtract(double value) {
        currentValue = currentValue.subtract(new BigDecimal(String.valueOf(value)));
        return this;
    }

    public Calc multiply(double value) {
        currentValue = new BigDecimal(String.valueOf(value)).multiply(currentValue);
        return this;
    }

    public Calc divide(double value) {
        currentValue = new BigDecimal(String.valueOf(currentValue))
                .divide(new BigDecimal(String.valueOf(value)), 4, RoundingMode.HALF_UP);
        return this;
    }

    public double getValue() {
        return Double.parseDouble(currentValue.setScale(4, RoundingMode.HALF_UP).toString());
    }

}
