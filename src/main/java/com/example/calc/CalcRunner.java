package com.example.calc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CalcRunner implements Runnable {

    Random random = new Random();
    int count1 = 0;
    int count2 = 0;
    int gap1Total = 0;
    int gap2Total = 0;
    int total = 0;
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    Counter counter1;
    Counter counter2;

    public CalcRunner(MeterRegistry meterRegistry) {
        this.timer1 = meterRegistry.timer("calc1-elapsed-time", "name", "calc1");;
        this.timer2 = meterRegistry.timer("bigd-elapsed-time", "name", "bigd");;
        this.timer3 = meterRegistry.timer("calc2-elapsed-time", "name", "calc2");;


        counter1 = meterRegistry.counter("calc1-error", "name", "calc1");
        counter2 = meterRegistry.counter("calc2-error", "name", "calc2");
    }

    @Override
    public void run() {

            double value1 = cleanDouble(zeroSafe(random.nextDouble(), random));
            double value2 = cleanDouble(zeroSafe(random.nextDouble(), random));
            double value3 = cleanDouble(zeroSafe(random.nextDouble(), random));
            double value4 = cleanDouble(zeroSafe(random.nextDouble(), random));


            long gap2Start = System.currentTimeMillis();
            double doubleVal2 = doubleToBigD(value1)
                    .multiply(doubleToBigD(value2))
                    .multiply(doubleToBigD(value3))
                    .multiply(doubleToBigD(value4))
                    .setScale(4, RoundingMode.HALF_UP)
                    .doubleValue();

            long gap2End = System.currentTimeMillis();

        long gap3Start = System.currentTimeMillis();
        Calc3 calc3 = Calc3.init(value1)
                .multiply(value2)
                .multiply(value3)
                .multiply(value4);

        double doubleVal3 = calc3
                .getValue();
        long gap3End = System.currentTimeMillis();


            long gap2 = gap2End - gap2Start;
            long gap3 = gap3End - gap3Start;

            total++;


            timer2.record(gap2, TimeUnit.MILLISECONDS);
            timer3.record(gap3, TimeUnit.MILLISECONDS);

            if (Double.compare(doubleVal3, doubleVal2) != 0) {
                counter2.increment();
                System.out.println("Error value clac3 = " + value3 + " bigD = " + value2);
                System.out.println(calc3.getOp());
            }
    }

    public static double zeroSafe(double value, Random random) {
        if (Double.compare(value, 0.0f) == 0) {
            return zeroSafe(random.nextDouble(), random);
        } else {
            return value;
        }
    }

    public static double cleanDouble(double value) {
        return (double) Math.round(value * 10_000) / 10_000;
    }

    public static BigDecimal doubleToBigD(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
    }
}