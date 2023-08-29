package com.fake_orgasm.generator.user_generator;

/**
 * The DateGenerator class generates random dates within a specified range of years.
 * It provides methods to generate random days, months, and years for the date format "MM/DD/YYYY".
 */
public class DateGenerator {
    private final int yearCeiling;

    private int daysCount;
    private int monthsCount;
    private int yearsCount;

    /**
     * The minimum year for generating dates.
     */
    public static int YEAR_FLOOR = 2000;

    /**
     * Constructs a DateGenerator object with the specified year ceiling.
     *
     * @param yearCeiling The maximum year for generating dates.
     */
    public DateGenerator(int yearCeiling) {
        this.yearCeiling = yearCeiling;
        daysCount = 0;
        monthsCount = 0;
        yearsCount = YEAR_FLOOR;
    }

    /**
     * Generates a random date in the format "MM/DD/YYYY".
     *
     * @return A random date in the specified format.
     */
    protected String make() {
        return String.format("%s/%s/%s", makeRandomMonth(), makeRandomDay(), makeRandomYear());
    }

    /**
     * Generates a random day of the month.
     *
     * @return A random day of the month.
     */
    private int makeRandomDay() {
        int DAYS_CEIL = 31;
        daysCount++;
        if (daysCount > DAYS_CEIL) {
            daysCount = 1;
        }
        return daysCount;
    }

    /**
     * Generates a random month.
     *
     * @return A random month.
     */
    private int makeRandomMonth() {
        int MONTHS_CEIL = 12;
        monthsCount++;
        if (monthsCount > MONTHS_CEIL) {
            monthsCount = 1;
        }
        return monthsCount;
    }

    /**
     * Generates a random year within the specified range.
     *
     * @return A random year.
     */
    private int makeRandomYear() {
        yearsCount++;
        if (yearsCount > yearCeiling) {
            yearsCount = YEAR_FLOOR;
        }

        return yearsCount;
    }
}
