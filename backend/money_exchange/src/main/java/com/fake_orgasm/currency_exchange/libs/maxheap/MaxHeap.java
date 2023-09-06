package com.fake_orgasm.currency_exchange.libs.maxheap;

import java.lang.reflect.Array;
import lombok.Getter;
import lombok.Setter;

/**
 * A Max Heap implementation that allows search data basing on priority values.
 *
 * @param <T> Type of values stored on Heap, must be comparable.
 */
public class MaxHeap<T extends Comparable<T>> {

    /**
     * An array of Nodes of the tree to perform a faster access during the insertions.
     */
    private Node[] nodes;

    /**
     * Current number of elements in the tree, not the number of nodes allocated.
     */
    private int elements;

    /**
     * Stores the current position where a new element must be inserted.
     */
    private int current;

    /**
     * Just a useless flag to understand the code better.
     */
    private static final int FIRST_ELEMENT = 0;

    /**
     * Constructor to create an array given a max size.
     *
     * @param maxSize Is heap capacity.
     */
    public MaxHeap(int maxSize) {
        nodes = (Node[]) Array.newInstance(Node.class, maxSize);
        elements = 0;
        current = 0;
    }

    /**
     * This method gets the element on the root and extracts it from the tree.
     *
     * @return the element on the root or null if the tree is empty.
     */
    public T extract() {
        T element = top();
        nodes[FIRST_ELEMENT] = null;

        if (element != null && elements > 0) {
            elements--;
            current = getParentPos(elements);
            nodes[FIRST_ELEMENT] = nodes[elements];
            nodes[elements] = null;
            resort();
        }
        return element;
    }

    /**
     * Method to obtain left son of a node giving a position.
     * @param parentPos Is parent index.
     * @return Son's position.
     */
    private int getLeftSonPos(int parentPos) {
        return (2 * parentPos) + 1;
    }

    /**
     * Method to obtain right son of a node giving a position.
     *
     * @param parentPos Is parent index.
     * @return Son's position.
     */
    private int getRightSonPos(int parentPos) {

        return (2 * parentPos) + 2;
    }

    /**
     * Method to obtain parent position of any node.
     *
     * @param sonPos Is son index.
     * @return Parent's position.
     */
    private int getParentPos(int sonPos) {
        int parentPos;

        if (sonPos % 2 == 0) {
            parentPos = (sonPos / 2) - 1;
        } else {
            parentPos = sonPos / 2;
        }

        return parentPos;
    }

    /**
     * Adds a new element into the HeapTree in order.
     * <p>
     * Also, if the % of elements is >= RESIZE_FACTOR the heapTree
     * is resized to allocate a bigger quantity of elements.
     *
     * @param element The element to insert.
     */
    public void put(T element) {
        if (current < 0) {
            current = 0;
        }
        if (current == FIRST_ELEMENT && nodes[current] == null) {
            nodes[current] = new Node(element);
            elements++;
        } else {
            int pos = getLeftSonPos(current);
            if (nodes[pos] == null) {
                nodes[pos] = new Node(element);
                nodes[current].leftSon = nodes[pos];
                elements++;
            } else {
                pos = getRightSonPos(current);
                if (nodes[pos] == null) {
                    nodes[pos] = new Node(element);
                    nodes[current].rightSon = nodes[pos];
                    elements++;
                    current++;
                }
            }

            sort(pos);
        }
    }

    /**
     * This method is called when an element is extracted from the tree.
     * <p>
     * When it happens, the last element inserted is moved to the root of the tree
     * having to resort the structure as a consequence.
     */
    private void resort() {
        boolean ordered = false;
        int current = FIRST_ELEMENT;

        while (!ordered) {
            int left = getLeftSonPos(current);
            int right = getRightSonPos(current);

            if (left >= elements) {
                ordered = true;
            } else {
                if (nodes[right] == null || nodes[left].element.compareTo(nodes[right].element) > 0) {

                    if (nodes[current].element.compareTo(nodes[left].element) < 0) {

                        T aux = nodes[current].element;
                        nodes[current].element = nodes[left].element;
                        nodes[left].element = aux;
                        current = left;
                    } else {
                        ordered = true;
                    }
                } else if (nodes[left].element.compareTo(nodes[right].element) < 0) {

                    if (nodes[current].element.compareTo(nodes[right].element) < 0) {
                        T aux = nodes[current].element;
                        nodes[current].element = nodes[right].element;
                        nodes[right].element = aux;
                        current = right;
                    } else {
                        ordered = true;
                    }
                }
            }
        }
    }

    /**
     * @return The number of elements inside the tree.
     */
    public int size() {
        return elements;
    }

    /**
     * Puts the new element inserted in the correct position of the tree.
     *
     * @param addedPos the position of the new element inserted
     *                 <p>
     *                 SuppressWarnings: the default warnings about casts that aren't checked can be ignored...
     */
    private void sort(int addedPos) {
        int parentPos = getParentPos(addedPos);
        boolean ordered = false;

        while (parentPos >= 0 && !ordered) {

            if (nodes[addedPos].element.compareTo(nodes[parentPos].element) > 0) {

                T aux = nodes[addedPos].element;
                nodes[addedPos].element = nodes[parentPos].element;
                nodes[parentPos].element = aux;
                addedPos = parentPos;
            } else {
                ordered = true;
            }

            parentPos = getParentPos(addedPos);
        }
    }

    /**
     * @return the element in the root if it exists, null if not.
     */
    public T top() {
        if (nodes == null || nodes[FIRST_ELEMENT] == null) {
            return null;
        }
        return nodes[FIRST_ELEMENT].element;
    }

    /**
     * Method to implement a way to visualize this class by string.
     * @return Array parsed as string.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < elements; i++) {
            stringBuilder.append(nodes[i].element.toString());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    /**
     * Class to create heap's nodes.
     */
    private final class Node {

        /**
         * Node's element.
         */
        @Getter
        @Setter
        private T element;

        /**
         * Reference to left son.
         */
        @Getter
        @Setter
        private Node leftSon;

        /**
         * Reference to right son.
         */
        @Getter
        @Setter
        private Node rightSon;

        /**
         * Node class to ini beside element.
         * @param element Is node content.
         */
        private Node(T element) {
            this.element = element;
        }
    }
}
