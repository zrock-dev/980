package com.fake_orgasm.currency_exchange.models.money_types_database;

import com.fake_orgasm.currency_exchange.models.DoubleMoney;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class used to storage all types of dollars money exchanges.
 */
public class Dollars {

    /**
     * Collection to store all values of dollar exchange types.
     *
     * @return Collection set.
     */
    public static Collection<DoubleMoney> getDollarsMoneys() {
        return new ArrayList<>(List.of(
                new DoubleMoney(0.01),
                new DoubleMoney(0.05),
                new DoubleMoney(0.1),
                new DoubleMoney(0.25),
                new DoubleMoney(0.5),
                new DoubleMoney(1.0),
                new DoubleMoney(2.0),
                new DoubleMoney(5.0),
                new DoubleMoney(10.0),
                new DoubleMoney(20.0),
                new DoubleMoney(50.0),
                new DoubleMoney(100.0)));
    }
}
