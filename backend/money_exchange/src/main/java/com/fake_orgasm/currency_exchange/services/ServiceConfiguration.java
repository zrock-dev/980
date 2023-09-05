package com.fake_orgasm.currency_exchange.services;

import com.fake_orgasm.currency_exchange.models.DoubleMoney;
import com.fake_orgasm.currency_exchange.models.ExchangeRates;
import com.fake_orgasm.currency_exchange.models.money_types_database.BolivianMoney;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public IMoneyExchanger<DoubleMoney> bolivianExchanger(){
        ExchangeRates<DoubleMoney> bolivianMoneys = new ExchangeRates<>();
        bolivianMoneys.addExchanges(BolivianMoney.getBolivianMoneys());
        return new MoneyExchanger<>(bolivianMoneys);
    }
}
