package com.fake_orgasm.users_management.libs.btree;

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
     * @param currNode The current node being searched.
     * @param key      The key to search for.
     * @return The node containing the key or null if not found.
     */
    private Node<T> search(final Node<T> currNode, final T key) {
        if (currNode == null) {
            return null;
        }

        int index = Utils.findPositionToInsert(currNode, key);

        if (index < currNode.getSize() && key.compareTo(currNode.getKey(index)) == 0) {
            return currNode;
        }

        if (currNode.isLeaf()) {
            return null;
        }

        return search(currNode.getChild(index), key);
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
        if (node != null) {
            int index = Utils.binarySearch(node, key);
            if (index != -1) {
                return node.getKey(index);
            }
        }
        return null;
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
        Node<T> rootNode = this.root;
        if (rootNode.getSize() == 2 * order - 1) {
            Node<T> node = new Node<>(order);
            this.root = node;
            node.setLeaf(false);
            node.setSize(0);
            node.setChild(0, rootNode);
            split(node, 0, rootNode);
            insertIntoSubtree(node, key);
        } else {
            insertIntoSubtree(rootNode, key);
        }
    }

    /**
     * Splits a node during insertion in the B-tree.
     *
     * @param parent   The parent node where the split is occurring.
     * @param position The position where the split is occurring.
     * @param child    The child node being split.
     */
    private void split(Node<T> parent, int position, Node<T> child) {
        Node<T> newChild = new Node<>(order);
        newChild.setLeaf(child.isLeaf());
        newChild.setSize(order - 1);

        moveDataToNewNode(child, newChild);
        moveDataToParentNode(parent, position, newChild);

        parent.setKey(position, child.getKey(order - 1));
        parent.setSize(parent.getSize() + 1);
    }

    /**
     * Moves data from a node to a new node.
     *
     * @param node    the node to move data from
     * @param newNode the new node to move data to
     */
    private void moveDataToNewNode(Node<T> node, Node<T> newNode) {
        for (int j = 0; j < order - 1; j++) {
            newNode.setKey(j, node.getKey(j + order));
        }
        if (!node.isLeaf()) {
            for (int j = 0; j < order; j++) {
                newNode.setChild(j, node.getChild(j + order));
            }
        }
        node.setSize(order - 1);
    }

    /**
     * Moves data from a node to its parent node.
     *
     * @param parent   the parent node to move data to
     * @param position the position of the new child node in the parent's children list
     * @param newNode  the new child node to add to the parent's children list
     */
    private void moveDataToParentNode(Node<T> parent, int position, Node<T> newNode) {
        for (int j = parent.getSize(); j >= position + 1; j--) {
            parent.setChild(j + 1, parent.getChild(j));
        }
        parent.setChild(position + 1, newNode);

        for (int j = parent.getSize() - 1; j >= position; j--) {
            parent.setKey(j + 1, parent.getKey(j));
        }
    }

    /**
     * Inserts a new key into the B-tree starting
     * from the specified subtree node.
     *
     * @param key         The key to be inserted.
     * @param currentNode The starting node of the subtree.
     */
    private void insertIntoSubtree(final Node<T> currentNode, final T key) {
        if (currentNode.isLeaf()) {
            insertIntoLeaf(currentNode, key);
        } else {
            int insertionIndex = Utils.findPositionToInsert(currentNode, key);
            Node<T> childNode = currentNode.getChild(insertionIndex);
            if (childNode.getSize() == 2 * order - 1) {
                split(currentNode, insertionIndex, childNode);
                if (key.compareTo(currentNode.getKey(insertionIndex)) > 0) {
                    insertionIndex++;
                }
            }
            insertIntoSubtree(currentNode.getChild(insertionIndex), key);
        }
    }

    /**
     * Inserts a key into a leaf node of the B-tree.
     *
     * @param leafNode The leaf node where the key will be inserted.
     * @param key      The key to be inserted into the leaf node.
     */
    private void insertIntoLeaf(Node<T> leafNode, T key) {
        int insertionIndex = leafNode.getSize() - 1;
        while (insertionIndex >= 0 && key.compareTo(leafNode.getKey(insertionIndex)) < 0) {
            leafNode.setKey(insertionIndex + 1, leafNode.getKey(insertionIndex));
            insertionIndex--;
        }
        leafNode.setKey(insertionIndex + 1, key);
        leafNode.setSize(leafNode.increaseSize());
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
    private void remove(Node<T> node, T key) {
        int pos = node.find(key);
        if (pos != -1) {
            if (node.isLeaf()) {
                removeKeyFromLeaf(node, key);
            } else {
                removeFromNonLeaf(node, pos, key);
            }
        } else {
            removeKeyFromChild(node, key);
        }
    }

    /**
     * Removes a key from a leaf node of the B-tree.
     *
     * @param node The leaf node from which to remove the key.
     * @param key
     */
    private void removeKeyFromLeaf(Node<T> node, T key) {
        int keyIndex = Utils.findPositionToInsert(node, key);
        if (keyIndex != -1) {
            for (int i = keyIndex; i < node.getSize() - 1; i++) {
                node.setKey(i, node.getKey(i + 1));
            }
            node.decreaseSize();
        }
    }

    /**
     * Removes a key from a non-leaf node of the B-tree.
     *
     * @param parentNode The non-leaf node from which to remove the key.
     * @param position   The position of the key to be removed in the node.
     * @param key        The key to be removed.
     */
    private void removeFromNonLeaf(Node<T> parentNode, int position, T key) {
        Node<T> predecessor = parentNode.getChild(position);
        Node<T> successor = parentNode.getChild(position + 1);

        if (predecessor.getSize() >= order) {
            handlePredecessorCase(parentNode, position, predecessor);
        } else if (successor.getSize() >= order) {
            handleSuccessorCase(parentNode, position, successor);
        } else {
            handleMergeCase(predecessor, successor, key);
        }
    }

    /**
     * Handles the case when the predecessor key is used to replace a key in
     * the parent node during deletion.
     *
     * @param parentNode  the parent node containing the key to be replaced.
     * @param position    the position of the key to be replaced.
     * @param predecessor the predecessor node from which the key will be taken.
     */
    private void handlePredecessorCase(Node<T> parentNode, int position, Node<T> predecessor) {
        T predecessorKey = findPredecessorKey(predecessor);
        remove(predecessor, predecessorKey);
        parentNode.setKey(position, predecessorKey);
    }

    /**
     * Handles the case when the successor key is used to replace a key in
     * the parent node during deletion.
     *
     * @param parentNode the parent node containing the key to be replaced.
     * @param position   the position of the key to be replaced.
     * @param successor  the successor node from which the key will be taken.
     */
    private void handleSuccessorCase(Node<T> parentNode, int position, Node<T> successor) {
        T successorKey = findSuccessorKey(successor);
        remove(successor, successorKey);
        parentNode.setKey(position, successorKey);
    }

    /**
     * Handles the case when two nodes are merged during deletion.
     *
     * @param predecessor the node that comes before the successor node.
     * @param successor   the node that comes after the predecessor node.
     * @param key         the key that was deleted to trigger the merge.
     */
    private void handleMergeCase(Node<T> predecessor, Node<T> successor, T key) {
        mergeNodes(predecessor, successor);
        remove(predecessor, key);
    }

    /**
     * Merges two nodes, transferring keys and children from the right node to the left node.
     *
     * @param leftNode  the node that will receive the keys and children.
     * @param rightNode the node that will be merged into the left node.
     */
    private void mergeNodes(Node<T> leftNode, Node<T> rightNode) {
        leftNode.getKeys()[leftNode.increaseSize()] = rightNode.getKeys()[0];
        for (int i = 0; i < rightNode.getSize(); i++) {
            leftNode.getKeys()[leftNode.increaseSize()] = rightNode.getKeys()[i];
        }
        for (int i = 0; i <= rightNode.getSize(); i++) {
            leftNode.getChildren()[leftNode.increaseSize()] = rightNode.getChildren()[i];
        }
    }

    /**
     * Removes a key from a child node of the B-tree node.
     *
     * @param node The parent node containing the child node.
     * @param key  The key to be removed from the child node.
     */
    private void removeKeyFromChild(Node<T> node, T key) {
        int pos = Utils.findPositionToInsert(node, key);
        Node<T> tmp = node.getChildren()[pos];
        if (tmp.getSize() >= this.order) {
            remove(tmp, key);
        } else {
            if (canRedistributeFromRightSibling(node, pos)) {
                redFromRightSibling(node, pos, tmp);
            } else if (canRedistributeFromLeftSibling(node, pos)) {
                reFromLeftSibling(node, pos, tmp);
            } else {
                mergeNodes(node, pos, key);
                return;
            }
            remove(tmp, key);
        }
    }

    /**
     * Merges a node with its children at the specified position and key.
     *
     * @param parent   The node to merge.
     * @param position The position of the child to merge.
     * @param key      The key to merge with.
     */
    private void mergeNodes(Node<T> parent, int position, T key) {
        if (position == parent.getSize()) {
            position--;
        }
        T divider = parent.getKeys()[position];
        Node<T> leftChild = parent.getChildren()[position];
        Node<T> rightChild = parent.getChildren()[position + 1];

        Utils.shiftKeysLeft(parent, position);
        Utils.shiftChildrenLeft(parent, position + 1);
        parent.decreaseSize();
        leftChild.getKeys()[leftChild.getSize() + 1] = divider;

        mergeKeysAndChildren(parent, leftChild, rightChild);
        remove(leftChild, key);
    }

    private void mergeKeysAndChildren(Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
        for (int i = 0, j = leftChild.getSize(); i < rightChild.getSize() + 1; i++, j++) {
            if (i < rightChild.getSize()) {
                leftChild.setKey(j, rightChild.getKey(i));
            }
            leftChild.setChild(j, rightChild.getChild(i));
        }
        leftChild.setSize(leftChild.getSize() + rightChild.getSize());
        if (parent.getSize() == 0 && parent == root) {
            root = parent.getChildren()[0];
        }
    }

    /**
     * Redistributes keys and children from a right sibling
     * to the current node and a temporary node.
     *
     * @param parentNode   The current node.
     * @param position     The position of the key to redistribute.
     * @param currentChild The temporary node.
     */
    private void redFromRightSibling(Node<T> parentNode, int position, Node<T> currentChild) {
        T divider = parentNode.getKey(position);
        Node<T> rightSibling = parentNode.getChild(position + 1);

        parentNode.setKey(position, rightSibling.getKey(0));
        currentChild.setKey(currentChild.getSize(), divider);
        currentChild.setChild(currentChild.getSize() + 1, rightSibling.getChild(0));

        Utils.shiftKeysLeft(rightSibling, 0);
        Utils.shiftChildrenLeft(rightSibling, 0);

        rightSibling.decreaseSize();
    }

    /**
     * Redistributes keys and children from a left sibling to the current node
     * and a temporary node.
     *
     * @param parentNode   The current node.
     * @param position     The position of the key to redistribute.
     * @param currentChild The temporary node.
     */
    private void reFromLeftSibling(Node<T> parentNode, int position, Node<T> currentChild) {
        T divider = parentNode.getKey(position - 1);
        Node<T> leftSibling = parentNode.getChild(position - 1);

        parentNode.setKey(position - 1, leftSibling.getKey(leftSibling.getSize() - 1));

        Node<T> childToMove = leftSibling.getChild(leftSibling.getSize());
        currentChild.setChild(currentChild.getSize() + 1, childToMove);
        leftSibling.setChild(leftSibling.getSize(), null);
        currentChild.setKey(0, divider);

        Utils.shiftKeysRight(currentChild, 0);
        Utils.shiftChildrenRight(currentChild, 0);

        leftSibling.decreaseSize();
        currentChild.increaseSize();
    }

    /**
     * Checks if redistribution is possible from a right sibling.
     *
     * @param node The current node.
     * @param pos  The position of the key to redistribute.
     * @return {@code true} if redistribution is possible, {@code false} otherwise.
     */
    private boolean canRedistributeFromRightSibling(Node<T> node, int pos) {
        return pos != node.getSize() && node.getChildren()[pos + 1].getSize() >= order;
    }

    /**
     * Checks if redistribution is possible from a left sibling.
     *
     * @param node The current node.
     * @param pos  The position of the key to redistribute.
     * @return {@code true} if redistribution is possible, {@code false} otherwise.
     */
    private boolean canRedistributeFromLeftSibling(Node<T> node, int pos) {
        return pos != 0 && node.getChildren()[pos - 1].getSize() >= order;
    }

    /**
     * Finds the predecessor key of a given node in the B-tree.
     *
     * @param node The node for which to find the predecessor key.
     * @return The predecessor key of the given node.
     */
    private T findPredecessorKey(Node<T> node) {
        while (!node.isLeaf()) {
            node = node.getChild(node.getSize());
        }
        return node.getKey(node.getSize() - 1);
    }

    /**
     * Finds the successor key of a given node in the B-tree.
     *
     * @param node The node for which to find the successor key.
     * @return The successor key of the given node.
     */
    private T findSuccessorKey(Node<T> node) {
        while (!node.isLeaf()) {
            node = node.getChild(0);
        }
        return node.getKey(0);
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
