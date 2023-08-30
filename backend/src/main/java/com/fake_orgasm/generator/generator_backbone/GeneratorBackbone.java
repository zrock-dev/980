package com.fake_orgasm.generator.generator_backbone;

import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The GeneratorBackbone class is in charge of the generation of user data.
 *
 */
public class GeneratorBackbone {
    private UserGenerator userGenerator;
    private FlightHistoryGenerator flightHistoryGenerator;

    /**
     * This method constructs a new GeneratorBackbone instance.
     *
     */
    public GeneratorBackbone() {
        userGenerator = new UserGenerator();
        flightHistoryGenerator = FlightHistoryGenerator.getInstance();
    }

    /**
     * This method generates user data based on the specified number of users.
     *
     * @param numUsers The number of users to generate.
     */
    public void generateUsers(int numUsers) {
        ExecutorService userExecutor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
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

    /**
     * This method prints user information to the console.
     *
     * @param user The user to be printed.
     */
    private synchronized void printUser(User user) {
        System.out.println("Generated user: " + user.toString());
    }
}
