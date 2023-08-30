package com.fake_orgasm.users_management.services;


import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.InvalidPageException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service implementation for managing user-related operations using a B-tree data structure.
 */
@Service
public class UserManagementService implements IUserManagement {
    private final BTree<User> btree;

    /**
     * Constructs a UserManagementService instance with a provided B-tree.
     *
     * @param btree The B-tree used for storing and managing User objects.
     */
    public UserManagementService(BTree<User> btree) {
        this.btree = btree;
    }

    /**
     * Searches for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @return A list of User objects matching the search criteria.
     */
    @Override
    public List<User> search(String name) {
        return BTreeHandler.bfs(name, btree.getRoot());
    }

    /**
     * Creates a new user.
     *
     * @param user The User object representing the user to be created.
     * @return True if the user creation was successful, false otherwise.
     * @throws IncompleteUserException If the user's properties are incomplete.
     */
    @Override
    public boolean create(User user) throws IncompleteUserException {
        validateUser(user);
        btree.insert(user);
        return true;
    }

    /**
     * Deletes a user.
     *
     * @param user The User object representing the user to be deleted.
     * @throws IncompleteUserException If the user's properties are incomplete.
     */
    @Override
    public void delete(User user) throws IncompleteUserException {
        validateUser(user);
        btree.remove(user);
    }

    /**
     * Retrieves information about a specific user from the B-tree.
     *
     * @param user The User object representing the user to be retrieved.
     * @return The User object containing the user's information, or null if not found.
     */
    @Override
    public User getUser(User user) throws IncompleteUserException {
        validateUser(user);
        return btree.searchKey(user);
    }

    /**
     * Updates a user's information.
     *
     * @param user       The User object representing the user
     *                   whose information will be updated.
     * @param updateUser The User object containing the new data to update.
     * @return True if the user's information was successfully updated,
     * false otherwise.
     * @throws IncompleteUserException If the user's properties are incomplete.
     */
    @Override
    public boolean update(User user, User updateUser) throws IncompleteUserException {
        validateUser(user);
        if (btree.remove(user)) {
            btree.insert(updateUser);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list of users from a specified page number.
     *
     * @param page The page number from which to retrieve users.
     * @return A list of User objects from the specified page.
     * @throws InvalidPageException If the page number is invalid.
     */
    @Override
    public List<User> getUsersByPage(int page) throws InvalidPageException {
        if (page < 1) {
            throw new InvalidPageException("Invalid page number.");
        }

        return BTreeHandler.getKeysFromPage(page, btree);
    }

    /**
     * Validates the completeness of a User object.
     *
     * @param user The User object to validate.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    private void validateUser(User user) throws IncompleteUserException {
        if (user == null) {
            throw new NullPointerException("User object is null.");
        }
        if (!isComplete(user)) {
            throw new IncompleteUserException("User's properties are incomplete.");
        }
    }

    /**
     * Checks if a User object is complete, i.e., all mandatory properties are non-null and non-empty.
     *
     * @param user The User object to check for completeness.
     * @return True if the user object is complete, false otherwise.
     */
    private boolean isComplete(User user) {
        try {
            if (user.getName() == null || user.getName().isEmpty()) {
                return false;
            }
            if (user.getLastName() == null || user.getLastName().isEmpty()) {
                return false;
            }
            user.getId();
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}