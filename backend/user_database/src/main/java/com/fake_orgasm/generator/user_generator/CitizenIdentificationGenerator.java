package com.fake_orgasm.generator.user_generator;

/**
 * The CitizenIdentificationGenerator class generates unique citizen identification numbers.
 * It maintains a counter to keep track of generated identification numbers.
 */
public class CitizenIdentificationGenerator {
    private int citizenIdentificationCounter;

    /**
     * Constructs a CitizenIdentificationGenerator object with an initial counter value of 0.
     */
    protected CitizenIdentificationGenerator() {
        citizenIdentificationCounter = 0;
    }

    /**
     * Generates a new citizen identification number.
     *
     * @return A unique citizen identification number.
     */
    protected int make() {
        citizenIdentificationCounter++;
        return citizenIdentificationCounter;
    }
}
