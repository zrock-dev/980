package com.fake_orgasm.users_management.services;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.exceptions.DuplicateUserException;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.InvalidPageException;
import com.fake_orgasm.users_management.services.exceptions.NonexistentUserException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This class manages user-related operations in the application.
 * It provides methods for creating, searching, updating, and deleting users, as well as other
 * user management-related operations.
 */
@Service
public class UserManager implements IUserManager {
    private List<User> users;
    private UserGenerator userGenerator;

    /**
     * This is a constructor method to initialize the state of the class.
     */
    public UserManager() {
        userGenerator = new UserGenerator();
        this.users = new ArrayList<>();
        User user;
        for (int i = 0; i < 10_000; i++) {
            user = makeUser();
            user.setFlights(new ArrayList<>());
            this.users.add(user);
        }
    }

    /**
     * Generates a new User object by utilizing the UserGenerator and FlightHistoryGenerator classes.
     *
     * @return The newly created User object.
     */
    private User makeUser() {
        User user = userGenerator.make();
        return user;
    }

    /**
     * Searches for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @param page the page of the results.
     * @return A list of User objects matching the search criteria.
     */
    @Override
    public Page search(String name, int page) {
        page++;
        if (page < 1) {
            throw new InvalidPageException("Invalid page number.");
        }

        List<User> result = new ArrayList<>();

        users.forEach(user -> {
            if (user.getFullName().contains(name)) {
                result.add(user);
            }
        });
        int pageSize = 20;
        int totalResults = result.size();
        if (totalResults == 0) {
            return new Page(0, 0, new ArrayList<>(), 0, 0);
        }

        int totalPages = (totalResults + pageSize - 1) / pageSize;

        if (page > totalPages) {
            throw new InvalidPageException("Invalid page number.");
        }

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalResults);

        List<User> usersForPage = result.subList(startIndex, endIndex);

        return new Page(totalResults, usersForPage.size(), usersForPage, page-1, totalPages-1);
    }

    /**
     * Creates a new user.
     *
     * @param user The User object representing the user to be created.
     * @return True if the user creation was successful, false otherwise.
     */
    @Override
    public boolean create(User user) {
        if (user == null) {
            throw new NullPointerException("User object is null.");
        }

        if (!isUserComplete(user)) {
            throw new IncompleteUserException("User properties are incomplete.");
        }

        if (users.contains(user)) {
            throw new DuplicateUserException("User already exists.");
        }

        users.add(user);
        return true;
    }

    /**
     * Deletes a user.
     *
     * @param user The User object representing the user to be deleted.
     */
    @Override
    public void delete(User user) {
        if (user == null) {
            throw new NullPointerException("User object is null.");
        }
        if (isUserComplete(user)) {
            User userToRemove = null;
            for (User user1 : users) {
                if (user1.getId() == user.getId()) {
                    userToRemove = user1;
                    break;
                }
            }
            if (userToRemove == null) {
                throw new NonexistentUserException("User Object Not Exist");
            }
            users.remove(userToRemove);
        } else {
            throw new IncompleteUserException("User properties are incomplete.");
        }
    }

    /**
     * Retrieves information about a specific user.
     *
     * @param user The User object representing the user to be retrieved.
     * @return The User object containing the user's information, or null if not found.
     */
    @Override
    public User getUser(User user) {
        if (user == null) {
            throw new NullPointerException("User object is null.");
        }
        if (isUserComplete(user)) {
            if (users.contains(user)) {
                return users.get(users.indexOf(user));
            } else {
                throw new NonexistentUserException("User Object Not Exist");
            }
        } else {
            throw new IncompleteUserException("User properties are incomplete.");
        }
    }

    /**
     * Updates a user's information.
     *
     * @param user       The User object representing the user
     *                   whose information will be updated.
     * @param updateUser The User object containing the new data to update.
     * @return True if the user's information was successfully updated,
     * false otherwise.
     */
    @Override
    public boolean update(User user, User updateUser) {
        if (user == null) {
            throw new NullPointerException("User object is null.");
        }
        if (isUserComplete(user) && isUserComplete(updateUser)) {
            if (users.contains(user)) {
                updateUser.setId(user.getId());
                users.set(users.indexOf(user), updateUser);
                return true;
            } else {
                throw new NonexistentUserException("User Object Not Exist");
            }
        } else {
            throw new IncompleteUserException("User properties are incomplete.");
        }
    }

    /**
     * Retrieves a list of users from a specified page number.
     *
     * @param page The page number from which to retrieve users.
     * @return A list of User objects from the specified page.
     */
    @Override
    public Page getUsersByPage(int page) {
        page++;
        int pageSize = 20;
        int totalUsers = users.size();
        int totalPages = (totalUsers + pageSize - 1) / pageSize;

        if (totalUsers == 0) {
            return new Page(0, 0, new ArrayList<>(), 0, 0);
        }
        if (page < 1 || page > totalPages) {
            throw new InvalidPageException("Invalid page number.");
        }

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalUsers);
        List<User> sublist = users.subList(startIndex, endIndex);
        return new Page(totalUsers, sublist.size(), sublist, page-1, totalPages-1);
    }

    /**
     * Checks if the given user object is complete.
     *
     * @param user the user object to be checked
     * @return true if the user object is complete, false otherwise
     */
    private boolean isUserComplete(User user) {
        if (user.getFirstName() != null && user.getFirstLastName() != null && user.getId() > 0) {
            return true;
        }
        return false;
    }
}
