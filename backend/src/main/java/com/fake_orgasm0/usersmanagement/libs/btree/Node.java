package com.fake_orgasm0.usersmanagement.libs.btree;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable> {
    private List<T> keys ;
    private List<Node<T>> children;
    private Node<T> parent;

    public Node(){
        keys = new ArrayList<>();
        children = new ArrayList<>();
    }
    public Node(T key){
        keys = new ArrayList<>();
        children = new ArrayList<>();
        keys.add(key);
    }

    public Node(List<T> keys, Node<T> parent){
        this.keys = keys;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public List<T> getKeys() {
        return keys;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        parent.addChildren(this);
        this.parent = parent;
    }

    public void addChildren(Node<T> newChild){
        children.add(newChild);
    }
    public int getChildrenSize(){
        return children.size();
    }
    public int getKeysSize(){
        return keys.size();
    }
    public T getKey(int i){
        return keys.get(i);
    }
    public Node<T> getChild(int i){
        return children.get(i);
    }
    public boolean isLeaf(){
        return children.size() == 0;
    }
    public void printTree(String prefix) {
        System.out.println(prefix + "|__ " + keys);

        for (int i = 0; i < children.size(); i++) {
            String childPrefix = prefix + (i == children.size() - 1 ? "    " : "|   ");
            this.children.get(i).printTree(childPrefix);
        }
    }
}
