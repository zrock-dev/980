package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.repository.NodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * A Node is a data structure that represents a node in a B-tree.
 * It contains an array of keys, an array of children,
 * and a flag indicating whether it is a leaf or not.
 * It also has a size attribute that indicates
 * the number of keys in the node.
 * A Node of order t has the following properties:
 * - It has at most 2t - 1 keys
 * - It has at least t - 1 keys (except the root)
 * - It has at most 2t children
 * - It has at least t children (except the root and leaves)
 *
 * @param <T> general type.
 */
@Getter
@Setter
@JsonDeserialize(using = NodeDeserializer.class)
public class Node<T extends Comparable<T>> {
    /**
     * The id of this node.
     */
    private String id;

    /**
     * The number of keys currently stored in the node.
     */
    private int size;

    /**
     * The order of the B-tree node, determining
     * the maximum number of keys the node can hold.
     */
    private final int order;

    /**
     * An array storing the keys contained within the node.
     */
    private final T[] keys;

    /**
     * An array storing the children nodes of the current node.
     */
    private final Node<T>[] children;

    /**
     * An array storing the children references nodes of the current node.
     */
    private String[] idChildren;

    /**
     * A boolean flag indicating whether the node is a leaf node or not.
     */
    private boolean leaf;

    /**
     * Creates a new Node with a given order.
     *
     * @param degree the order of the node, must be greater than 2
     */
    @SuppressWarnings(value = "unchecked")
    public Node(final int degree) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.order = degree;
        this.keys = (T[]) new Comparable[2 * degree - 1];
        this.children = (Node<T>[]) new Node<?>[2 * degree];
        this.leaf = true;
        this.size = 0;
        UUID randomUUID = UUID.randomUUID();
        this.id = randomUUID.toString();
        this.idChildren = new String[2 * degree];
    }

    /**
     * Returns the key at a given index in the node.
     *
     * @param index the index to get the key from,
     *              must be between 0 and size - 1
     * @return the key at the given index
     */
    public T getKey(final int index) {
        return keys[index];
    }

    /**
     * Sets the key at a given index in the node to a given value.
     *
     * @param index the index to set the key to, must be between 0 and size - 1
     * @param key   the value to set the key to, must not be null
     */
    public void setKey(final int index, final T key) {
        this.keys[index] = key;
    }

    /**
     * Returns the child at a given index in the node.
     *
     * @param index the index to get the child from, must be between 0 and size
     * @return the child at the given index
     */
    public Node<T> getChild(final int index) {
        return children[index];
    }

    /**
     * Retrieves the ID of a child at the specified index.
     *
     * @param index the index of the child
     * @return the ID of the child at the specified index
     */
    public String getIdChild(int index) {
        return idChildren[index];
    }

    /**
     * Sets the child at a given index in the node to a given value.
     *
     * @param index the index to set the child to, must be between 0 and size
     * @param child the value to set the child to, must not be null
     */
    public void setChild(final int index, final Node<T> child) {
        children[index] = child;
        if (child != null) {
            idChildren[index] = child.getId();
        }
    }

    /**
     * Finds the index of a given key in the node, or returns -1 if not found.
     *
     * @param key the key to find, must not be null
     * @return the index of the key, or -1 if not found
     */
    public int find(final T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (keys[i].compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method increases the keys size.
     *
     * @return a size increased.
     */
    public int increaseSize() {
        size++;
        return size;
    }

    /**
     * This method decreases the keys size.
     */
    public void decreaseSize() {
        size--;
    }

    /**
     * Object visualization.
     *
     * @return string to visualize the object.
     */
    @Override
    public String toString() {
        return Arrays.asList(keys).toString() + size;
    }

    /**
     * Prints the structure of the B-tree starting from this node.
     *
     * @param prefix The prefix string for indentation in the printed output.
     */
    public void printTree(String prefix) {
        System.out.println(prefix + "|__ " + new ArrayList(Arrays.asList(keys)) + " " + size);
        Node<T> node;
        for (int i = 0; i <= size; i++) {
            String childPrefix = prefix + (i == size - 1 ? "    " : "|   ");
            node = this.children[i];
            if (node != null) {
                node.printTree(childPrefix);
            }
        }
    }

    /**
     * Sets the ID of a child at the specified index in the idChildren array.
     *
     * @param i  the index of the child
     * @param id the ID of the child
     */
    public void setIdChild(int i, String id) {
        idChildren[i] = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}