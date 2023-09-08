package com.fake_orgasm.money_exchanger;

import com.fake_orgasm.currency_exchange.models.ExchangeRates;
import com.fake_orgasm.currency_exchange.models.IntegerMoney;
import com.fake_orgasm.currency_exchange.services.MoneyExchanger;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test to check final functionality of MoneyExchanger class.
 */
class MoneyExchangerTest {

    /**
     * Variable to manage integer monies on a maxheap.
     */
    private ExchangeRates<IntegerMoney> moneyRates;

    /**
     * Method to set up all test filling money rates according to an array of exchanges values.
     */
    @BeforeEach
    void setUp() {
        IntegerMoney[] integerMonies = new IntegerMoney[5];
        integerMonies[0] = new IntegerMoney(1);
        integerMonies[1] = new IntegerMoney(5);
        integerMonies[2] = new IntegerMoney(10);
        integerMonies[3] = new IntegerMoney(15);
        integerMonies[4] = new IntegerMoney(20);

        moneyRates = new ExchangeRates<>();
        for (IntegerMoney integerMoney : integerMonies) {
            moneyRates.addExchange(integerMoney);
        }
    }

    /**
     * Method to check if exchanger is given most optimal quantity of money.
     */
    @Test
    void getExchangeValues() {
        MoneyExchanger<IntegerMoney> exchanger = new MoneyExchanger<>(moneyRates);
        JSONArray exchangerValues = exchanger.getExchangeValues(new IntegerMoney(67));
        System.out.println(exchangerValues);
    }
}
