package com.fake_orgasm.flights_management.services.records;

import com.fake_orgasm.users_management.models.User;

/**
 * This class is a record for the User class.
 *
 * @param oldData the old data for the User.
 * @param newData the new  data for the User.
 */
public record UpdateRequest(User oldData, User newData) {
    /**
     * Returns the old data for the User.
     *
     * @return the old data for the User
     */
    @Override
    public User oldData() {
        return oldData;
    }

    /**
     * Generates a new instance of the User class.
     *
     * @return an instance of the User class
     */
    @Override
    public User newData() {
        return newData;
    }
}