package com.fake_orgasm.generator.flight_history_generator;

public enum Priority {
    NORMAL("priority normal"),
    REGULAR("priority regular"),
    VIP("priority vip");

    private final String priority;

    Priority(String value) {
        this.priority = value;
    }

    public String getPriority() {
        return priority;
    }
}
