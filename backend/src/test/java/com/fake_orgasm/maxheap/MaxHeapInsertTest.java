package com.fake_orgasm.maxheap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fake_orgasm.currencyexchange.libs.maxheap.MaxHeap;
import com.fake_orgasm.currencyexchange.models.ExchangeRates;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing insertion functionality of the MaxHeap.
 */
public class MaxHeapInsertTest {

    @Test
    void testIntegersExchanges() {
        ExchangeRates<Integer> exchangeRates = new ExchangeRates<>();
        exchangeRates.addExchanges(List.of(1, 5, 10, 20));
        MaxHeap<Integer> heapInteger = new MaxHeap<>(exchangeRates.size());
        for (Integer exchangeValue : exchangeRates) {
            heapInteger.insert(exchangeValue);
        }

        assertEquals(20, heapInteger.peek());
    }

    @Test
    void testDoubleExchanges() {
        ExchangeRates<Double> exchangeRates = new ExchangeRates<>();
        exchangeRates.addExchanges(List.of(0.5, 1.0, 1.5, 5.5, 20.0, 20.5, 100.0));
        MaxHeap<Double> heapInteger = new MaxHeap<>(exchangeRates.size());
        for (Double exchangeValue : exchangeRates) {
            heapInteger.insert(exchangeValue);
        }

        assertEquals(100.0, heapInteger.peek());
    }
}
