package com.fake_orgasm.rest_controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.rest_controller.UserDatabaseController;
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
public class UserDatabaseDeleteControllerTest {
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
        users.add(new User(13, "daniel", "thomas", "espinoza", "escalera"));
        users.add(new User(14, "daniel", "andres", "becerra", "espinoza"));

        return users;
    }

    /**
     * Test for successfully deleting a user.
     *
     * @throws Exception If an exception occurs during the test.
     */
    @Test
    public void testDeleteUserSuccess() throws Exception {
        User validUser = createUsers().get(0);
        String validUserJson = objectMapper.writeValueAsString(validUser);

        mockMvc.perform(delete(BASE_END_POINT + "/" + validUser.getId() + "?fn="
                                + validUser.getFirstName() + "&sn="
                                + validUser.getSecondName() + "&lfn="
                                + validUser.getFirstLastName() + "&lsn="
                                + validUser.getSecondLastName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User deleted successfully."))
                .andExpect(jsonPath("$.status").value(200));

        verify(userManager).delete(any(User.class));
    }

    /**
     * Test for deleting a nonexistent user.
     *
     * @throws Exception If an exception occurs during the test.
     */
    @Test
    public void testDeleteNonexistentUser() throws Exception {
        User nonexistentUser = createUsers().get(1);
        String nonexistentUserJson = objectMapper.writeValueAsString(nonexistentUser);

        doThrow(new NonexistentUserException("User Object Not Exist"))
                .when(userManager)
                .delete(any(User.class));

        mockMvc.perform(delete(BASE_END_POINT + "/" + nonexistentUser.getId() + "?fn="
                                + nonexistentUser.getFirstName() + "&sn="
                                + nonexistentUser.getSecondName() + "&lfn="
                                + nonexistentUser.getFirstLastName() + "&lsn="
                                + nonexistentUser.getSecondLastName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonexistentUserJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User Object Not Exist"))
                .andExpect(jsonPath("$.status").value(404));

        verify(userManager).delete(any(User.class));
    }

    /**
     * Test for deleting a user with incomplete data.
     *
     * @throws Exception If an exception occurs during the test.
     */
    @Test
    public void testDeleteUserWithIncompleteData() throws Exception {
        User incompleteUser = createUsers().get(0);
        String incompleteUserJson = objectMapper.writeValueAsString(incompleteUser);

        doThrow(new IncompleteUserException("User properties are incomplete."))
                .when(userManager)
                .delete(any(User.class));

        mockMvc.perform(delete(BASE_END_POINT + "/" + incompleteUser.getId() + "?fn="
                                + incompleteUser.getFirstName() + "&sn="
                                + incompleteUser.getSecondName() + "&lfn="
                                + incompleteUser.getFirstLastName() + "&lsn="
                                + incompleteUser.getSecondLastName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incompleteUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User properties are incomplete."))
                .andExpect(jsonPath("$.status").value(400));

        verify(userManager).delete(any(User.class));
    }
}
