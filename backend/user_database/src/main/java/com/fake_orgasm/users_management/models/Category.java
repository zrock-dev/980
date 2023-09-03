package com.fake_orgasm.users_management.models;

/**
 * This enum represents the types of users that you have in the execution of the program.
 */
public enum Category {
    VIP(1),
    FREQUENT_PASSENGER(2),
    REGULAR_PASSENGER(3);

    private final int priorityNumber;

    Category(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    /**
     * @return int type, return the priority of the category.
     */
    public int getPriorityNumber() {
        return priorityNumber;
    }
}
