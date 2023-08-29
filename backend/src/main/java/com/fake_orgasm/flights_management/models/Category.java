package com.fake_orgasm.flights_management.models;

/**
 * This enum represents the types of users that you have in the execution of the program.
 */
public enum Category {

    VIP(1, "Vip"),
    FREQUENT_PASSENGER(2, "Frequent passenger"),
    REGULAR_PASSENGER(3, "Regular passenger");

    private final int priorityNumber;
    private final String categoryType;

    /**
     * This is a constructor method to create a category for passenger.
     *
     * @param priorityNumber is the priority number.
     * @param categoryType is the priority type.
     */
    Category(int priorityNumber, String categoryType) {

        this.priorityNumber = priorityNumber;
        this.categoryType = categoryType;
    }

    /**
     * This is a getter method to get the priority;
     *
     * @return int type, return the priority of the category.
     */
    public int getPriorityNumber() {
        return priorityNumber;
    }

    public String getCategoryType() {
        return categoryType;
    }
}