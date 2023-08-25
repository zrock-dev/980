package com.fake_orgasm.currencyexchange.libs.maxheap;

/**
 * A Max Heap implementation that allows search data basing on priority values.
 * @param <T> Type of values stored on Heap, must be comparable.
 */
public class MaxHeap<T extends Comparable<T>> {

    /**
     * Local array to represent heap.
     */
    private final T[] array;

    /**
     * Integer to represent heap size.
     */
    private int size;

    /**
     * Integer to restrict heap capacity.
     */
    private int capacity;

    /**
     * Construct a heap with specified capacity.
     * @param maxCapacity is maximum capacity of heap.
     */
    public MaxHeap(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = maxCapacity;
        this.size = 0;
        this.array = (T[]) new Comparable[capacity];
    }

    /**
     * Method to insert a new value on heap.
     * @param value Is value to be inserted.
     */
    public void insert(T value) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException("Heap capacity exceeded");
        }

        array[size] = value;
        size++;

        int currentIdx = size - 1;
        int parentIdx = (currentIdx - 1) / 2;

        while (currentIdx > 0 && array[currentIdx].compareTo(array[parentIdx]) > 0) {
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
        if (size == 0) {
            return null;
        }

        int indexToRemove = 0;
        T value = array[0];
        array[indexToRemove] = array[size - 1];
        size--;

        int currentIdx = indexToRemove;

        while (true) {
            int leftChildIdx = 2 * currentIdx + 1;
            int rightChildIdx = 2 * currentIdx + 2;
            int largestIdx = currentIdx;

            if (leftChildIdx < size && valueInHeapArrayIsMajor(leftChildIdx, largestIdx)) {
                largestIdx = leftChildIdx;
            } else if (rightChildIdx < size && valueInHeapArrayIsMajor(rightChildIdx, largestIdx)) {
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
     * Method to determine if the i-th element in array is major than j-th one.
     * @param firstIndex Is i-th element.
     * @param secondIndex Is j-th element.
     * @return Comparator result.
     */
    private boolean valueInHeapArrayIsMajor(int firstIndex, int secondIndex) {
        return array[firstIndex].compareTo(array[secondIndex]) > 0;
    }

    /**
     * Method to exchange to elements on heap.
     * @param i is i-th of array.
     * @param j is j-th of array.
     */
    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
