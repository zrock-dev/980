package com.fake_orgasm.rest_controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.rest_controller.UserDatabaseController;
import com.fake_orgasm.users_management.services.IUserManager;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import com.fake_orgasm.users_management.services.exceptions.NonexistentUserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
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
public class UserDatabaseSearchControllerTest {
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
        users.add(new User(13, "daniel", "espinoza", LocalDate.of(2004, 04, 25), "BOLIVIA"));
        users.add(new User(14, "daniel", "andrade", LocalDate.of(2002, 01, 25), "CHILE"));
        return users;
    }

    /**
     * Test case for searching an existing key.
     *
     * @return description of return value
     * @throws Exception if an exception occurs during the test
     */

    /**
     * Test case for getting a nonexistent user.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetNonexistentUser() throws Exception {
        User nonexistentUser = new User(999, "nonexistent", "user", "chester", "checa");
        String nonexistentUserJson = objectMapper.writeValueAsString(nonexistentUser);

        doThrow(new NonexistentUserException("User Object Not Exist"))
                .when(userManager)
                .getUser(any(User.class));

        mockMvc.perform(get(BASE_END_POINT + "/" + nonexistentUser.getId() + "?fn="
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

        verify(userManager).getUser(any(User.class));
    }

    @Test
    public void testGetUserWithIncompleteData() throws Exception {
        User incompleteUser = new User(999, "nonexistent", "user", "chester", "checa");
        String incompleteUserJson = objectMapper.writeValueAsString(incompleteUser);

        doThrow(new IncompleteUserException("User properties are incomplete."))
                .when(userManager)
                .getUser(any(User.class));

        mockMvc.perform(get(BASE_END_POINT + "/" + incompleteUser.getId() + "?fn="
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

        verify(userManager).getUser(any(User.class));
    }
}
