package com.fake_orgasm.generator.user_generator;

import com.fake_orgasm.users_management.models.User;
import java.util.Calendar;

/**
 * The UserGenerator class is responsible for generating User instances with fake information.
 * It utilizes various sub-generators to create usernames, citizen identification numbers, and birth dates.
 */
public class UserGenerator {
    private final UserNameGenerator userNameGenerator;
    private final CitizenIdentificationGenerator citizenIdentificationGenerator;
    private final DateGenerator dateGenerator;

    /**
     * Initializes a new instance of the UserGenerator class.
     * Creates sub-generators for usernames, citizen identification numbers, and birth dates.
     * The birth date generator's year ceiling is set to the current year.
     */
    public UserGenerator() {
        userNameGenerator = new UserNameGenerator();
        citizenIdentificationGenerator = new CitizenIdentificationGenerator();
        dateGenerator = new DateGenerator(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * Generates a new User instance with fake information.
     * Utilizes sub-generators to populate the username, citizen ID, and birth date.
     *
     * @return A User instance with fake information.
     */
    public User make() {
        User user = userNameGenerator.make();
        user.setId(citizenIdentificationGenerator.make());
        user.setDateBirth(dateGenerator.make());
        return user;
    }
}
