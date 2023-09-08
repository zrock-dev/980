package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.models.User;

import java.util.*;

public class NameIndexer implements Indexer<User>{
    private Map<String, Set<Node<User>>> nameIndex;

    public NameIndexer() {
        nameIndex = new HashMap<>();
    }

    /**
     * Adds a key to the indexer.
     *
     * @param user The key to be added.
     * @param node The node where the key is located.
     */
    @Override
    public void add(User user, Node<User> node){
        String firstName = user.getFirstName().toLowerCase();
        String secondName = user.getSecondName().toLowerCase();
        String firstLastName = user.getFirstLastName().toLowerCase();
        String secondLastName = user.getSecondLastName().toLowerCase();
        savePrefix(firstName, node);
        savePrefix(secondName, node);
        savePrefix(firstLastName, node);
        savePrefix(secondLastName, node);
    }

    private void savePrefix(String word, Node<User> node){
        List<String> syllabus = SyllableSeparator.split(word);
        for (String syllable : syllabus) {
            if (!nameIndex.containsKey(syllable)) {
                nameIndex.put(syllable, new HashSet<>());
            }
            nameIndex.get(syllable).add(node);
        }
    }

    /**
     * Searches for keys that match the given key.
     *
     * @param name The key to be searched.
     * @return A set of keys that match the given key.
     */
    @Override
    public Set<User> search(String name){
        name = name.toLowerCase();
        String[] words = name.split(" ");
        List<String> syllabus;
        Set<Node<User>> nodes = new HashSet<>();
        for(int i = 0; i < words.length; i++){
            syllabus = SyllableSeparator.split(words[i]);
            for (String syllable : syllabus) {
                if (nameIndex.containsKey(syllable)) {
                    nodes.addAll(nameIndex.get(syllable));
                }
            }
        }
        return getUserFromNodes(nodes, words);
    }

    /**
     * Adds all the keys in the given node to the indexer.
     *
     * @param node The node containing the keys to be added.
     */
    @Override
    public void addAll(Node<User> node) {
        for(int i = 0; i < node.getSize(); i++){
            add(node.getKey(i), node);
        }
    }

    private Set<User> getUserFromNodes(Set<Node<User>> nodes, String[] words){
        Set<User> usersFound = new HashSet<>();
        List<User> users;
        for(Node<User> node : nodes){
            users = new ArrayList<>();
            for(int i = 0; i < node.getSize(); i++){
                users.add(node.getKey(i));
            }
            for(User user : users){
                if (verifyIfContainsWord(user, words)) {
                    usersFound.add(user);
                }
            }
        }
        return usersFound;
    }

    private boolean verifyIfContainsWord(User user , String[] word){
        for (String currentWord : word) {
            String fullName = user.getFullName().toLowerCase();
           if (!fullName.contains(currentWord)) return false;
        }
        return true;
    }


}
