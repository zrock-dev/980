package com.fake_orgasm.currency_exchange.models;

/**
 * Class to model integer value money types.
 */
public class IntegerMoney implements IMoneySubtracted<IntegerMoney> {

    /**
     * Value of money.
     */
    private Integer moneyValue;

    /**
     * Constructor to set an integer value to this class.
     *
     * @param value Is money value to be set.
     */
    public IntegerMoney(Integer value) {
        this.moneyValue = value;
    }

    /**
     * Method to obtain current money value.
     *
     * @return Local variable.
     */
    public Integer getMoneyValue() {
        return moneyValue;
    }

    /**
     * Method implemented from IMoneySubtracted interface.
     *
     * @param value Is value to subtract with.
     */
    @Override
    public void subtract(IntegerMoney value) {
        this.moneyValue -= value.getMoneyValue();
    }

    /**
     * Method to compare this IntegerMoney class to another one.
     *
     * @param o the object to be compared.
     * @return Comparative result.
     */
    @Override
    public int compareTo(IntegerMoney o) {
        return Integer.compare(this.moneyValue, o.getMoneyValue());
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
