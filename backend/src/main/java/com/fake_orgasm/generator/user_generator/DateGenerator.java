package com.fake_orgasm.generator.user_generator;

public class DateGenerator {
    private final int yearCeiling;

    private int daysCount;
    private int monthsCount;
    private int yearsCount;

    public static int YEAR_FLOOR = 2000;

    public DateGenerator(int yearCeiling) {
        this.yearCeiling = yearCeiling;
        daysCount = 0;
        monthsCount = 0;
        yearsCount = YEAR_FLOOR;
    }

    protected String make() {
        return String.format("%s/%s/%s", makeRandomMonth(), makeRandomDay(), makeRandomYear());
    }

    private int makeRandomDay() {
        int DAYS_CEIL = 31;
        daysCount++;
        if (daysCount > DAYS_CEIL) {
            daysCount = 1;
        }
        return daysCount;
    }

    private int makeRandomMonth() {
        int MONTHS_CEIL = 12;
        monthsCount++;
        if (monthsCount > MONTHS_CEIL) {
            monthsCount = 1;
        }
        return monthsCount;
    }

    private int makeRandomYear() {
        yearsCount++;
        if (yearsCount > yearCeiling) {
            yearsCount = YEAR_FLOOR;
        }

        return yearsCount;
    }
}
