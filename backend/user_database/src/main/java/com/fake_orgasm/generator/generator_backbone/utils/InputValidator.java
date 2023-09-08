package com.fake_orgasm.generator.generator_backbone.utils;

import java.util.Scanner;

/**
 * This is a utility class for input validation.
 */
public class InputValidator {
    private Scanner scanner;

    /**
     * This is the constructor method.
     */
    public InputValidator() {
        scanner = new Scanner(System.in);
    }

    /**
     * This method validates and returns an integer input within a specified range.
     *
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The validated input.
     */
    public int validateNumber(int min, int max) {
        int input;
        do {
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);

        return input;
    }

    /**
     * This method closes the Scanner used for input.
     */
    public void close() {
        scanner.close();
    }
}
