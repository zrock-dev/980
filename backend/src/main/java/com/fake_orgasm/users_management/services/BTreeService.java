package com.fake_orgasm.users_management.services;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.NameIndexer;
import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.InvalidPageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BTreeService implements IUserManagement {
    public static BTree<User> bTree = new BTree<>(2, new BTreeRepository());
    private NameIndexer nameIndexer;

    public BTreeService() {
        nameIndexer = new NameIndexer();
    }
    /**
     * Searches for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @return A list of User objects matching the search criteria.
     */
    @Override
    public List<User> search(String name) {
        List<User> users = new ArrayList<>();
        Set<User> userSet = nameIndexer.searchName(name);
        for(User user : userSet){
            if(users.size() < 20){
                users.add(user);
            }else break;
        }
        return users;
    }

    /**
     * Creates a new user.
     *
     * @param user The User object representing the user to be created.
     * @return True if the user creation was successful, false otherwise.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    @Override
    public boolean create(User user) throws IncompleteUserException, NullPointerException {
        bTree.insert(user);
        nameIndexer.addUser(user, bTree.search(bTree.getRoot(), user));
        return true;
    }

    /**
     * Deletes a user.
     *
     * @param user The User object representing the user to be deleted.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    @Override
    public void delete(User user) throws IncompleteUserException, NullPointerException {
        bTree.remove(user);
    }

    /**
     * Retrieves information about a specific user.
     *
     * @param user The User object representing the user to be retrieved.
     * @return The User object containing the user's information, or null if not found.
     * @throws IncompleteUserException If the user's properties are incomplete.
     * @throws NullPointerException    If the provided user object is null.
     */
    @Override
    public User getUser(User user) throws IncompleteUserException, NullPointerException {
        return bTree.searchKey(user);
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
     * @throws NullPointerException    If the provided user object is null.
     */
    @Override
    public boolean update(User user, User updateUser) throws IncompleteUserException, NullPointerException {
        User userToUpdate = bTree.searchKey(user);
        userToUpdate.setId(updateUser.getId());
        userToUpdate.setFirstName(updateUser.getFirstName());
        userToUpdate.setSecondName(updateUser.getSecondName());
        userToUpdate.setFirstLastName(updateUser.getFirstLastName());
        userToUpdate.setSecondLastName(updateUser.getSecondLastName());
        userToUpdate.setDateBirth(updateUser.getDateBirth());
        userToUpdate.setCategory(updateUser.getCategory());
        userToUpdate.setCountry(updateUser.getCountry());
        Node<User> nodeChanged = bTree.search(bTree.getRoot(), userToUpdate);
        bTree.getRepository().save(nodeChanged);
        return true;
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
        return null;
    }
}
