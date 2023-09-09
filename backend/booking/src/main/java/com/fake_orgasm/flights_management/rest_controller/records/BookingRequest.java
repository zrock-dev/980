package com.fake_orgasm.flights_management.rest_controller.records;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.users_management.models.User;

/**
 * Represents a booking request in a flights management system.
 */

public record BookingRequest(User user, String flightId, Category category) {
    /**
     * Retrieves the user.
     *
     * @return the user object
     */
    @Override
    public User user() {
        return user;
    }

    /**
     * Retrieves the flight ID.
     *
     * @return The flight ID.
     */

    @Override
    public String flightId() {
        return flightId;
    }

    /**
     * Retrieves the category of the Java function.
     *
     * @return the category of the Java function
     */

    @Override
    public Category category() {
        return category;
    }
}
