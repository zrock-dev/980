package com.fake_orgasm.generator.generator_backbone;


import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneratorBackbone {
    private UserGenerator userGenerator;
    private FlightHistoryGenerator flightHistoryGenerator;

    public GeneratorBackbone() {
        userGenerator = new UserGenerator();
        flightHistoryGenerator = FlightHistoryGenerator.getInstance();
    }

    public void generateUsers(int numUsers) {
        ExecutorService userExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < numUsers; i++) {
            userExecutor.execute(() -> {
                User user = userGenerator.make();
                FlightHistory history = flightHistoryGenerator.generateRandomFlightHistory();
                user.addFlightHistory(history);

                printUser(user);
            });
        }

        userExecutor.shutdown();
        try {
            userExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("User data generation completed.");
    }


    private synchronized void printUser(User user) {
        System.out.println("Generated user: " + user.toString());
    }

}
