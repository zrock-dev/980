package com.fake_orgasm.generator.user_generator;

import com.fake_orgasm.users_management.models.User;

public class UserGenerator {
    private final UserNameGenerator userNameGenerator;
    private final CitizenIdentificationGenerator citizenIdentificationGenerator;

    public UserGenerator() {
        userNameGenerator = new UserNameGenerator();
        citizenIdentificationGenerator = new CitizenIdentificationGenerator();
    }

    public User make(){
        User user = userNameGenerator.make();
        user.setCitizen_id(citizenIdentificationGenerator.next());
        return user;
    }
}
