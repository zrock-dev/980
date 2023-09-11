package com.fake_orgasm.currency_exchange.models;

import org.decimal4j.util.DoubleRounder;

/**
 * Class to model integer value money types.
 */
public class DoubleMoney implements IMoneySubtracted<DoubleMoney> {

    /**
     * Value of money.
     */
    private Double moneyValue;

    /**
     * Constructor to set an integer value to this class.
     *
     * @param value Is money value to be set.
     */
    public DoubleMoney(Double value) {
        this.moneyValue = value;
    }

    /**
     * Method to obtain current money value.
     *
     * @return Local variable.
     */
    public Double getMoneyValue() {
        return moneyValue;
    }

    /**
     * Method implemented from IMoneySubtracted interface.
     *
     * @param value Is value to subtract with.
     */
    @Override
    public void subtract(DoubleMoney value) {
        double sum = DoubleRounder.round(Double.sum(this.moneyValue, value.getMoneyValue() * -1), 2);
        this.moneyValue = sum;
    }

    /**
     * Method to compare this IntegerMoney class to another one.
     *
     * @param o the object to be compared.
     * @return Comparative result.
     */
    @Override
    public int compareTo(DoubleMoney o) {
        return Double.compare(this.moneyValue, o.getMoneyValue());
    }

    /**
     * String of this class.
     *
     * @return String value of local variable.
     */
    public String toString() {
        return String.valueOf(this.moneyValue);
    }
}
