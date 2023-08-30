package com.fake_orgasm.generator.user_generator;

import com.fake_orgasm.users_management.models.User;
import java.util.Calendar;

public class UserGenerator {
    private final UserNameGenerator userNameGenerator;
    private final CitizenIdentificationGenerator citizenIdentificationGenerator;
    private final DateGenerator dateGenerator;

    public UserGenerator() {
        userNameGenerator = new UserNameGenerator();
        citizenIdentificationGenerator = new CitizenIdentificationGenerator();
        dateGenerator = new DateGenerator(Calendar.getInstance().get(Calendar.YEAR));
    }

    public User make() {
        User user = userNameGenerator.make();
        user.setCitizen_id(citizenIdentificationGenerator.make());
        user.setDateBirth(dateGenerator.make());
        return user;
    }
}
