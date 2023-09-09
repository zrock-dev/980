package com.fake_orgasm.users_management.rest_controller;

import static com.fake_orgasm.users_management.rest_controller.RestUtil.buildResponse;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.rest_controller.records.UpdateUserRequest;
import com.fake_orgasm.users_management.services.IUserManager;
import com.fake_orgasm.users_management.services.Page;
import com.fake_orgasm.users_management.services.exceptions.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param page the page that defines the amount of results.
     * @return a list of users matching the search criteria
     */
    @GetMapping("/search")
    public Page searchUsers(@RequestParam String name, @RequestParam int page) {
        Page searchResult = userManager.search(name, page);
        return searchResult;
    }

    /**
     * Creates a new user using the provided user object.
     *
     * @param user the user object to be created
     * @return a ResponseEntity containing a success message if the user was created successfully,
     * or a ResponseEntity with a status code and an error message if the user creation failed
     */
    @PostMapping("")
    public ResponseEntity<RestResponse> createUser(@RequestBody User user) {
        userManager.create(user);
        return buildResponse("User created successfully.", HttpStatus.CREATED);
    }

    /**
     * Deletes a user.
     *
     * @param userId the ID of the user to be deleted
     * @param fn     the first name of the user
     * @param sn     the second name of the user
     * @param lfn    the last first name of the user
     * @param lsn    the last second name of the user
     * @return a response entity indicating the success of the delete operation
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<RestResponse> deleteUser(
            @PathVariable int userId,
            @RequestParam String fn,
            @RequestParam String sn,
            @RequestParam String lfn,
            @RequestParam String lsn) {
        userManager.delete(new User(userId, fn, sn, lfn, lsn));
        return buildResponse("User deleted successfully.", HttpStatus.OK);
    }

    /**
     * Retrieves a user based on the user ID and query parameters.
     *
     * @param userId the ID of the user to retrieve
     * @param fn     the first name of the user
     * @param sn     the second name of the user
     * @param lfn    the last first name of the user
     * @param lsn    the last second name of the user
     * @return the retrieved user if found, or an error response
     */
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
    public ResponseEntity<RestResponse> updateUser(@RequestBody UpdateUserRequest updateUser) {
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
    public ResponseEntity<Page> getUsersByPage(@RequestParam int page) {
        Page usersPage = userManager.getUsersByPage(page);
        return ResponseEntity.ok(usersPage);
    }
}
