package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.exceptions.UserNotFoundException;
import com.fake_orgasm.flights_management.services.records.UpdateRequest;
import com.fake_orgasm.users_management.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This class is a rest client for manage and request data from the server
 */
@Service
public class RestClient {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/users";

    /**
     * This is a constructor method to initialize the rest client.
     *
     * @param restTemplate the rest template.
     */
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * This method searches for users by name.
     *
     * @param name the name of the user.
     * @return the list of users.
     */
    public List<User> searchUsers(String name) {
        String url = BASE_URL + "/search?name=" + name;

        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        List<User> usersFound = response.getBody();
        return usersFound;
    }

    /**
     * This method updates user information in the user management service.
     *
     * @param oldDataUser the old data.
     * @param newDataUser the new data.
     * @return true if the user was updated successfully, false otherwise
     */


    public boolean updateUserData(User oldDataUser, User newDataUser) {
        try {
            restTemplate.put(BASE_URL, new UpdateRequest(oldDataUser, newDataUser));
            return true;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("Non existent user");
            } else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException(ex.getMessage());
            } else {
                throw new RuntimeException("Error updating user");
            }
        } catch (RestClientException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * Creates a user by sending a POST request to the specified base URL with the user object.
     *
     * @param user the user object to be created
     * @return true if the user was created successfully, false otherwise
     */

    public boolean createUser(User user) {
        ResponseEntity<Void> response = restTemplate.postForEntity(BASE_URL + "/" + user.getId(), user, Void.class);

        HttpStatus statusCode = (HttpStatus) response.getStatusCode();

        if (statusCode == HttpStatus.CREATED) {
            return true;
        } else {
            throw new RuntimeException("Unexpected status code: " + statusCode);
        }
    }

    /**
     * This method finds an existing user or creates a new one if not found in the user
     * management system.
     *
     * @param user The User object representing the passenger.
     * @return The existing or newly created User object.
     */
    public User findUser(User user) {
        String url = BASE_URL + "/" + user.getId() +
                "?fn=" + user.getFirstName() +
                "&sn=" + user.getSecondName() +
                "&lfn=" + user.getFirstLastName() +
                "&lsn=" + user.getSecondLastName();

        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return createUser(user) ? user : null;
        } else {
            throw new RuntimeException("Unexpected status code: " + response.getStatusCode());
        }
    }
}
