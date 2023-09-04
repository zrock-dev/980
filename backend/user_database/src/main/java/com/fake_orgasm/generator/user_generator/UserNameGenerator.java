package com.fake_orgasm.generator.user_generator;

import com.fake_orgasm.generator.user_generator.combinatory_parts.Administrator;
import com.fake_orgasm.generator.user_generator.combinatory_parts.CoreWorker;
import com.fake_orgasm.generator.user_generator.combinatory_parts.Piece;
import com.fake_orgasm.generator.user_generator.combinatory_parts.Worker;
import com.fake_orgasm.generator.utils.Notifiable;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.utils.FileReader;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The UserNameGenerator class is responsible for generating usernames for User instances.
 * It combines various components to construct usernames in a structured manner.
 */
public class UserNameGenerator implements Notifiable {

    public static final String GENERATOR_ROOT = "src/main/resources/generation";
    public static final int GENERATION_CHUNK_SIZE = 32;
    public static final int GENERATION_STACKS = 4;

    private Administrator administrator;
    private CoreWorker secondLastNames;
    private Worker firstLastNames;
    private Worker firstNames;
    private Worker secondNames;

    private final Logger logger = LogManager.getLogger(getClass());

    /**
     * Initializes a new instance of the UserNameGenerator class.
     * Sets up the worker components for username generation.
     */
    public UserNameGenerator() {
        try {
            firstNames =
                    new Worker(new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "first_name_pool")), this);
            secondNames = new Worker(
                    new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "second_name_pool")), firstNames);
            firstLastNames = new Worker(
                    new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "first_last_name_pool")), secondNames);
            secondLastNames = new CoreWorker(
                    new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "second_last_name_pool")),
                    firstLastNames);

            administrator = new Administrator(new Piece[] {firstNames, secondNames, firstLastNames, secondLastNames});
            administrator.startup();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Generates a new User instance with a generated username.
     *
     * @return A User instance with a generated username.
     */
    public User make() {
        String secondLastName = secondLastNames.next();
        String firstLastName = firstLastNames.next();
        String secondName = secondNames.next();
        String firstName = firstNames.next();

        return new User(firstName, secondName, firstLastName, secondLastName);
    }

    @Override
    public void doNotify() {
        try {
            administrator.fillStacks();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
