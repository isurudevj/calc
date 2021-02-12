package com.example;

import com.example.calc.CalcRunner;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AppA implements CommandLineRunner {

    private final MeterRegistry meterRegistry;

    public AppA(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(AppA.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        CalcRunner calcRunner = new CalcRunner(meterRegistry);
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(calcRunner, 1, 1, TimeUnit.MILLISECONDS);
    }
}
