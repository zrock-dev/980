package com.fake_orgasm.currency_exchange.rest_controller;

import com.fake_orgasm.currency_exchange.models.DoubleMoney;
import com.fake_orgasm.currency_exchange.services.MoneyExchanger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class to manage all http requests of this microservice.
 */
@RestController
@RequestMapping("/api/currency-exchange")
public class CurrencyExchangeController {

    /**
     * MoneyExchanger with DoubleMoney parameter to manage money of double type.
     */
    private MoneyExchanger<DoubleMoney> moneyExchanger;

    /**
     * Context to manage beans implemented.
     */
    private final ApplicationContext context;

    /**
     * Class constructor to set injection facilities to spring boot.
     *
     * @param context Is context given from spring application.
     */
    @Autowired
    public CurrencyExchangeController(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Json Object to obtain data processed in bolivian money given a big amount to obtain exchanges.
     *
     * @param amount Is amount to be processed.
     * @return Process result.
     */
    @GetMapping("/bolivian")
    public JSONArray getBolivianExchange(@RequestParam Double amount) {
        if (amount < 0 || amount > 1_000_000) {
            throw new RuntimeException("Amount must be greater than 0 and less than 1_000_000");
        }
        this.moneyExchanger = context.getBean("bolivianExchanger", MoneyExchanger.class);
        return moneyExchanger.getExchangeValues(new DoubleMoney(amount));
    }

    /**
     * Json Object to obtain data processed in euro money given a big amount to obtain exchanges.
     *
     * @param amount Is amount to be processed.
     * @return Process result.
     */
    @GetMapping("/euro")
    public JSONArray getEuroExchange(@RequestParam Double amount) {
        if (amount < 0 || amount > 1_000_000) {
            throw new RuntimeException("Amount must be greater than 0 and less than 1_000_000");
        }
        this.moneyExchanger = context.getBean("euroExchanger", MoneyExchanger.class);
        return moneyExchanger.getExchangeValues(new DoubleMoney(amount));
    }

    /**
     * Json Object to obtain data processed in dollar money given a big amount to obtain exchanges.
     *
     * @param amount Is amount to be processed.
     * @return Process result.
     */
    @GetMapping("/dollar")
    public JSONArray getDollarExchange(@RequestParam Double amount) {
        if (amount < 0 || amount > 1_000_000) {
            throw new RuntimeException("Amount must be greater than 0 and less than 1_000_000");
        }
        this.moneyExchanger = context.getBean("dollarExchanger", MoneyExchanger.class);
        return moneyExchanger.getExchangeValues(new DoubleMoney(amount));
    }
}
