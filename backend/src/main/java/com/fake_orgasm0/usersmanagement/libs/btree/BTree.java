package com.fake_orgasm0.usersmanagement.libs.btree;


import static com.fake_orgasm0.usersmanagement.libs.btree.Utils.splice;

public class BTree<T extends Comparable> {
    private Node<T> root;
    private int maxKeys;
    private int minKeys;
    private int middle;
    private int size;
    private int height;

    public BTree(int order) {
        if (order <= 2) throw new IllegalArgumentException("Order must be greater than 2");

        this.maxKeys = order - 1;
        this.middle = maxKeys / 2;
        this.minKeys = (order / 2);
        this.size = 0;
        this.height = 0;
    }

    public void insert(T key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        size++;
        if (root == null || size == 0) {
            root = new Node<>(key);
        } else {
            insertHelper(key, root);
        }
    }

    private void insertHelper(T key, Node<T> node) {
        int indexInsert = Utils.getIndex(node.getKeys(), key);
        if (node.isLeaf()) {
            node.getKeys().add(indexInsert, key);
            balance(node);
        } else {
            insertHelper(key, node.getChild(indexInsert));
        }
    }


    private void balance(Node<T> node) {
        if (node == null || maxKeys >= node.getKeysSize()) return;
        goUpKey(node, splitToSiblingNode(node), node.getKeys().remove(middle));
        balance(node.getParent());
    }

    private Node<T> splitToSiblingNode(Node<T> node) {
        Node<T> siblingNode = new Node<>(splice(node.getKeys(), middle + 1), node.getParent());
        reassignChildren(node, siblingNode, middle + 1);
        return siblingNode;
    }


    private void goUpKey(Node<T> leftNode, Node<T> rightNode, T key) {
        if (leftNode.getParent() != null) {
            int index = Utils.getIndex(leftNode.getParent().getKeys(), key);
            leftNode.getParent().getKeys().add(index, key);
            leftNode.getParent().getChildren().add(index + 1, rightNode);
        } else growBTree(leftNode, rightNode, key);
    }

    private void growBTree(Node<T> leftNode, Node<T> rightNode, T key) {
        this.root = new Node<>(key);
        height++;
        leftNode.setParent(this.root);
        rightNode.setParent(this.root);
    }

    private void reassignChildren(Node<T> node, Node<T> nodeToAssign, int from) {
        if (!node.isLeaf())
            splice(node.getChildren(), from)
                    .forEach(n -> n.setParent(nodeToAssign));
    }

    public void printTree() {
        root.printTree("");
    }

    public Node<T> getRootNode() {
        return this.root;
    }

    public boolean contains(T key) {
        return search(key) != null;
    }

    public Node<T> search(T key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return searchHelper(key, root);
    }

    private Node<T> searchHelper(T key, Node<T> node) {
        if (node.getKeys().contains(key)) {
            return node;
        } else {
            if (!node.isLeaf())
                return searchHelper(key, node.getChild(Utils.getIndex(node.getKeys(), key)));
        }
        return null;
    }

    public void delete(T key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        Node<T> node = search(key);
        if (node != null) {
            size--;
            node.getKeys().remove(key);
            node = (!node.isLeaf()) ? replaceWithPredecessor(node, key) : node;
            redistribute(node);
        }
    }

    private Node<T> replaceWithPredecessor(Node<T> node, T key) {
        int index = Utils.getIndex(node.getKeys(), key);
        Node<T> min = findMaxNode(node.getChild(index));
        node.getKeys().add(index, min.getKeys().remove(min.getKeysSize() - 1));
        return min;
    }


    private void redistribute(Node<T> node) {
        if (node.getParent() != null && node.getKeysSize() < minKeys) {
            Node<T> parent = node.getParent(), rightSibling, leftSibling;
            int index = parent.getChildren().indexOf(node);

            if(parent.getKeysSize()== 0){
                return;
            }
            if (index == parent.getChildrenSize() - 1) index--;

            rightSibling = parent.getChild(index + 1);
            leftSibling = parent.getChild(index);

            if (leftSibling != null && leftSibling.getKeysSize() > minKeys) {
                redistributeToRight(node, leftSibling, index);
            } else if (rightSibling != null && rightSibling.getKeysSize() > minKeys) {
                redistributeToLeft(node, rightSibling, index);
            } else {
                mergeWithSiblings(leftSibling, parent, rightSibling, index);
            }
            redistribute(parent);
        }
    }

    private void redistributeToRight(Node<T> rightSibling, Node<T> leftSibling, int indexToSplit) {
        Node<T> parent = rightSibling.getParent();
        rightSibling.getKeys().add(0, parent.getKeys().remove(indexToSplit));
        parent.getKeys().add(indexToSplit, leftSibling.getKeys().remove(leftSibling.getKeysSize() - 1));
        if (!leftSibling.isLeaf()) {
            rightSibling.getChildren().add(0, leftSibling.getChildren().remove(leftSibling.getChildrenSize() - 1));
        }
    }

    private void redistributeToLeft(Node<T> leftSibling, Node<T> rightSibling, int indexToSplit) {
        Node<T> parent = leftSibling.getParent();
        leftSibling.getKeys().add(parent.getKeys().remove(indexToSplit));
        parent.getKeys().add(indexToSplit, rightSibling.getKeys().remove(0));
        if (!rightSibling.isLeaf()) {
            leftSibling.getChildren().add(rightSibling.getChildren().remove(0));
        }
    }


    private void mergeWithSiblings(Node<T> leftNode, Node<T> parent, Node<T> rightNode, int indexNode) {
        leftNode.getKeys().add(parent.getKeys().remove(indexNode));
        leftNode.getKeys().addAll(splice(rightNode.getKeys(), 0));
        reassignChildren(rightNode, leftNode, 0);
        parent.getChildren().remove(rightNode);
        if (parent.getKeysSize() == 0) {
            root = leftNode;
            height--;
        }
    }

    private Node<T> findMaxNode(Node<T> node) {
        while (!node.isLeaf()) {
            node = node.getChild(node.getKeysSize());
        }
        return node;
    }

    private Node<T> findMinNode(Node<T> node) {
        while (!node.isLeaf()) {
            node = node.getChild(0);
        }
        return node;
    }


    public void clear() {
        root  = new Node<>();
        size = 0;
    }

    public int getHeight() {
        return height;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T getMinKey() {
        if (isEmpty()) {
            throw new IllegalStateException("BTree is empty");
        }
        return findMinNode(root).getKey(0);
    }

    public T getMaxKey() {
        if (isEmpty()) {
            throw new IllegalStateException("BTree is empty");
        }
        Node<T> node = findMaxNode(root);
        return node.getKey(node.getKeysSize() - 1);
    }
}
