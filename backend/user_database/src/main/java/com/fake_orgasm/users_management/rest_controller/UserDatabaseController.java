package com.fake_orgasm.users_management.rest_controller;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.rest_controller.records.UpdateUserRequest;
import com.fake_orgasm.users_management.services.IUserManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fake_orgasm.users_management.rest_controller.RestUtil.buildResponse;


/**
 * The UserDatabaseController class handles the REST API endpoints related to user management.
 * It provides functionality to create, update, delete, and retrieve user data from the database.
 */
@RestController
@RequestMapping("/api/users")
public class UserDatabaseController {

    private final IUserManager userManager;

    /**
     * This is a constructor for the UserDatabaseController class.
     *
     * @param userManager
     */
    @Autowired
    public UserDatabaseController(IUserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Retrieves a list of users based on the provided name.
     *
     * @param name the name to search for
     * @return a list of users matching the search criteria
     */
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userManager.search(name);
    }

    /**
     * Creates a new user using the provided user object.
     *
     * @param user the user object to be created
     * @return a ResponseEntity containing a success message if the user was created successfully,
     * or a ResponseEntity with a status code and an error message if the user creation failed
     */
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userManager.create(user);
        return buildResponse("User created successfully.", HttpStatus.CREATED);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable int userId,
            @RequestParam String fn,
            @RequestParam String sn,
            @RequestParam String lfn,
            @RequestParam String lsn) {
        userManager.delete(new User(userId, fn, sn, lfn, lsn));
        return buildResponse("User deleted successfully.", HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(
            @PathVariable int userId,
            @RequestParam String fn,
            @RequestParam String sn,
            @RequestParam String lfn,
            @RequestParam String lsn) {
        User retrievedUser = userManager.getUser(new User(userId, fn, sn, lfn, lsn));
        if (retrievedUser != null) {
            return ResponseEntity.ok(retrievedUser);
        } else {
            return buildResponse("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a user.
     *
     * @param updateUser the request object containing the user data to be updated
     * @return the response entity indicating the status of the update operation
     */
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUser) {
        userManager.update(updateUser.getOldData(), updateUser.getNewData());
        return buildResponse("User updated successfully.", HttpStatus.OK);
    }

    /**
     * Retrieves a page of users based on the provided page number.
     *
     * @param page the page number of the users to retrieve
     * @return a ResponseEntity containing a list of User objects if the page is valid,
     * or a ResponseEntity with a BAD_REQUEST status and an error message if the page is invalid
     */
    @GetMapping("")
    public ResponseEntity<?> getUsersByPage(@RequestParam int page) {
        List<User> users = userManager.getUsersByPage(page);
        return ResponseEntity.ok(users);
    }
}
