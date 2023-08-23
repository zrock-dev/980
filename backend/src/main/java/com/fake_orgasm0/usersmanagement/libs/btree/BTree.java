package com.fake_orgasm0.usersmanagement.libs.btree;

import lombok.Getter;

/**
 * A B-tree implementation.
 *
 * @param <T> The type of elements stored in the B-tree, must be comparable.
 */
@Getter
public class BTree<T extends Comparable<T>> {

    private final int order;
    public Node<T> root;

    /**
     * Constructs a new B-tree with the given order.
     *
     * @param order The order of the B-tree.
     */
    public BTree(int order) {
        this.order = order;
        this.root = new Node<>(order);
        this.root.setSize(0);
        root.setLeaf(true);
    }

    // ... (rest of the code)

    /**
     * Searches for a specific key in the B-tree.
     *
     * @param node The current node being searched.
     * @param key  The key to search for.
     * @return The node containing the key or null if not found.
     */
    private Node<T> search(Node<T> node, T key) {
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
     */
    public T searchKey(T key) {
        Node<T> node = search(root, key);

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
    private void split(Node<T> x, int pos, Node<T> y) {
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
     *
     * @param key The key to insert.
     */
    public void insert(final T key) {
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
     * Inserts a new key into the B-tree starting from the specified subtree node.
     *
     * @param key  The key to be inserted.
     * @param node The starting node of the subtree.
     */
    private void insertIntoSubtree(Node<T> node, T key) {

        if (node.isLeaf()) {
            int i;
            for (i = node.getSize() - 1; i >= 0 && key.compareTo(node.getKey(i)) < 0; i--) {
                node.setKey(i + 1, node.getKey(i));
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
     * Prints the entire B-tree structure by recursively traversing and printing each node's keys.
     * The tree is printed in an indented format to show hierarchy.
     */
    public void printTree() {
        this.printTree(root);
    }

    private void printTree(Node<T> x) {
        if(x == null)return;
        for (int i = 0; i < x.getSize(); i++) {
            System.out.print(x.getKey(i)+ " ");
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
     * @param x   The current node being considered.
     * @param key The key to remove.
     */

    private void remove(Node<T> x, T key) {
        int pos = x.find(key);
        if (pos != -1) {
            if (x.isLeaf()) {
                int i = 0;
                while (i < x.getSize() && x.getKeys()[i].compareTo(key) != 0) {
                    i++;
                }

                for (; i < x.getSize(); i++) {
                    if (i != 2 * order - 2) {
                        x.getKeys()[i] = x.getKeys()[i + 1];
                    }
                }
                x.decreaseSize();

            } else {
                Node<T> pred = x.getChildren()[pos];
                T predKey;
                if (pred.getSize() >= order) {
                    while (true) {
                        if (pred.isLeaf()) {
                            predKey = pred.getKeys()[pred.getSize() - 1];
                            break;
                        } else {
                            pred = pred.getChildren()[pred.getSize()];
                        }
                    }
                    remove(pred, predKey);
                    x.getKeys()[pos] = predKey;
                    return;
                }

                Node<T> nextNode = x.getChildren()[pos + 1];
                if (nextNode.getSize() >= order) {
                    T nextKey = nextNode.getKeys()[0];
                    if (!nextNode.isLeaf()) {
                        nextNode = nextNode.getChildren()[0];
                        while (true) {
                            if (nextNode.isLeaf()) {
                                nextKey = nextNode.getKeys()[nextNode.getSize() - 1];
                                break;
                            } else nextNode = nextNode.getChildren()[nextNode.getSize()];
                        }
                    }

                    remove(nextNode, nextKey);
                    x.getKeys()[pos] = nextKey;
                    return;
                }

                int temp = pred.getSize() + 1;
                pred.getKeys()[pred.increaseSize()] = x.getKeys()[pos];
                for (int i = 0, j = pred.getSize(); i < nextNode.getSize(); i++) {
                    pred.getKeys()[j++] = nextNode.getKeys()[i];
                    pred.increaseSize();
                }
                for (int i = 0; i < nextNode.getSize() + 1; i++) {
                    pred.getChildren()[temp++] = nextNode.getChildren()[i];
                }

                x.getChildren()[pos] = pred;
                for (int i = pos; i < x.getSize(); i++) {
                    if (i != 2 * order - 2) {
                        x.getChildren()[i] = x.getChildren()[i + 1];
                    }
                }

                x.decreaseSize();
                x.decreaseSize();
                if (x.getSize() == 0) {
                    if (x == root) {
                        root = x.getChildren()[0];
                    }
                }
                remove(pred, key);

            }
        } else {
            for (pos = 0; pos < x.getSize(); pos++) {
                if (x.getKeys()[pos].compareTo(key) > 0) break;
            }
            Node<T> tmp = x.getChildren()[pos];
            if (tmp.getSize() >= order) {
                remove(tmp, key);
                return;
            }

            Node<T> nb;
            T divider;

            if (pos != x.getSize() && x.getChildren()[pos + 1].getSize() >= order) {
                divider = x.getKeys()[pos];
                nb = x.getChildren()[pos + 1];
                x.getKeys()[pos] = nb.getKeys()[0];
                tmp.getKeys()[tmp.getSize()+1] = divider;
                tmp.getChildren()[tmp.getSize()] = nb.getChildren()[0];
                for (int i = 1; i < nb.getSize(); i++) {
                    nb.getKeys()[i - 1] = nb.getKeys()[i];
                }
                for (int i = 1; i <= nb.getSize(); i++) {
                    nb.getChildren()[i - 1] = nb.getChildren()[i];
                }
                nb.decreaseSize();

                remove(tmp, key);
            } else if (pos != 0 && x.getChildren()[pos - 1].getSize() >= order) {

                divider = x.getKeys()[pos - 1];
                nb = x.getChildren()[pos - 1];
                x.getKeys()[pos - 1] = nb.getKeys()[nb.getSize() - 1];
                Node<T> child = nb.getChildren()[nb.getSize()];
                nb.decreaseSize();
                for (int i = tmp.getSize(); i > 0; i--) {
                    tmp.getKeys()[i] = tmp.getKeys()[i - 1];
                }
                tmp.getKeys()[0] = divider;
                for (int i = tmp.getSize() + 1; i > 0; i--) {
                    tmp.getChildren()[i] = tmp.getChildren()[i - 1];
                }
                tmp.getChildren()[0] = child;
                tmp.increaseSize();
                remove(tmp, key);
            } else {
                Node<T> lt;
                Node<T> rt;

                if (pos != x.getSize()) {
                    divider = x.getKeys()[pos];
                    lt = x.getChildren()[pos];
                    rt = x.getChildren()[pos + 1];
                } else {
                    divider = x.getKeys()[pos - 1];
                    rt = x.getChildren()[pos];
                    lt = x.getChildren()[pos - 1];
                    pos--;
                }

                for (int i = pos; i < x.getSize() - 1; i++) {
                    x.getKeys()[i] = x.getKeys()[i + 1];
                }

                for (int i = pos + 1; i < x.getSize(); i++) {
                    x.getChildren()[i] = x.getChildren()[i + 1];
                }

                x.decreaseSize();
                lt.getKeys()[lt.getSize()+1] = divider;

                for (int i = 0, j = lt.getSize(); i < rt.getSize() + 1; i++, j++) {
                    if (i < rt.getSize()) {
                        lt.getKeys()[j] = rt.getKeys()[i];
                    }
                    lt.getChildren()[j] = rt.getChildren()[i];
                }
                lt.setSize(lt.getSize()+rt.getSize());
                if (x.getSize() == 0) {
                    if (x == root) {
                        root = x.getChildren()[0];
                    }
                }
                remove(lt, key);
            }

        }
    }

    /**
     * Removes a key from the B-tree.
     *
     * @param key The key to remove.
     * @return True if the key was found and removed, false otherwise.
     */
    public boolean remove(T key) {
        Node<T> x = search(root, key);
        if (x == null) return false;
        remove(root, key);
        return true;
    }
}