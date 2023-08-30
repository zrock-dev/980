package com.fake_orgasm.generator.generator_backbone;

import com.fake_orgasm.generator.generator_backbone.utils.InputValidator;

/**
 * This the GeneratorUI class.
 */
public class GeneratorUI {
    private InputValidator inputValidator;
    private GeneratorBackbone generatorBackbone;

    /**
     * This method is the class constructor.
     */
    public GeneratorUI() {
        inputValidator = new InputValidator();
        generatorBackbone = new GeneratorBackbone();
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
        System.out.println("0. Exit");
    }

    /**
     * This method gets the user's choice from the menu.
     *
     * @return The user's choice.
     */
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        return inputValidator.validateNumber(0, 1);
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
                generatorBackbone.generateUsers(numUsers);
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("...");
        }
    }
}
