package com.fake_orgasm.usersmanagement.services;

import com.fake_orgasm.usersmanagement.models.User;
import com.fake_orgasm.usersmanagement.services.exceptions.IncompleteUserException;
import com.fake_orgasm.usersmanagement.services.exceptions.InvalidPageException;
import java.util.List;

/**
 * Defines the contract for managing user-related operations.
 */
public interface IUserManagement {

    /**
     * Searches for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @return A list of User objects matching the search criteria.
     */
    List<User> search(String name);

    /**
     * Creates a new user.
     *
     * @param user The User object representing the user to be created.
     * @return True if the user creation was successful, false otherwise.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    boolean create(User user) throws IncompleteUserException, NullPointerException;

    /**
     * Deletes a user.
     *
     * @param user The User object representing the user to be deleted.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    void delete(User user) throws IncompleteUserException, NullPointerException;

    /**
     * Retrieves information about a specific user.
     *
     * @param user The User object representing the user to be retrieved.
     * @return The User object containing the user's information, or null if not found.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    User getUser(User user) throws IncompleteUserException, NullPointerException;

    /**
     * Updates a user's information.
     *
     * @param user        The User object representing the user
     *                    whose information will be updated.
     * @param updateUser The User object containing the new data to update.
     * @return True if the user's information was successfully updated,
     * false otherwise.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    boolean update(User user, User updateUser) throws IncompleteUserException, NullPointerException;

    /**
     * Retrieves a list of users from a specified page number.
     *
     * @param page The page number from which to retrieve users.
     * @return A list of User objects from the specified page.
     * @throws InvalidPageException If the page number is invalid.
     */
    List<User> getUsersByPage(int page) throws InvalidPageException;
}
