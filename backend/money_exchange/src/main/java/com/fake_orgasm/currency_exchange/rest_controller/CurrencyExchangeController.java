package com.fake_orgasm.currency_exchange.rest_controller;

import com.fake_orgasm.currency_exchange.models.IntegerMoney;
import com.fake_orgasm.currency_exchange.services.MoneyExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency-exchange")
public class CurrencyExchangeController {

    private final MoneyExchanger<IntegerMoney> moneyExchanger;

    @Autowired
    public CurrencyExchangeController(MoneyExchanger<IntegerMoney> moneyExchanger){
        this.moneyExchanger = moneyExchanger;
    }
}
