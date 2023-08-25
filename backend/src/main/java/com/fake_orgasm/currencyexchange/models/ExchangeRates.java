package com.fake_orgasm.currencyexchange.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ExchangeRates<T extends Comparable<T>> implements Iterable<T> {
    private final ArrayList<T> exchangeValues;

    public ExchangeRates() {
        this.exchangeValues = new ArrayList<>();
    }


    public Object[] exchangesArray(){
        return this.exchangeValues.toArray();
    }

    public void addExchange(T exchangeValue){
        this.exchangeValues.add(exchangeValue);
    }

    public void addExchanges(Collection<T> collection){
        this.exchangeValues.addAll(collection);
    }

    public int size(){
        return this.exchangeValues.size();
    }

    public Iterator<T> iterator(){
        return this.exchangeValues.iterator();
    }
}
