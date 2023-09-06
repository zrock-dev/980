package com.fake_orgasm.currency_exchange.services;

import com.fake_orgasm.currency_exchange.models.DoubleMoney;
import com.fake_orgasm.currency_exchange.models.ExchangeRates;
import com.fake_orgasm.currency_exchange.models.money_types_database.Bolivians;
import com.fake_orgasm.currency_exchange.models.money_types_database.Dollars;
import com.fake_orgasm.currency_exchange.models.money_types_database.Euros;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to set microservice configuration.
 */
@Configuration
public class ServiceConfiguration {

    /**
     * Bean to set money exchanger for euros.
     * @return Exchanger configured according to java database.
     */
    @Bean(name = "euroExchanger")
    public IMoneyExchanger<DoubleMoney> euroExchanger() {
        ExchangeRates<DoubleMoney> eurosMoney = new ExchangeRates<>();
        eurosMoney.addExchanges(Euros.getEurosMoneys());
        return new MoneyExchanger<>(eurosMoney);
    }

    /**
     * Bean to set money exchanger for bolivians.
     *
     * @return Exchanger configured according to java database.
     */
    @Bean(name = "bolivianExchanger")
    public IMoneyExchanger<DoubleMoney> bolivianExchanger() {
        ExchangeRates<DoubleMoney> bolivianMoneys = new ExchangeRates<>();
        bolivianMoneys.addExchanges(Bolivians.getBolivianMoneys());
        return new MoneyExchanger<>(bolivianMoneys);
    }

    /**
     * Bean to set money exchanger for dollars.
     *
     * @return Exchanger configured according to java database.
     */
    @Bean(name = "dollarExchanger")
    public IMoneyExchanger<DoubleMoney> dollarExchanger() {
        ExchangeRates<DoubleMoney> dollarsMoney = new ExchangeRates<>();
        dollarsMoney.addExchanges(Dollars.getDollarsMoneys());
        return new MoneyExchanger<>(dollarsMoney);
    }
}
