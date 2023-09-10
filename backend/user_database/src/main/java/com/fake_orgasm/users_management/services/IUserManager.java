package com.fake_orgasm.users_management.services;

import com.fake_orgasm.users_management.models.User;

/**
 * Defines the contract for managing user-related operations.
 */
public interface IUserManager {

    /**
     * Searches for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @param page The page number from which to retrieve users.
     * @return A list of User objects matching the search criteria.
     */
    Page search(String name, int page);

    /**
     * Creates a new user.
     *
     * @param user The User object representing the user to be created.
     * @return True if the user creation was successful, false otherwise.
     */
    boolean create(User user);

    /**
     * Deletes a user.
     *
     * @param user The User object representing the user to be deleted.
     */
    void delete(User user);

    /**
     * Retrieves information about a specific user.
     *
     * @param user The User object representing the user to be retrieved.
     * @return The User object containing the user's information, or null if not found.
     */
    User getUser(User user);

    /**
     * Updates a user's information.
     *
     * @param user       The User object representing the user
     *                   whose information will be updated.
     * @param updateUser The User object containing the new data to update.
     * @return True if the user's information was successfully updated,
     * false otherwise.
     */
    boolean update(User user, User updateUser);

    /**
     * Retrieves a list of users from a specified page number.
     *
     * @param page The page number from which to retrieve users.
     * @return A list of User objects from the specified page.
     */
    Page getUsersByPage(int page);
}
