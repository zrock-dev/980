package com.fake_orgasm.currency_exchange.models;

/**
 * Interface to set a type o money that can be subtracted from another one.
 *
 * @param <T> Is to specify the obvious fact that the money would
 *           be able to compare itself to another one.
 */
public interface IMoneySubtracted<T> extends Comparable<T> {

    /**
     * Method to subtract this type of money given another.
     *
     * @param value Is value to subtract with.
     */
    void subtract(T value);
}
