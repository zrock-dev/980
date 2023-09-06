package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.models.User;

import java.util.*;

public class NameIndexer {
    private Map<String, Set<Node<User>>> nameIndex;

    public NameIndexer() {
        nameIndex = new HashMap<>();
    }

    public void addUser(User user, Node<User> node){
        String firstName = user.getFirstName();
        String secondName = user.getSecondName();
        String firstLastName = user.getFirstLastName();
        String secondLastName = user.getSecondLastName();
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

    public Set<User> searchName(String name){
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

    private Set<User> getUserFromNodes(Set<Node<User>> nodes, String[] words){
        Set<User> usersFound = new HashSet<>();
        for(Node<User> node : nodes){
            for(User user : node.getKeys()){
                if (user.getFirstName().contains(words[0]) || user.getFirstName().contains(words[1])) {
                    usersFound.add(user);
                }else if (user.getSecondName().contains(words[0]) || user.getSecondName().contains(words[1])) {
                    usersFound.add(user);
                }else if (user.getFirstLastName().contains(words[0]) || user.getFirstLastName().contains(words[1])) {
                    usersFound.add(user);
                }else if (user.getSecondLastName().contains(words[0]) || user.getSecondLastName().contains(words[1])) {
                    usersFound.add(user);
                }
            }
        }
        return usersFound;
    }
}
