package com.fake_orgasm.generator.flight_history_generator;

public enum Priority {
    NORMAL("priority normal"),
    REGULAR("priority regular"),
    VIP("priority vip");

    private final String priority;

    Priority(String value) {
        this.priority = value;
    }

    /**
     * This method gets the priority as string.
     *
     * @return The String priority
     */
    public String getPriority() {
        return priority;
    }
}
