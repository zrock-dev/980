package com.fake_orgasm.generator;

import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.users_management.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserGenerator {
    public static String GENERATOR_ROOT = "src/main/resources/generation";
    public static int GENERATION_CHUNK_SIZE = 3;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<String> firstNamesList;
    private final List<String> firstLastNamesList;
    private final List<String> secondLastNamesList;

    private FileReader firstNamesPool;
    private FileReader firstLastNamesPool;
    private FileReader secondLastNamesPool;

    private Iterator<String> firstNamesIterator;
    private Iterator<String> firstLastNamesIterator;
    private Iterator<String> secondLastNamesIterator;

    private String currentFirstName;
    private String currentFirstLastName;


    public UserGenerator() {
        try {
            firstNamesPool = new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "first_name_pool"));
            firstLastNamesPool = new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "first_last_name_pool"));
            secondLastNamesPool = new FileReader(String.format("%s/%s.txt", GENERATOR_ROOT, "second_last_name_pool"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            firstNamesList = new ArrayList<>();
            firstLastNamesList = new ArrayList<>();
            secondLastNamesList = new ArrayList<>();

            fillStacks();
            firstNamesIterator = firstNamesList.iterator();
            firstLastNamesIterator = firstLastNamesList.iterator();
            secondLastNamesIterator = secondLastNamesList.iterator();

            currentFirstName = firstNamesIterator.next();
            currentFirstLastName = firstLastNamesIterator.next();
        }
    }

    public User make() {
        if (!firstNamesIterator.hasNext()) {
            clearStacks();
            fillStacks();
            firstNamesIterator = firstNamesList.iterator();
            firstLastNamesIterator = firstLastNamesList.iterator();
            secondLastNamesIterator = secondLastNamesList.iterator();

            currentFirstName = firstNamesIterator.next();
            currentFirstLastName = firstLastNamesIterator.next();
        }

        if (!secondLastNamesIterator.hasNext()) {
            if (!firstLastNamesIterator.hasNext()) {
                currentFirstName = firstNamesIterator.next();
                firstLastNamesIterator = firstLastNamesList.iterator();
            }

            currentFirstLastName = firstLastNamesIterator.next();
            secondLastNamesIterator = secondLastNamesList.iterator();
        }

        return new User(currentFirstName, currentFirstLastName, secondLastNamesIterator.next());
    }

    private void fillStacks() {
        for (int loopControl = 0; loopControl < GENERATION_CHUNK_SIZE; loopControl++) {
            if (firstNamesPool.hasNext() && firstLastNamesPool.hasNext() && secondLastNamesPool.hasNext()) {
                firstNamesList.add(firstNamesPool.nextLine());
                firstLastNamesList.add(firstLastNamesPool.nextLine());
                secondLastNamesList.add(secondLastNamesPool.nextLine());
            } else {
                // exception
                logger.error("Out of lines to keep generating");
            }
        }
    }

    private void clearStacks(){
        firstNamesList.clear();
        firstLastNamesList.clear();
        secondLastNamesList.clear();
    }
}
