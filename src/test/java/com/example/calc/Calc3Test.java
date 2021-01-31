package com.example.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Calc3Test {

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 1.3579"
    })
    void add(double value1, double value2, double answer) {
        Assertions.assertEquals(Calc3.init(value1).add(value2).getValue(), answer);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 0.8889"
    })
    void subtract(double value1, double value2, double answer) {
        Assertions.assertEquals(Calc3.init(value1).subtract(value2).getValue(), answer);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 0.2634"
    })
    void multiply(double value1, double value2, double answer) {
        int compare = Double.compare(Calc3.init(value1).multiply(value2).getValue(), answer);
        Assertions.assertEquals(0, compare);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 4.7906"
    })
    void divide(double value1, double value2, double answer) {
        int compare = Double.compare(Calc3.init(value1).divide(value2).getValue(), answer);
        Assertions.assertEquals(0, compare);
    }
}