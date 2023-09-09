package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class UserSearcher implements Callable<Set<User>> {

    private List<Node<User>> nodesToCalculate;
    private String[] words;
    /**
     * Constructor of the class.
     *
     * @param words words to search.
     */
    public UserSearcher(String[] words) {
        nodesToCalculate = new ArrayList<>();
        this.words = words;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Set<User> call() throws Exception {
        if (nodesToCalculate.size() > 0) {
            return getUserFromNodes(nodesToCalculate, words);
        }
        return null;
    }

    /**
     * Add a node to calculate.
     *
     * @param node node to add.
     */
    public void addNodeToCalculate(Node<User> node) {
        nodesToCalculate.add(node);
    }

    /**
     * Get the users from the nodes.
     *
     * @param nodes nodes to get the users.
     * @param words words to search.
     * @return users found.
     */
    private Set<User> getUserFromNodes(List<Node<User>> nodes, String[] words) {
        Set<User> usersFound = new HashSet<>();
        List<User> users;
        for (Node<User> node : nodes) {
            users = new ArrayList<>();
            for (int i = 0; i < node.getSize(); i++) {
                users.add(node.getKey(i));
            }
            for (User user : users) {
                if (verifyIfContainsWord(user, words)) {
                    usersFound.add(user);
                }
            }
        }
        return usersFound;
    }

    /**
     * Verify if the user contains the word.
     *
     * @param user user to verify.
     * @param word word to search.
     * @return true if the user contains the word, false otherwise.
     */
    private boolean verifyIfContainsWord(User user, String[] word) {
        int counter = 0;
        for (String currentWord : word) {
            List<String> syllable = SyllableSeparator.split(currentWord);
            List<String> firstName = SyllableSeparator.split(user.getFirstName().toLowerCase());
            List<String> secondName =
                    SyllableSeparator.split(user.getSecondName().toLowerCase());
            List<String> firstLastName =
                    SyllableSeparator.split(user.getFirstLastName().toLowerCase());
            List<String> secondLastName =
                    SyllableSeparator.split(user.getSecondLastName().toLowerCase());
            if (verifyMatch(firstName, syllable) || verifyMatch(secondName, syllable)) {
                counter++;
            } else if (verifyMatch(firstLastName, syllable) || verifyMatch(secondLastName, syllable)) {
                counter++;
            }
        }
        if (counter == word.length) {
            return true;
        }
        return false;
    }

    private boolean verifyMatch(List<String> completeWord, List<String> wordToMatch) {
        if (wordToMatch.size() > completeWord.size()) {
            return false;
        }
        for (int i = 0; i < wordToMatch.size(); i++) {
            if (!wordToMatch.get(i).equals(completeWord.get(i))) {
                return false;
            }
        }
        return true;
    }
}
