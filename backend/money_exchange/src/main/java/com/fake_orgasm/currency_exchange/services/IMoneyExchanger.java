package com.fake_orgasm.currency_exchange.services;

import org.json.simple.JSONObject;

public interface IMoneyExchanger<T> {
    JSONObject getExchangeValues(T value);
}
