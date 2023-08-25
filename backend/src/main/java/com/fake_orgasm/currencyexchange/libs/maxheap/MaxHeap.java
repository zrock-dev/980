package com.fake_orgasm.currencyexchange.libs.maxheap;

/**
 * A Max Heap implementation that allows search data basing on priority values
 * @param <T> Type of values stored on Heap, must be comparable.
 */
public class MaxHeap<T extends Comparable<T>> {

    private final T[] array;
    private int size;
    private final int capacity;

    /**
     * Construct a heap with specified capacity.
     * @param capacity is maximum capacity of heap.
     */
    public MaxHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.size = 0;
        this.array = (T[]) new Comparable[capacity];
    }

    /**
     * Method to insert a new value on heap.
     * @param value Is value to be inserted.
     */
    public void insert(T value) {
        if (size == capacity) throw new IndexOutOfBoundsException("Heap capacity exceeded");

        array[size] = value;
        size++;

        int currentIdx = size - 1;
        int parentIdx = (currentIdx - 1) / 2;

        while (currentIdx > 0 && array[currentIdx].compareTo(array[parentIdx])>0) {
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = (currentIdx - 1) / 2;
        }
    }

    /**
     * Method to subtract top element on heap.
     * @return Max element stored.
     */
    public T peek() {
        if (size == 0) return null;
        int indexToRemove = 0;
        T value = array[0];
        array[indexToRemove] = array[size - 1];
        size--;

        int currentIdx = indexToRemove;

        while (true) {
            int leftChildIdx = 2 * currentIdx + 1;
            int rightChildIdx = 2 * currentIdx + 2;
            int largestIdx = currentIdx;

            if (leftChildIdx < size && array[leftChildIdx].compareTo(array[largestIdx])> 0 ) {
                largestIdx = leftChildIdx;
            }
            else if (rightChildIdx < size && array[rightChildIdx].compareTo(array[largestIdx]) > 0) {
                largestIdx = rightChildIdx;
            }

            if (largestIdx != currentIdx) {
                swap(currentIdx, largestIdx);
                currentIdx = largestIdx;
            } else {
                break;
            }
        }
        return value;
    }


    /**
     * Method to exchange to elements on heap
     * @param i is i-th of array
     * @param j is j-th of array
     */
    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
