package com.fake_orgasm.generator.generator_backbone;

import com.fake_orgasm.generator.generator_backbone.utils.InputValidator;

/**
 * This the GeneratorUI class.
 */
public class GeneratorUI {
    private InputValidator inputValidator;

    /**
     * This method is the class constructor.
     */
    public GeneratorUI() {
        inputValidator = new InputValidator();
    }

    /**
     * This method starts the user interface for generator functionality.
     */
    public void start() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            handleUserChoice(choice);
        } while (choice != 0);
        inputValidator.close();
    }

    /**
     * This method displays the main menu.
     */
    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Generate user data");
        System.out.println("2. Generate flight histories");
        System.out.println("3. View user data");
        System.out.println("4. View flight histories");
        System.out.println("0. Exit");
    }

    /**
     * This method gets the user's choice from the menu.
     *
     * @return The user's choice.
     */
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        return inputValidator.validateNumber(0, 4);
    }

    /**
     * This method handles the user's menu choice and performs corresponding actions.
     *
     * @param choice The user's choice.
     */
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                int numUsers = inputValidator.validateNumber(1, 1000000);
                System.out.println("Generating " + numUsers + " user data...");
                break;
            case 2:
                int numHistories = inputValidator.validateNumber(1, 1000000);
                System.out.println("Generating " + numHistories + " flight histories...");
                break;
            case 3:
                System.out.println("Charging user data...");
                break;
            case 4:
                System.out.println("Charging flights...");
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("...");
        }
    }
}
