package com.example.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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

    @ParameterizedTest
    @CsvFileSource(resources = {
            "/samples/multiply_errors.csv"
    })
    //@CsvSource(value = "0.0993, 0.5845, 0.0417, 0.5991, 0.0015")
    void multiply2(double value1, double value2, double value3, double value4, double answer) {
        Calc3 calc3 = Calc3.init(value1)
                .multiply(value2)
                .multiply(value3)
                .multiply(value4);

        double doubleVal3 = calc3
                .getValue();

        System.out.println(calc3.getOp());

        Assertions.assertEquals(answer, doubleVal3);
    }
}