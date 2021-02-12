package com.example.calc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CalcRunner implements Runnable {

    Random random = new Random();
    int total = 0;
    private Timer timer1;
    private Timer timer2;

    Counter counter1;
    Counter counter2;

    public CalcRunner(MeterRegistry meterRegistry) {

        this.timer1 = meterRegistry.timer("bigd-elapsed-time", "name", "bigd");

        this.timer2 = meterRegistry.timer("calc-elapsed-time", "name", "calc");

        counter1 = meterRegistry.counter("calc-error", "name", "calc");
    }

    @Override
    public void run() {
        int scale = 4;

        double value1 = cleanDouble(zeroSafe(random.nextDouble(), random), scale);
        double value2 = cleanDouble(zeroSafe(random.nextDouble(), random), scale);
        double value3 = cleanDouble(zeroSafe(random.nextDouble(), random), scale);
        double value4 = cleanDouble(zeroSafe(random.nextDouble(), random), scale);
        double value5 = cleanDouble(zeroSafe(random.nextDouble(), random), scale);


        long gap1Start = System.currentTimeMillis();

        double bigDAnswer = doubleToBigD(value1, scale)
                .multiply(doubleToBigD(value2, scale))
                .divide(doubleToBigD(value1, scale), new MathContext(scale, RoundingMode.HALF_UP))
                .subtract(doubleToBigD(value4, scale))
                .multiply(doubleToBigD(value3, scale))
                .divide(doubleToBigD(value5, scale), new MathContext(scale, RoundingMode.HALF_UP))
                .add(doubleToBigD(value2, scale))
                .multiply(doubleToBigD(value4, scale))
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();

        long gap1End = System.currentTimeMillis();

        long gap2Start = System.currentTimeMillis();

        Calc calc = Calc.init(value1)
                .multiply(value2)
                .divide(value1, scale)
                .subtract(value4)
                .multiply(value3)
                .divide(value5, scale)
                .add(value2)
                .multiply(value4);

        double calcAnswer = calc.getValue(scale);

        long gap2End = System.currentTimeMillis();


        long gap1 = gap1End - gap1Start;
        long gap2 = gap2End - gap2Start;

        total++;

        timer1.record(gap1, TimeUnit.MILLISECONDS);
        timer2.record(gap2, TimeUnit.MILLISECONDS);

        if (Double.compare(calcAnswer, bigDAnswer) != 0) {
            counter1.increment();

            System.out.println(String.format("%s, %s, %s, %s, %s, %s",
                    doubleToBigD(value1, scale),
                    doubleToBigD(value2, scale),
                    doubleToBigD(value3, scale),
                    doubleToBigD(value4, scale),
                    doubleToBigD(value5, scale),
                    bigDAnswer
            ));
        }

    }

    public static double zeroSafe(double value, Random random) {
        if (Double.compare(value, 0.0f) == 0) {
            return zeroSafe(random.nextDouble(), random);
        } else {
            return value;
        }
    }

    public static double cleanDouble(double value, int precision) {
        long denominator = Math.round(Math.pow(10, precision));
        return (double) Math.round(value * denominator) / denominator;
    }

    public static BigDecimal doubleToBigD(double value, int scale) {
        return new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP);
    }
}
