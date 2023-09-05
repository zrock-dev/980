package com.fake_orgasm.currency_exchange.rest_controller;

import com.fake_orgasm.currency_exchange.models.DoubleMoney;
import com.fake_orgasm.currency_exchange.services.IMoneyExchanger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency-exchange")
public class CurrencyExchangeController {

    private final IMoneyExchanger<DoubleMoney> moneyExchanger;

    @Autowired
    public CurrencyExchangeController(IMoneyExchanger<DoubleMoney> moneyExchanger){
        this.moneyExchanger = moneyExchanger;
    }


    @GetMapping("/process-bolivian-money")
    public JSONObject getBolivianExchange(@RequestParam Double amount){
        return moneyExchanger.getExchangeValues(new DoubleMoney(amount));
    }
}
