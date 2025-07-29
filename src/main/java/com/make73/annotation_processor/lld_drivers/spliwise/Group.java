package com.make73.annotation_processor.lld_drivers.spliwise;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Group {
    Integer id;
    String name;
    Set<User> users;
    List<Expense> expenses;
    Map<User, Integer> currentSplit;

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.users = new HashSet<>();
        this.expenses = new ArrayList<>();
        this.currentSplit = new HashMap<>();
    }

    public void addMember(User user) {
        users.add(user);
        currentSplit.put(user, 0);
    }

    public void addExpense(Expense expense) throws UserNotInGroupException {
        if (!users.containsAll(expense.consumers.stream().map(ExpenseSplit::getUser).collect(Collectors.toList()))) {
            throw new UserNotInGroupException();
        }

        for (ExpenseSplit split : expense.consumers) {
            currentSplit.put(split.getUser(), currentSplit.get(split.getUser()) + split.getAmount());
            if (expense.payee.equals(split.getUser())) {
                currentSplit.put(split.getUser(), currentSplit.get(split.getUser()) - expense.getTotalAmount());
            }
        }

        expenses.add(expense);

    }

    public Integer getBalance(User user) {
        return currentSplit.get(user);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Set<User> getMembers() {
        return users;
    }

}
