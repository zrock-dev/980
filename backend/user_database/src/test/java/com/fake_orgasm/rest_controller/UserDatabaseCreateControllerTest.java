package com.fake_orgasm.rest_controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.IBTreeRepository;
import com.fake_orgasm.users_management.rest_controller.UserDatabaseController;
import com.fake_orgasm.users_management.services.IUserManager;
import com.fake_orgasm.users_management.services.exceptions.DuplicateUserException;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
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
public class UserDatabaseCreateControllerTest {
    private static final String BASE_END_POINT = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    IUserManager userManager;

    @MockBean
    IBTreeRepository repository;

    /**
     * A description of the entire Java function.
     *
     * @return description of return value
     */
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
        users.add(new User(13, "daniel", "espinoza", LocalDate.of(2004, 04, 25), Category.VIP, "BOLIVIA"));
        users.add(new User(14, "daniel", "andrade", LocalDate.of(2002, 01, 25), Category.VIP, "CHILE"));
        return users;
    }

    /**
     * Creates a test for the 'create' function.
     *
     * @return description of return value
     * @throws Exception if an exception occurs
     */
    @Test
    public void createTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = createUsers().get(0);
        user.setDateBirth(null);
        String userJson = objectMapper.writeValueAsString(user);

        when(userManager.create(any(User.class))).thenReturn(true);

        mockMvc.perform(post(BASE_END_POINT + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User created successfully."));
        verify(userManager).create(any(User.class));
    }

    /**
     * Test case for creating a null user.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    public void createNullUserTest() throws Exception {
        String userJson = "{}";

        when(userManager.create(any(User.class)))
                .thenThrow(new IncompleteUserException("User properties are incomplete."));

        mockMvc.perform(post(BASE_END_POINT + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User properties are incomplete."))
                .andExpect(jsonPath("$.status").value(400));

        verify(userManager).create(any(User.class));
    }

    /**
     * Test case for creating incomplete user information.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void createIncompleteUserInfoTest() throws Exception {
        User user = createUsers().get(0);
        user.setFirstName(null);
        user.setDateBirth(null);
        String userJson = objectMapper.writeValueAsString(user);

        when(userManager.create(any(User.class)))
                .thenThrow(new IncompleteUserException("User properties are incomplete."));

        mockMvc.perform(post(BASE_END_POINT + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User properties are incomplete."))
                .andExpect(jsonPath("$.status").value(400));
        verify(userManager).create(any(User.class));
    }

    /**
     * Test case for trying to create a duplicated user.
     *
     * @throws Exception if an exception occurs during the test case
     */
    @Test
    public void tryToCreateDuplicatedUserTest() throws Exception {
        User validUser = createUsers().get(0);
        validUser.setDateBirth(null);
        String validUserJson = objectMapper.writeValueAsString(validUser);

        when(userManager.create(any(User.class))).thenThrow(new DuplicateUserException("User already exists."));
        mockMvc.perform(post(BASE_END_POINT + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User already exists."))
                .andExpect(jsonPath("$.status").value(400));

        verify(userManager, times(1)).create(any(User.class));
    }
}
