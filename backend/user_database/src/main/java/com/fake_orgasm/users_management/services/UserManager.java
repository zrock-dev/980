package com.fake_orgasm.users_management.services;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.NameIndexer;
import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import com.fake_orgasm.users_management.services.exceptions.DuplicateUserException;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.InvalidPageException;
import com.fake_orgasm.users_management.services.exceptions.NonexistentUserException;
import java.util.*;
import org.springframework.stereotype.Service;

/**
 * This class manages user-related operations in the application.
 * It provides methods for creating, searching, updating, and deleting users, as well as other
 * user management-related operations.
 */
@Service
public class UserManager implements IUserManager {
    private UserGenerator userGenerator;

    private NameIndexer nameIndexer;
    private static BTree<User> bTree;

    /**
     * This is a constructor method to initialize the state of the class.
     */
    public UserManager() {
        nameIndexer = new NameIndexer();
        userGenerator = new UserGenerator();
        bTree = new BTree<>(10, new BTreeRepository(), nameIndexer);
        generateUsers(10000);
    }

    /**
     * Generates 10,000 users and inserts them into the BTree.
     *
     * @param numberToGenerate The number of users to generate.
     */
    public void generateUsers(int numberToGenerate) {
        User user;
        for (int i = 0; i < numberToGenerate; i++) {
            user = makeUser();
            user.setFlights(new ArrayList<>());
            bTree.insert(user);
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
        result.addAll(nameIndexer.search(name));
        int pageSize = 20;
        int totalResults = result.size();
        if (totalResults == 0) {
            return new Page(0, 0, new ArrayList<>(), 0, 0);
        }

        int totalPages = (totalResults + pageSize - 1) / pageSize;

        if (page > totalPages && page != 1) {
            throw new InvalidPageException("Invalid page number.");
        }

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalResults);

        List<User> usersForPage = result.subList(startIndex, endIndex);

        return new Page(totalResults, usersForPage.size(), usersForPage, page - 1, totalPages - 1);
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

        if (bTree.getSize() > 0 && bTree.searchKey(user) != null) {
            throw new DuplicateUserException("User already exists.");
        }

        bTree.insert(user);
        nameIndexer.add(user, bTree.search(bTree.getRoot(), user));
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
            User userToRemove = bTree.searchKey(user);
            if (userToRemove == null) {
                throw new NonexistentUserException("User Object Not Exist");
            }
            bTree.remove(userToRemove);
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
            if (bTree.searchKey(user) != null) {
                return bTree.searchKey(user);
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
            if (bTree.searchKey(user) != null) {
                User userToUpdate = bTree.searchKey(user);
                userToUpdate.setId(updateUser.getId());
                userToUpdate.setFirstName(updateUser.getFirstName());
                userToUpdate.setSecondName(updateUser.getSecondName());
                userToUpdate.setFirstLastName(updateUser.getFirstLastName());
                userToUpdate.setSecondLastName(updateUser.getSecondLastName());
                userToUpdate.setDateBirth(updateUser.getDateBirth());
                userToUpdate.setFlights(updateUser.getFlights());
                userToUpdate.setCountry(updateUser.getCountry());
                Node<User> nodeChanged = bTree.search(bTree.getRoot(), userToUpdate);
                bTree.getRepository().save(nodeChanged);
                nameIndexer.add(user, nodeChanged);
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
        int totalUsers = bTree.getSize();
        int totalPages = totalUsers / 20;
        if (page < 1 || page > totalPages) {
            throw new InvalidPageException("Invalid page number.");
        }
        if (totalUsers == 0) {
            return new Page(0, 0, new ArrayList<>(), 0, 0);
        }
        Stack<Node<User>> stack = new Stack<>();
        Node<User> current = bTree.getRoot();
        Map<String, Integer> counter = new HashMap<>();
        int numberChild;
        List<User> keys = new ArrayList<>();
        int indexPage = 0;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                if (!counter.containsKey(current.getId())) {
                    counter.put(current.getId(), 0);
                }
                stack.push(current);
                numberChild = counter.get(current.getId());
                current = current.getChild(numberChild);
            }
            current = stack.pop();
            int currentPosition = counter.get(current.getId());
            int sizeChildren = current.getValidChildren() - 1;
            if (currentPosition < sizeChildren) {
                keys.add(current.getKey(counter.get(current.getId())));
                stack.push(current);
            } else if (currentPosition == sizeChildren && counter.get(current.getId()) != 0) {
                current = null;
                continue;
            } else {
                for (User user : castToUserList(current.getKeys())) {
                    if (keys.size() == 20) {
                        if ((indexPage + 1) == page) {
                            return new Page(totalUsers, keys.size(), keys, page, totalPages);
                        } else {
                            indexPage++;
                            keys = new ArrayList<>();
                        }
                    }
                    keys.add(user);
                }
            }
            counter.put(current.getId(), counter.get(current.getId()) + 1);
            numberChild = counter.get(current.getId());
            current = current.getChild(numberChild);
        }
        return null;
    }

    private List<User> castToUserList(Object[] users) {
        List<User> listReturned = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            listReturned.add((User) users[i]);
        }
        return listReturned;
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

    /**
     * Get the bTree.
     *
     * @return bTree.
     */
    public static BTree<User> getBTree() {
        return bTree;
    }
}
