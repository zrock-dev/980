package com.fake_orgasm.users_management.libs.btree;

import com.fake_orgasm.users_management.repository.IBTreeRepository;
import lombok.Getter;
import org.apache.commons.io.input.ReaderInputStream;

/**
 * A B-tree implementation that allows efficient storage and retrieval of sorted data.
 *
 * @param <T> The type of elements stored in the B-tree, must be comparable.
 */
@Getter
public class BTree<T extends Comparable<T>> {
    /**
     * The repository used by the BTree.
     */
    private IBTreeRepository<T> repository;

    private boolean useRepository;
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
        this.useRepository = false;
    }

    /**
     * Creates a B-tree instance with the specified degree and repository.
     *
     * @param degree     The degree of the B-tree. Must be greater than 1.
     * @param repository The repository used to store and retrieve B-tree nodes.
     * @throws IllegalArgumentException If the degree is not greater than 1.
     */
    public BTree(final int degree, IBTreeRepository<T> repository) {
        if (degree <= 1) {
            throw new IllegalArgumentException("Order must be greater than 1");
        }
        this.repository = repository;
        this.order = degree;
        Node<T> node = repository.readNodeById("root");
        if (node == null) {
            this.root = new Node<>(order);
            this.root.setId("root");
            root.setSize(0);
            root.setLeaf(true);
        } else {
            this.root = node;
        }
        this.useRepository = true;
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
        Node<T> childNode = currNode.getChild(index);
        if (useRepository && childNode == null) {
            childNode = repository.readNodeById(currNode.getIdChild(index));
            currNode.setChild(index, childNode);
        }
        return search(childNode, key);
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
            String aux = node.getId();
            node.setId(rootNode.getId());
            this.root = node;
            rootNode.setId(aux);
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

        if (useRepository) {
            saveNodeData(parent, child, newChild);
        }
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
            if (useRepository && childNode == null) {
                childNode = repository.readNodeById(currentNode.getIdChild(insertionIndex));
                currentNode.setChild(insertionIndex, childNode);
            }
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
        if (useRepository) {
            saveNodeData(leafNode);
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
                if (i != 2 * order - 2) {
                    node.setKey(i, node.getKey(i + 1));
                }
            }
            node.decreaseSize();
            if (useRepository) {
                saveNodeData(node);
            }
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

        if (useRepository && (predecessor == null)) {
            predecessor = repository.readNodeById(parentNode.getIdChild(position));
            parentNode.setChild(position, predecessor);
        }
        if (useRepository && (successor == null)) {
            successor = repository.readNodeById(parentNode.getIdChild(position + 1));
            parentNode.setChild(position + 1, successor);
        }

        if (predecessor.getSize() >= order) {
            handlePredecessorCase(parentNode, position, predecessor);
        } else if (successor.getSize() >= order) {
            handleSuccessorCase(parentNode, position, successor);
        } else {
            handleMergeCase(predecessor, successor, position, parentNode);
            remove(predecessor, key);
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
        if (useRepository) {
            saveNodeData(parentNode, predecessor);
        }
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
        if (useRepository) {
            saveNodeData(parentNode, successor);
        }
    }

    /**
     * Handles the merge case for the B-tree.
     *
     * @param predecessor the predecessor node
     * @param successor   the successor node
     * @param pos         the position of the key
     * @param parent      the parent node
     */

    private void handleMergeCase(final Node<T> predecessor, Node<T> successor, int pos, Node<T> parent) {
        int temp = predecessor.getSize() + 1;
        predecessor.setKey(predecessor.getSize(), predecessor.getKey(pos));
        predecessor.increaseSize();
        for (int i = 0, j = predecessor.getSize(); i < successor.getSize(); i++) {
            predecessor.setKey(j++, successor.getKey(i));
            predecessor.increaseSize();
        }
        for (int i = 0; i < successor.getSize() + 1; i++) {
            predecessor.setChild(temp++, successor.getChild(i));
        }
        parent.setChild(pos, predecessor);

        for (int i = pos; i < parent.getSize(); i++) {
            if (i != 2 * order - 2) {
                parent.setChild(i, parent.getChild(i + 1));
            }
        }
        parent.decreaseSize();
        if (parent.getSize() == 0 && parent == root) {
            root = parent.getChild(0);
            saveNodeData(parent, successor, predecessor);
        } else if (useRepository) {
            saveNodeData(parent, successor, predecessor);
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
        if (node.isLeaf()) {
            return;
        }
        Node<T> tmp = node.getChild(pos);
        if (useRepository && (tmp == null)) {
            tmp = repository.readNodeById(node.getIdChild(pos));
            node.setChild(pos, tmp);
        }

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
     * Merges two child nodes of a parent node at a given position,
     * shifting keys and children accordingly.
     *
     * @param parent the parent node
     * @param pos    the position of the parent node's child nodes to be merged
     * @param key    the key to be removed from the left child node
     */

    private void mergeNodes(Node<T> parent, int pos, T key) {
        if (pos == parent.getSize()) {
            pos--;
        }

        T divider = parent.getKey(pos);
        Node<T> leftChild = parent.getChild(pos);
        Node<T> rightChild = parent.getChild(pos + 1);


        Utils.shiftKeysLeft(parent, pos);
        Utils.shiftChildrenLeft(parent, pos + 1);

        parent.decreaseSize();
        leftChild.getKeys()[leftChild.getSize()] = divider;
        leftChild.increaseSize();
        mergeKeysAndChildren(parent, leftChild, rightChild);

        remove(leftChild, key);

    }

    /**
     * Merges the keys and children of the parent node with the left and right child nodes.
     *
     * @param parent     the parent node
     * @param leftChild  the left child node
     * @param rightChild the right child node
     */

    private void mergeKeysAndChildren(Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
        for (int i = 0, j = leftChild.getSize(); i < rightChild.getSize() + 1; i++, j++) {
            if (i < rightChild.getSize()) {
                leftChild.setKey(j, rightChild.getKey(i));
            }
            leftChild.setChild(j, rightChild.getChild(i));
        }
        leftChild.setSize(leftChild.getSize() + rightChild.getSize());
        if (parent.getSize() == 0 && parent == root) {
            decreaseTree(parent, leftChild, rightChild);
        } else if (useRepository) {
            saveNodeData(parent, leftChild, rightChild);
        }
    }

    /**
     * Decreases the tree by updating the root node, saving node data, and deleting nodes if necessary.
     *
     * @param parent     the parent node
     * @param leftChild  the left child node
     * @param rightChild the right child node
     */

    private void decreaseTree(Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
        root = parent.getChild(0);
        if (useRepository) {
            repository.delete(leftChild);
            repository.delete(parent);
            root.setId("root");
            repository.delete(rightChild);
            saveNodeData(root);
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
        currentChild.increaseSize();
        currentChild.setChild(currentChild.getSize(), rightSibling.getChild(0));


        Utils.shiftKeysLeft(rightSibling, 0);
        Utils.shiftChildrenLeft(rightSibling, 0);
        rightSibling.decreaseSize();
        if (useRepository) {
            saveNodeData(parentNode, currentChild, rightSibling);
        }
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
        leftSibling.decreaseSize();


        Utils.shiftKeysRight(currentChild, 0);
        currentChild.setKey(0, divider);
        Utils.shiftChildrenRight(currentChild, 0);


        currentChild.setChild(0, childToMove);
        currentChild.increaseSize();
        if (useRepository) {
            saveNodeData(parentNode, currentChild, leftSibling);
        }
    }

    /**
     * Checks if redistribution is possible from a right sibling.
     *
     * @param node The current node.
     * @param pos  The position of the key to redistribute.
     * @return {@code true} if redistribution is possible, {@code false} otherwise.
     */
    private boolean canRedistributeFromRightSibling(Node<T> node, int pos) {
        if (pos == node.getSize()) {
            return false;
        }
        Node<T> rightChild = node.getChild(pos + 1);
        if (useRepository && rightChild == null) {
            rightChild = repository.readNodeById(node.getIdChild(pos + 1));
            node.setChild(pos + 1, rightChild);
        }
        return rightChild.getSize() >= order;
    }

    /**
     * Checks if redistribution is possible from a left sibling.
     *
     * @param node The current node.
     * @param pos  The position of the key to redistribute.
     * @return {@code true} if redistribution is possible, {@code false} otherwise.
     */
    private boolean canRedistributeFromLeftSibling(Node<T> node, int pos) {
        if (pos == 0) {
            return false;
        }
        Node<T> leftSibling = node.getChild(pos - 1);
        if (useRepository && leftSibling == null) {
            leftSibling = repository.readNodeById(node.getIdChild(pos - 1));
            node.setChild(pos - 1, leftSibling);
        }
        return leftSibling.getSize() >= order;
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

    /**
     * Saves the data of the given nodes.
     *
     * @param nodes the nodes whose data needs to be saved
     */
    private void saveNodeData(Node<T>... nodes) {
        for (Node<T> node : nodes) {
            for (int i = 0; i < order * 2; i++) {
                if (i<node.getSize()) {
                    repository.delete(node.getChild(i));
                }
            }
            if (node.getSize() == 0) {
                repository.delete(node);
            } else {
                repository.save(node);
            }
        }
    }
}