package com.example;

public interface CalcSpec {

    CalcSpec add(double value);

    CalcSpec subtract(double value);

    CalcSpec multiply(double value);

    CalcSpec divide(double value, int precision);

    double getValue(int precision);

}
