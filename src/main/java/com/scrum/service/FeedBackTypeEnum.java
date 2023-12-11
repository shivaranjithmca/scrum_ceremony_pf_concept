package com.scrum.service;

public enum FeedBackTypeEnum {
    NEGATIVE(0),
    POSITIVE(1),
    IDEA(2),
    PRAISE(3);

    int type;

    private FeedBackTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
