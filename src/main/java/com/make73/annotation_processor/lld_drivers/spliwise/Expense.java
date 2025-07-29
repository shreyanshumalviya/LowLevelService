package com.make73.annotation_processor.lld_drivers.spliwise;

import java.util.HashSet;
import java.util.Set;

public class Expense {
    User payee;
    Set<ExpenseSplit> consumers;

    public Expense(User payee, HashSet<ExpenseSplit> consumers) {
        this.payee = payee;
        this.consumers = consumers;
    }

    public Integer getTotalAmount() {
        return consumers.stream().mapToInt(ExpenseSplit::getAmount).sum();
    }
}
