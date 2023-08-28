package com.fake_orgasm.users_management.libs.btree;

/**
 * Utility class for providing methods to work with collections.
 */
public class Utils {

    /**
     * Finds the position to insert a key in a binary search tree node.
     *
     * @param node the node to search in
     * @param key  the key to insert
     * @param <T>  type of data to manage
     * @return the position to insert the key
     */
    public static <T extends Comparable<T>> int findPositionToInsert(Node<T> node, T key) {
        int left = 0;
        int right = node.getSize() - 1;
        int mid;
        int comparison;
        while (left <= right) {
            mid = left + (right - left) / 2;
            comparison = key.compareTo(node.getKey(mid));

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * Finds the position to insert a key in a binary search tree node.
     *
     * @param node the node to search in
     * @param key  the key to insert
     * @param <T>  type of data to manage.
     * @return the position to insert the key
     */
    public static <T extends Comparable<T>> int binarySearch(Node<T> node, T key) {
        int left = 0;
        int right = node.getSize() - 1;
        int mid;
        int comparison;
        int index = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            comparison = key.compareTo(node.getKey(mid));
            if (comparison == 0) {
                index = mid;
                break;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * Shifts keys to the right within a node, starting from the given index.
     *
     * @param node       the node containing keys to be shifted.
     * @param startIndex the index from which keys should be shifted.
     * @param <T>        the type of elements in the node.
     */
    public static <T extends Comparable<T>> void shiftKeysRight(Node<T> node, int startIndex) {
        for (int i = node.getSize() - 1; i >= startIndex; i--) {
            node.setKey(i + 1, node.getKey(i));
        }
    }

    /**
     * Shifts children to the right within a node, starting from the given index.
     *
     * @param node       the node containing children to be shifted.
     * @param startIndex the index from which children should be shifted.
     * @param <T>        the type of elements in the node.
     */
    public static <T extends Comparable<T>> void shiftChildrenRight(Node<T> node, int startIndex) {
        for (int i = node.getSize(); i >= startIndex; i--) {
            node.setChild(i + 1, node.getChild(i));
        }
    }

    /**
     * Shifts keys to the left within a node, starting from the given index.
     *
     * @param node       the node containing keys to be shifted.
     * @param startIndex the index from which keys should be shifted.
     * @param <T>        the type of elements in the node.
     */
    public static <T extends Comparable<T>> void shiftKeysLeft(Node<T> node, int startIndex) {
        for (int i = startIndex + 1; i < node.getSize(); i++) {
            node.setKey(i - 1, node.getKey(i));
        }
    }

    /**
     * Shifts children to the left within a node, starting from the given index.
     *
     * @param node       the node containing children to be shifted.
     * @param startIndex the index from which children should be shifted.
     * @param <T>        the type of elements in the node.
     */
    public static <T extends Comparable<T>> void shiftChildrenLeft(Node<T> node, int startIndex) {
        for (int i = startIndex + 1; i <= node.getSize(); i++) {
            node.setChild(i - 1, node.getChild(i));
        }
    }
}
