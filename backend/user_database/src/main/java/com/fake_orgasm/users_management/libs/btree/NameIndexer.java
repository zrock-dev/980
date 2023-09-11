package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.IndexerRepository;
import com.fake_orgasm.users_management.services.UserManager;
import java.util.*;
import java.util.concurrent.*;

public class NameIndexer implements Indexer<User> {
    private Map<String, Set<String>> nameIndex;
    private Map<String, Node<User>> nodes;
    private IndexerRepository indexerRepository;

    /**
     * Constructor of the class.
     */
    public NameIndexer() {
        nameIndex = new HashMap<>();
        nodes = new HashMap<>();
        indexerRepository = new IndexerRepository();
    }

    /**
     * Adds a key to the indexer.
     *
     * @param user The key to be added.
     * @param node The node where the key is located.
     */
    @Override
    public void add(User user, Node<User> node) {
        String firstName = user.getFirstName().toLowerCase();
        String secondName = user.getSecondName().toLowerCase();
        String firstLastName = user.getFirstLastName().toLowerCase();
        String secondLastName = user.getSecondLastName().toLowerCase();
        savePrefix(firstName, node);
        savePrefix(secondName, node);
        savePrefix(firstLastName, node);
        savePrefix(secondLastName, node);
    }

    private void savePrefix(String word, Node<User> node) {
        List<String> syllabus = SyllableSeparator.split(word);
        for (String syllable : syllabus) {
            if (!nameIndex.containsKey(syllable)) {
                nameIndex.put(syllable, new HashSet<>());
            }
            nameIndex.get(syllable).add(node.getId());
            indexerRepository.save(syllable, node.getId());
            nodes.put(node.getId(), node);
        }
    }

    /**
     * Searches for keys that match the given key.
     *
     * @param name The key to be searched.
     * @return A set of keys that match the given key.
     */
    @Override
    public Set<User> search(String name) {
        name = name.toLowerCase();
        String[] words = name.split(" ");
        List<String> syllabus;
        Set<Node<User>> nodes = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            syllabus = SyllableSeparator.split(words[i]);
            for (String syllable : syllabus) {
                if (!nameIndex.containsKey(syllable)) {
                    Map<String, Set<String>> key = indexerRepository.getMap(syllable);
                    if (key == null) {
                        continue;
                    }
                    nameIndex.putAll(key);
                }
                List<Node<User>> nodesFound = getNodes(nameIndex.get(syllable));
                nodes.addAll(nodesFound);
            }
        }
        return threadSearcher(nodes, words);
    }

    private List<Node<User>> getNodes(Set<String> idNodes) {
        List<Node<User>> nodesReturned = new ArrayList<>();
        for (String id : idNodes) {
            if (!nodes.containsKey(id)) {
                nodes.put(id, UserManager.getBTree().getRepository().readNodeById(id));
            }
            nodesReturned.add(nodes.get(id));
        }
        return nodesReturned;
    }

    private Set<User> threadSearcher(Set<Node<User>> nodes, String[] words) {
        List<UserSearcher> userSearchers = createThreads(nodes, words);
        ExecutorService executor = Executors.newFixedThreadPool(userSearchers.size());
        List<Future<Set<User>>> results = new ArrayList<>();
        for (int i = 0; i < userSearchers.size(); i++) {
            Callable<Set<User>> task = userSearchers.get(i);
            Future<Set<User>> result = executor.submit(task);
            results.add(result);
        }
        executor.shutdown();
        Set<User> usersFound = new HashSet<>();
        for (Future<Set<User>> result : results) {
            try {
                Set<User> resultSet = result.get();
                if (resultSet != null) {
                    usersFound.addAll(resultSet);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return usersFound;
    }

    private List<UserSearcher> createThreads(Set<Node<User>> nodes, String[] words) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        List<UserSearcher> userSearchers = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            userSearchers.add(new UserSearcher(words));
        }
        int counterThreads = 0;
        for (Node<User> node : nodes) {
            userSearchers.get(counterThreads).addNodeToCalculate(node);
            counterThreads++;
            if (counterThreads == userSearchers.size()) {
                counterThreads = 0;
            }
        }
        return userSearchers;
    }

    /**
     * Adds all the keys in the given node to the indexer.
     *
     * @param node The node containing the keys to be added.
     */
    @Override
    public void addAll(Node<User> node) {
        for (int i = 0; i < node.getSize(); i++) {
            add(node.getKey(i), node);
        }
    }
}
