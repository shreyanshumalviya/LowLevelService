package com.make73.annotation_processor.lld_drivers.spliwise;

public class ExpenseSplit {
    User user;
    Integer amount;

    public ExpenseSplit(User user, Integer amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Integer getAmount() {
        return amount;
    }
}
