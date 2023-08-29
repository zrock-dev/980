package com.fake_orgasm.generator.generator_backbone;

import com.fake_orgasm.generator.UserGenerator;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.users_management.models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneratorBackbone {
    //private UserGenerator userGenerator;
    private FlightHistoryGenerator flightHistoryGenerator;

    public GeneratorBackbone() {
      //  userGenerator = new UserGenerator();
        flightHistoryGenerator = FlightHistoryGenerator.getInstance();
    }

    public void generateUsers(int numUsers) {
        /**
        ExecutorService userExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < numUsers; i++) {
            userExecutor.execute(() -> {
                User user = userGenerator.make();
                printUser(user);
            });
        }

        userExecutor.shutdown();
        try {
            userExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
         */

        System.out.println("User data generation completed.");
    }

    public void generateFlightHistories(int numHistories) {
        ExecutorService flightExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < numHistories; i++) {
            flightExecutor.execute(() -> {
                FlightHistory histories = flightHistoryGenerator.generateRandomFlightHistory();
                printFlightHistories(histories);
            });
        }

        flightExecutor.shutdown();
        try {
            flightExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Flight history generation completed.");
    }

    private synchronized void printUser(User user) {
        System.out.println("Generated user: " + user.toString());
    }

    private synchronized void printFlightHistories(FlightHistory histories) {
        System.out.println("Generated flight history: " + histories.toString());
    }
}
