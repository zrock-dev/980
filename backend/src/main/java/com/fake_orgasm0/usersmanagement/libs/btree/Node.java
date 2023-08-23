package com.fake_orgasm0.usersmanagement.libs.btree;

import lombok.Getter;

import java.util.Arrays;

/**
 * A Node is a data structure that represents a node in a B-tree.
 * It contains an array of keys, an array of children, and a flag indicating whether it is a leaf or not.
 * It also has a size attribute that indicates the number of keys in the node.
 * A Node of order t has the following properties:
 * - It has at most 2t - 1 keys
 * - It has at least t - 1 keys (except the root)
 * - It has at most 2t children
 * - It has at least t children (except the root and leaves)
 */
@Getter
public class Node<T extends Comparable<T>> {

    private int size;
    private final int order;
    private final T[] keys;
    private final Node<T>[] children;
    private boolean leaf;

    /**
     * Creates a new Node with a given order.
     *
     * @param order the order of the node, must be greater than 2
     */
    @SuppressWarnings(value = "unchecked")
    public Node(int order) {
        if (order <= 2) throw new IllegalArgumentException("Order must be greater than 2");
        this.order = order;
        this.keys = (T[]) new Comparable[2 * order - 1];
        this.children = (Node<T>[]) new Node<?>[2 * order];
        this.leaf = true;
        this.size = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the leaf flag of the node to a given value.
     *
     * @param leaf the value to set the leaf flag to
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }


    /**
     * Returns the key at a given index in the node.
     *
     * @param index the index to get the key from, must be between 0 and size - 1
     * @return the key at the given index
     */
    public T getKey(int index) {
        return keys[index];
    }

    /**
     * Sets the key at a given index in the node to a given value.
     *
     * @param index the index to set the key to, must be between 0 and size - 1
     * @param key   the value to set the key to, must not be null
     */
    public void setKey(int index, T key) {
        this.keys[index] = key;
    }

    /**
     * Returns the child at a given index in the node.
     *
     * @param index the index to get the child from, must be between 0 and size
     * @return the child at the given index
     */
    public Node<T> getChild(int index) {
        return children[index];
    }

    /**
     * Sets the child at a given index in the node to a given value.
     *
     * @param index the index to set the child to, must be between 0 and size
     * @param child the value to set the child to, must not be null
     */
    public void setChild(int index, Node<T> child) {
        children[index] = child;
    }

    /**
     * Finds the index of a given key in the node, or returns -1 if not found.
     *
     * @param key the key to find, must not be null
     * @return the index of the key, or -1 if not found
     */
    public int find(T key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        for (int i = 0; i < size; i++) {
            if (keys[i].compareTo(key) == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This method increases the keys size
     *
     * @return a size increased
     */
    public int increaseSize() {
        size++;
        return size;
    }

    /**
     * This method decreases the keys size
     */
    public void decreaseSize() {
        size--;
    }

    @Override
    public String toString() {
        return Arrays.asList(keys).toString();
    }

}
