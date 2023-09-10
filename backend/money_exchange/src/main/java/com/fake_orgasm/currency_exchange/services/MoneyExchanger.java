package com.fake_orgasm.currency_exchange.services;

import com.fake_orgasm.currency_exchange.libs.maxheap.MaxHeap;
import com.fake_orgasm.currency_exchange.models.ExchangeRates;
import com.fake_orgasm.currency_exchange.models.IMoneySubtracted;
import java.util.*;
import org.json.simple.JSONObject;

/**
 * This class is to manage the service of give the user
 * different exchanges according to a number entered.
 *
 * @param <T> Is for param the class a specific type of MoneySubtracted.
 */
public class MoneyExchanger<T extends IMoneySubtracted<T>> implements IMoneyExchanger<T> {

    /**
     * MaxHeap to manage all types of exchanges values.
     */
    private final MaxHeap<T> heap;

    /**
     * ExchangeRates to store all possible rates.
     */
    private final ExchangeRates<T> rates;

    /**
     * A JsonObject to return to the final of the procurement.
     */
    private final JSONObject jsonObject;

    /**
     * A Map to put all values with its exchange type.
     */
    private final Map<T, Integer> exchangesMap;

    /**
     * Class constructor  given a collection of moneyRates.
     *
     * @param moneyRates Is moneyRates to be set in heap.
     */
    public MoneyExchanger(ExchangeRates<T> moneyRates) {
        this.rates = moneyRates;
        this.heap = new MaxHeap<>(moneyRates.size());
        this.jsonObject = new JSONObject();
        this.exchangesMap = new HashMap<>(moneyRates.size());
    }

    /**
     * Method to fill local max heap according to exchange rates.
     */
    private void fillHeap() {
        for (Object rate : rates.exchangesArray()) {
            heap.put((T) rate);
        }
    }

    /**
     * Method to fill local JsonObject variable from map.
     *
     * @param value Is money value to obtain all possible exchanges quantity.
     * @return A JsonObject with all values processed.
     */
    public JSONObject getExchangeValues(T value) {
        fillHeap();
        processQuantityRatesOnMap(value);
        this.jsonObject.clear();

        for (T exchangeValue : exchangesMap.keySet()) {
            jsonObject.put(String.valueOf(exchangeValue), exchangesMap.get(exchangeValue));
        }
        return jsonObject;
    }

    /**
     * Method to process an amount of exchanges quantity and process it on map.
     *
     * @param bigAmount Is mount to be separated in exchanges given.
     */
    private void processQuantityRatesOnMap(T bigAmount) {
        this.exchangesMap.clear();
        T heapTop = this.heap.extract();
        while (heapTop != null && bigAmount.compareTo(heapTop) >= 0 || this.heap.size() > 0) {
            int size = this.heap.size();
            int quantityOfMoney = getMoneyQuantity(bigAmount, heapTop);
            if (quantityOfMoney > 0) {
                exchangesMap.put(heapTop, quantityOfMoney);
            }
            heapTop = this.heap.extract();
        }
    }

    /**
     * Method to obtain a quantity of money given two values, an amount and money value.
     * <p>On case that value given is less than money, method returns 0
     *
     * @param value Is an amount to be decomposed.
     * @param money Is money given as rule to be compared with value.
     * @return Quantity of money that conforms value.
     */
    private int getMoneyQuantity(T value, T money) {
        int moneyQuantity = 0;
        if (money.compareTo(value) > 0) {
            return moneyQuantity;
        }

        while (value.compareTo(money) > 0 || value.compareTo(money) == 0) {
            moneyQuantity++;
            value.subtract(money);
        }

        return moneyQuantity;
    }
}
