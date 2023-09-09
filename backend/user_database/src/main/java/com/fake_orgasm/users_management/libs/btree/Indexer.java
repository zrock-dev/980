package com.fake_orgasm.users_management.libs.btree;

import java.util.Set;

/**
 * This interface represents an indexer for a BTree.
 *
 * @param <T> The type of the keys of the BTree.
 */
public interface Indexer<T extends Comparable<T>> {
    /**
     * Adds a key to the indexer.
     *
     * @param key The key to be added.
     * @param node The node where the key is located.
     */
    void add(T key, Node<T> node);

    /**
     * Searches for keys that match the given key.
     *
     * @param id The key to be searched.
     * @return A set of keys that match the given key.
     */
    Set<T> search(String id);

    /**
     * Adds all the keys in the given node to the indexer.
     *
     * @param node The node containing the keys to be added.
     */
    void addAll(Node<T> node);
}
