package com.fake_orgasm.currency_exchange.services;

import org.json.simple.JSONObject;

/**
 * Interface to establish promises of class' flow to obtain a result of processes.
 *
 * @param <T> To set the kind of data that will be processed.
 */
public interface IMoneyExchanger<T> {

    /**
     * Method to promise the class it will return a result of processing.
     *
     * @param value Is value to be processed.
     * @return Json containing data processed.
     */
    JSONObject getExchangeValues(T value);
}
