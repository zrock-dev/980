package com.fake_orgasm.maxheap;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fake_orgasm.currencyexchange.libs.maxheap.MaxHeap;
import org.junit.jupiter.api.Test;


/**
 * Test class for general methods and behavior of the MaxHeap class.
 */
public class MaxHeapExceptions {
    /**
     * Test case for verifying that creating a MaxHeap
     * with an invalid capacity throws an exception.
     */
    @Test
    void testIllegalCapacityException(){
        assertThrows(IllegalArgumentException.class, () -> new MaxHeap<>(0));
        assertThrows(IllegalArgumentException.class, () -> new MaxHeap<>(-1));
    }

    /**
     * Test case for verifying inserting a new element
     * on heap exceeding capacity limit throws an exception.
     */
    @Test
    void testIllegalInsertionException(){
        MaxHeap<Integer> heap = new MaxHeap<>(1);
        heap.insert(0);
        assertThrows(IndexOutOfBoundsException.class, () -> heap.insert(0));
    }
}
