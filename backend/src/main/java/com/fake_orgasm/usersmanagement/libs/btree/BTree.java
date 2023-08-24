package com.fake_orgasm.usersmanagement.libs.btree;

import lombok.Getter;

/**
 * A B-tree implementation that allows efficient storage and retrieval of sorted data.
 *
 * @param <T> The type of elements stored in the B-tree, must be comparable.
 */
@Getter
public class BTree<T extends Comparable<T>> {

    /**
     * The order of the B-tree node.
     */
    private final int order;
    /**
     * The root node of the B-tree.
     */
    private Node<T> root;

    /**
     * Constructs a BTree object with the specified degree.
     *
     * @param degree The degree of the BTree.
     *               Must be a positive integer greater than 2.
     * @throws IllegalArgumentException If degree is than 2.
     */
    public BTree(final int degree) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.order = degree;
        this.root = new Node<>(degree);
        this.root.setSize(0);
        this.root.setLeaf(true);
    }

    /**
     * Searches for a specific key in the B-tree.
     *
     * @param node The current node being searched.
     * @param key  The key to search for.
     * @return The node containing the key or null if not found.
     */
    private Node<T> search(final Node<T> node, final T key) {
        if (node == null) {
            return null;
        }

        int i;
        for (i = 0; i < node.getSize(); i++) {
            if (key.compareTo(node.getKey(i)) < 0) {
                break;
            }
            if (key.compareTo(node.getKey(i)) == 0) {
                return node;
            }
        }

        if (node.isLeaf()) {
            return null;
        } else {
            return search(node.getChild(i), key);
        }
    }

    /**
     * Searches for a specific key in the B-tree.
     *
     * @param key The key to search for.
     * @return The key if found, otherwise null.
     * Returns null also if the key is found
     * but its associated node's value is null.
     * @throws IllegalArgumentException If the provided key is null.
     */
    public T searchKey(final T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<T> node = search(root, key);
        if (node == null) {
            return null;
        }
        for (int i = 0; i < node.getSize(); i++) {
            if (node.getKey(i).compareTo(key) == 0) {
                return node.getKey(i);
            }
        }
        return null;
    }

    /**
     * Splits a node during insertion in the B-tree.
     *
     * @param x   The parent node where the split is occurring.
     * @param pos The position where the split is occurring.
     * @param y   The child node being split.
     */
    private void split(final Node<T> x, final int pos, final Node<T> y) {
        Node<T> z = new Node<>(order);
        z.setLeaf(y.isLeaf());
        z.setSize(order - 1);
        for (int j = 0; j < order - 1; j++) {
            z.setKey(j, y.getKey(j + order));
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < order; j++) {
                z.setChild(j, y.getChild(j + order));
            }
        }
        y.setSize(order - 1);
        for (int j = x.getSize(); j >= pos + 1; j--) {
            x.setChild(j + 1, x.getChild(j));
        }
        x.setChild(pos + 1, z);
        for (int j = x.getSize() - 1; j >= pos; j--) {
            x.setKey(j + 1, x.getKey(j));
        }
        x.setKey(pos, y.getKey(order - 1));
        x.setSize(x.increaseSize());
    }

    /**
     * Inserts a key into the B-tree.
     * The B-tree structure might be
     * modified to accommodate the new key.
     *
     * @param key The key to insert.
     * @throws IllegalArgumentException If the provided key is null.
     */
    public void insert(final T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<T> r = root;
        if (r.getSize() == 2 * order - 1) {
            Node<T> node = new Node<>(order);
            root = node;
            node.setLeaf(false);
            node.setSize(0);
            node.setChild(0, r);
            split(node, 0, r);
            insertIntoSubtree(node, key);
        } else {
            insertIntoSubtree(r, key);
        }
    }

    /**
     * Inserts a new key into the B-tree starting
     * from the specified subtree node.
     *
     * @param key  The key to be inserted.
     * @param node The starting node of the subtree.
     */
    private void insertIntoSubtree(final Node<T> node, final T key) {

        if (node.isLeaf()) {
            int i = node.getSize() - 1;
            while (i >= 0 && key.compareTo(node.getKey(i)) < 0) {
                node.setKey(i + 1, node.getKey(i));
                i--;
            }

            node.setKey(i + 1, key);
            node.setSize(node.increaseSize());
        } else {
            int i = node.getSize() - 1;
            while (i >= 0 && key.compareTo(node.getKey(i)) < 0) {
                i--;
            }
            i++;
            Node<T> tmp = node.getChild(i);
            if (tmp.getSize() == 2 * order - 1) {
                split(node, i, tmp);
                if (key.compareTo(node.getKey(i)) > 0) {
                    i++;
                }
            }
            insertIntoSubtree(node.getChild(i), key);
        }
    }

    /**
     * Prints the entire B-tree structure by recursively
     * traversing and printing each node's keys.
     * The tree is printed in an indented format to show hierarchy.
     */
    public void printTree() {
        this.printTree(root);
    }

    /**
     * Prints the keys of the B-tree in a depth-first manner.
     *
     * @param x The node from which to start printing.
     */
    public void printTree(final Node<T> x) {
        if (x == null) {
            return;
        }
        for (int i = 0; i < x.getSize(); i++) {
            System.out.print(x.getKey(i) + " ");
        }
        if (!x.isLeaf()) {
            for (int i = 0; i < x.getSize() + 1; i++) {
                printTree(x.getChild(i));
            }
        }
    }

    /**
     * Removes a key from the B-tree starting from the given node.
     *
     * @param node The current node being considered.
     * @param key  The key to remove.
     */
    private void remove(final Node<T> node, final T key) {
        int pos = node.find(key);
        if (pos != -1) {
            if (node.isLeaf()) {
                removeKeyFromLeaf(node, pos);
            } else {
                remFromNonLeaf(node, pos, key);
            }
        } else {
            removeKeyFromChild(node, key);
        }
    }

    /**
     * Removes a key from a leaf node of the B-tree.
     *
     * @param node The leaf node from which to remove the key.
     * @param pos  The position of the key to be removed in the node.
     */
    private void removeKeyFromLeaf(Node<T> node, int pos) {
        for (int i = pos; i < node.getSize() - 1; i++) {
            node.setKey(i, node.getKey(i + 1));
        }
        node.decreaseSize();
    }

    /**
     * Removes a key from a non-leaf node of the B-tree.
     *
     * @param node The non-leaf node from which to remove the key.
     * @param pos  The position of the key to be removed in the node.
     * @param key  The key to be removed.
     */
    private void remFromNonLeaf(Node<T> node, int pos, T key) {
        Node<T> pred = node.getChild(pos);
        if (pred.getSize() >= order) {
            T predKey = findPredecessorKey(pred);
            remove(pred, predKey);
            node.setKey(pos, predKey);
        } else {
            Node<T> nextNode = node.getChild(pos + 1);
            if (nextNode.getSize() >= order) {
                T nextKey = findSuccessorKey(nextNode);
                remove(nextNode, nextKey);
                node.setKey(pos, nextKey);
            } else {
                mergeNodes(node, pos, pred, nextNode);
                remove(pred, key);
            }
        }
    }

    /**
     * Removes a key from a child node of the B-tree node.
     *
     * @param node The parent node containing the child node.
     * @param key  The key to be removed from the child node.
     */
    private void removeKeyFromChild(final Node<T> node, final T key) {
        int pos;
        for (pos = 0; pos < node.getSize(); pos++) {
            if (node.getKey(pos).compareTo(key) > 0) {
                break;
            }
        }
        Node<T> child = node.getChild(pos);
        if (child.getSize() >= order) {
            remove(child, key);
        } else {
            handleUnderflow(node, child, pos);
            remove(child, key);
        }
    }

    /**
     * Finds the predecessor key of a given node in the B-tree.
     *
     * @param node The node for which to find the predecessor key.
     * @return The predecessor key of the given node.
     */
    private T findPredecessorKey(final Node<T> node) {
        Node<T> predNode = node;
        while (!predNode.isLeaf()) {
            predNode = predNode.getChild(predNode.getSize());
        }
        return predNode.getKey(predNode.getSize() - 1);
    }

    /**
     * Finds the successor key of a given node in the B-tree.
     *
     * @param node The node for which to find the successor key.
     * @return The successor key of the given node.
     */
    private T findSuccessorKey(final Node<T> node) {
        Node<T> sucNode = node;
        while (!sucNode.isLeaf()) {
            sucNode = sucNode.getChild(0);
        }
        return sucNode.getKey(0);
    }

    /**
     * Merges two nodes and updates keys in the parent node.
     *
     * @param parent     The parent node containing the merging nodes.
     * @param pos        The position of the leftChild in the parent.
     * @param leftChild  The left child node to merge.
     * @param rightChild The right child node to merge.
     */
    private void mergeNodes(Node<T> parent, int pos, Node<T> leftChild, Node<T> rightChild) {
        leftChild.setKey(leftChild.getSize(), parent.getKey(pos));
        int index;
        for (int i = 0; i < rightChild.getSize(); i++) {
            index = leftChild.getSize() + 1 + i;
            leftChild.setKey(index, rightChild.getKey(i));
        }
        for (int i = 0; i <= rightChild.getSize(); i++) {
            index = leftChild.getSize() + 1 + i;
            leftChild.setChild(index, rightChild.getChild(i));
        }
        leftChild.setSize(leftChild.getSize() + 1 + rightChild.getSize());
        int removeIndex = pos + 1;
        while (parent.getSize() > removeIndex) {
            parent.setChild(removeIndex, parent.getChild(removeIndex + 1));
        }
        parent.decreaseSize();
        if (parent.getSize() == 0 && parent == root) {
            root = leftChild;
        }
    }

    /**
     * Handles the underflow condition in a parent node and its child nodes.
     *
     * @param parent The parent node with underflow condition.
     * @param child  The child node with underflow condition.
     * @param pos    The position of the child node in the parent.
     */
    private void handleUnderflow(final Node<T> parent, final Node<T> child, final int pos) {
        Node<T> leftSibling = pos > 0 ? parent.getChild(pos - 1) : null;
        Node<T> rightSibling = null;
        if (pos < parent.getSize()) {
            rightSibling = parent.getChild(pos + 1);
        }

        if (leftSibling != null && leftSibling.getSize() >= order) {
            redLeftSib(parent, child, leftSibling, pos);
        } else if (rightSibling != null && rightSibling.getSize() >= order) {
            redRightSib(parent, child, rightSibling, pos);
        } else if (leftSibling != null) {
            mergeNodes(parent, pos - 1, leftSibling, child);
        } else if (rightSibling != null) {
            mergeNodes(parent, pos, child, rightSibling);
        }
    }

    /**
     * Redistributes keys from a left sibling node to a child node.
     *
     * @param parent      The parent node containing
     *                    the child and sibling nodes.
     * @param child       The child node receiving redistributed keys.
     * @param leftSibling The left sibling node providing keys.
     * @param pos         The position of the child node in the parent.
     */
    private void redLeftSib(Node<T> parent, Node<T> child, Node<T> leftSibling, int pos) {
        for (int i = child.getSize(); i > 0; i--) {
            child.setKey(i, child.getKey(i - 1));
        }
        child.setKey(0, parent.getKey(pos - 1));
        parent.setKey(pos - 1, leftSibling.getKey(leftSibling.getSize() - 1));
        if (!child.isLeaf()) {
            for (int i = child.getSize() + 1; i > 0; i--) {
                child.setChild(i, child.getChild(i - 1));
            }
            child.setChild(0, leftSibling.getChild(leftSibling.getSize()));
        }
        child.increaseSize();
        leftSibling.decreaseSize();
    }

    /**
     * Redistributes keys from a right sibling
     * node to a child node.
     *
     * @param parent   The parent node containing the child
     *              and sibling nodes.
     * @param child The child node receiving redistributed keys.
     * @param rightChild The right sibling node providing keys.
     * @param pos   The position of the child node in the parent.
     */
    private void redRightSib(Node<T> parent, Node<T> child, Node<T> rightChild, int pos) {
        child.setKey(child.getSize(), parent.getKey(pos));
        parent.setKey(pos, rightChild.getKey(0));
        for (int i = 0; i < rightChild.getSize() - 1; i++) {
            rightChild.setKey(i, rightChild.getKey(i + 1));
        }
        if (!rightChild.isLeaf()) {
            child.setChild(child.getSize() + 1, rightChild.getChild(0));
            for (int i = 0; i < rightChild.getSize(); i++) {
                rightChild.setChild(i, rightChild.getChild(i + 1));
            }
        }
        child.increaseSize();
        rightChild.decreaseSize();
    }

    /**
     * Removes a key from the B-tree if it exists.
     *
     * @param key The key to remove.
     * @return True if the key was found and removed, false otherwise.
     * @throws IllegalArgumentException If the provided key is null.
     */
    public boolean remove(final T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<T> x = search(root, key);
        if (x == null) {
            return false;
        }
        remove(root, key);
        return true;
    }
}
