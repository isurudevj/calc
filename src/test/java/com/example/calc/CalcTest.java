package com.example.calc;

import com.example.CalcSpec;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


@Slf4j
class CalcTest {

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 1.3579"
    })
    void add(double value1, double value2, double answer) {
        Assertions.assertEquals(Calc.init(value1).add(value2).getValue(4), answer);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 0.8889"
    })
    void subtract(double value1, double value2, double answer) {
        Assertions.assertEquals(Calc.init(value1).subtract(value2).getValue(4), answer);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 0.2634"
    })
    void multiply(double value1, double value2, double answer) {
        int compare = Double.compare(Calc.init(value1).multiply(value2).getValue(4), answer);
        Assertions.assertEquals(0, compare);
    }

    @ParameterizedTest
    @CsvSource({
            "1.1234, 0.2345, 4.7906"
    })
    void divide(double value1, double value2, double answer) {
        int compare = Double.compare(Calc.init(value1).divide(value2, 4).getValue(4), answer);
        Assertions.assertEquals(0, compare);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {
            "/samples/multiply_errors.csv"
    })
    void multiply2(double value1, double value2, double value3, double value4, double answer) {
        Calc calc = Calc.init(value1)
                .multiply(value2)
                .subtract(value4)
                .multiply(value3)
                .add(value2)
                .multiply(value4);
        double doubleVal3 = calc
                .getValue(4);

        Assertions.assertEquals(answer, doubleVal3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {
            "/samples/divide_sample1.csv"
    })
    void divide1(double value1, double value2, double value3, double value4, double value5, double answer) {

        int scale = 4;

        CalcSpec calc = new OpsPrinter(Calc.init(value1), 4)
                .multiply(value2)
                .divide(value1, scale + 1)
                .subtract(value4)
                .multiply(value3)
                .divide(value5, scale + 1)
                .add(value2)
                .multiply(value4);

        log.info(calc.toString());
        log.info("{}", calc.getValue());
        Assertions.assertEquals(answer, calc.getValue(scale));
    }

}