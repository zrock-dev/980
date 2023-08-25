package com.fake_orgasm.currencyexchange.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class to store different types of money and their values on coins.
 * @param <T> Is generic to specify what type of comparable value must be parametrized.
 */
public class ExchangeRates<T extends Comparable<T>> implements Iterable<T> {

    /**
     * Local variable in form of arraylist to store all different values of exchange money.
     */
    private final ArrayList<T> exchangeValues;

    /**
     * Class constructor to init arraylist exchange types.
     */
    public ExchangeRates() {
        this.exchangeValues = new ArrayList<>();
    }

    /**
     * Method to obtain all exchanges on array-form.
     * @return local arraylist transformed as arraylist.
     */
    public Object[] exchangesArray() {
        return this.exchangeValues.toArray();
    }

    /**
     * Method to add an exchange value.
     * @param exchangeValue is money value.
     */
    public void addExchange(T exchangeValue) {
        this.exchangeValues.add(exchangeValue);
    }

    /**
     * Method to add different coins from any collection.
     * @param collection Is collection to insert.
     */
    public void addExchanges(Collection<T> collection) {
        this.exchangeValues.addAll(collection);
    }

    /**
     * Method to return exchanges size.
     * @return Quantity of money values.
     */
    public int size() {
        return this.exchangeValues.size();
    }

    /**
     * Iterator to apply for-each.
     * @return Iterator from arraylist.
     */
    public Iterator<T> iterator() {
        return this.exchangeValues.iterator();
    }
}
