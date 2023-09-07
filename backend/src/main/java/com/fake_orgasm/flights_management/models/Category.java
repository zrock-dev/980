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
     * This is a constructor method to create a category for a passenger.
     *
     * @param priorityNumber The priority number associated with the category.
     * @param categoryType   The category type or description.
     */
    Category(int priorityNumber, String categoryType) {
        this.priorityNumber = priorityNumber;
        this.categoryType = categoryType;
    }

    /**
     * This method retrieves the priority number associated with this category.
     *
     * @return The priority number as an integer.
     */
    public int getNumber() {
        return priorityNumber;
    }

    /**
     * This method retrieves the category type or description.
     *
     * @return A string representing the category type.
     */
    public String getType() {
        return categoryType;
    }

    /**
     * This method gets a Category enum based on the provided type string.
     *
     * @param type The type string to match against category types.
     * @return The Category enum corresponding to the provided type.
     */
    public static Category getCategory(String type) {
        return switch (type) {
            case "Vip" -> Category.VIP;
            case "Frequent passenger" -> Category.FREQUENT_PASSENGER;
            default -> Category.REGULAR_PASSENGER;
        };
    }
}
