package com.fake_orgasm.users_management.rest_controller.records;

import com.fake_orgasm.users_management.models.User;

/**
 * A record representing a request to update user data.
 * @param newData The new user data.
 * @param oldData The old user data.
 */
public record UpdateUserRequest(User oldData, User newData) {
    /**
     * Retrieves the old user data.
     *
     * @return The old user data.
     */
    public User getOldData() {
        return oldData;
    }

    /**
     * Retrieves the new user data.
     *
     * @return The new user data.
     */
    public User getNewData() {
        return newData;
    }
}
