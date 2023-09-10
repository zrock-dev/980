package com.fake_orgasm.rest_controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.rest_controller.UserDatabaseController;
import com.fake_orgasm.users_management.rest_controller.records.UpdateUserRequest;
import com.fake_orgasm.users_management.services.IUserManager;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.NonexistentUserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class is in charged to test the {@link UserDatabaseController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserDatabaseController.class)
public class UserDatabaseUpdateControllerTest {
    private static final String BASE_END_POINT = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    IUserManager userManager;

    @Test
    public void contextLoads() {
        assertNotNull(userManager);
    }

    /**
     * Creates a list of users.
     *
     * @return the list of users
     */
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Creates a list of users.
     *
     * @return the list of users
     */
    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(13, "daniel", "espinoza", "", ""));
        users.add(new User(14, "daniel", "andrade", "", ""));

        return users;
    }

    /**
     * Test case for the updateUserSuccess() function.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateUserSuccess() throws Exception {
        User existingUser = createUsers().get(0);
        User updateUser = new User(existingUser.getId(), "Updated", "User", "", "CANADA");
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(existingUser, updateUser);
        String updateUserJson = objectMapper.writeValueAsString(updateUserRequest);

        when(userManager.update(any(User.class), any(User.class))).thenReturn(true);

        mockMvc.perform(put(BASE_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User updated successfully."))
                .andExpect(jsonPath("$.status").value(200));

        verify(userManager).update(any(User.class), any(User.class));
    }

    /**
     * Test case for updating a nonexistent user.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateNonexistentUser() throws Exception {
        User nonexistentUser = new User(999, "nonexistent", "user", "non", "exist");

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(nonexistentUser, nonexistentUser);
        String nonexistentUserJson = objectMapper.writeValueAsString(updateUserRequest);
        when(userManager.update(any(User.class), any(User.class)))
                .thenThrow(new NonexistentUserException("User Object Not Exist"));

        mockMvc.perform(put(BASE_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonexistentUserJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User Object Not Exist"))
                .andExpect(jsonPath("$.status").value(404));

        verify(userManager).update(any(User.class), any(User.class));
    }

    /**
     * Test case for updating a user with incomplete data.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateUserWithIncompleteData() throws Exception {
        User incompleteUser = new User(999, "incomplete", "", "", "USA");
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(incompleteUser, incompleteUser);
        String nonexistentUserJson = objectMapper.writeValueAsString(updateUserRequest);

        when(userManager.update(any(User.class), any(User.class)))
                .thenThrow(new IncompleteUserException("User properties are incomplete."));

        mockMvc.perform(put(BASE_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonexistentUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User properties are incomplete."))
                .andExpect(jsonPath("$.status").value(400));

        verify(userManager).update(any(User.class), any(User.class));
    }
}
