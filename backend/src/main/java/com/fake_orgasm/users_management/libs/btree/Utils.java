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
            node.setIdChild(i + 1, node.getIdChild(i));
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
            node.setIdChild(i - 1, node.getIdChild(i));
        }
    }

    /**
     * Reduces the keys in the given parent node starting from the specified position.
     *
     * @param parent the parent node
     * @param pos    the starting position
     * @param <T>    the type of elements to work.
     */

    public static <T extends Comparable<T>> void reduceKeys(Node<T> parent, int pos) {
        for (int i = pos; i < parent.getSize(); i++) {
            parent.setKey(i, parent.getKey(i + 1));
        }

        for (int i = pos + 1; i < parent.getSize() + 1; i++) {
            parent.setChild(i, parent.getChild(i + 1));
            parent.setIdChild(i, parent.getIdChild(i + 1));
        }
    }

    /**
     * Joins two nodes in a binary tree by moving the keys and children of the successor node
     * to the end of the predecessor node's keys and children arrays.
     *
     * @param predecessor the node that comes before the successor node
     * @param successor   the node that comes after the predecessor node
     * @param temp        an integer representing a temporary value used for indexing
     * @param <T>         the type of elements to work.
     */

    public static <T extends Comparable<T>> void joinNodes(Node<T> predecessor, Node<T> successor, int temp) {
        for (int i = 0, j = predecessor.getSize(); i < successor.getSize(); i++) {
            predecessor.setKey(j++, successor.getKey(i));
            predecessor.increaseSize();
        }
        for (int i = 0; i < successor.getSize() + 1; i++) {
            predecessor.setChild(temp, successor.getChild(i));
            predecessor.setIdChild(temp, successor.getIdChild(i));
            temp++;
        }
    }

}
