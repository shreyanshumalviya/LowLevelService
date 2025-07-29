package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;
import com.make73.annotation_processor.lld_drivers.spliwise.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExposeClass(
        name = "SplitWiseDriver",
        description = "This is an app that allows you to create group and add your friends to the group to keep track of your expenses",
        link = "SplitWiseDriver",
        stack = "Java")
public class SplitWiseDriver {
    private UserManager userManager;

    private GroupManager groupManager;

    public SplitWiseDriver() {
        userManager = new UserManager();
        groupManager = new GroupManager();

        // default test data
        addUser("Shreyanshu");
        addUser("Deepanshu");
        addGroup("House");
        addMember(1, 1);
        addMember(1, 2);
    }

    @ExposeMethod(name = "Create User", link = "createUser", description = "Provide name for new user")
    public void addUser(String name) {
        userManager.createUser(name);
    }

    @ExposeMethod(name = "Create Group", link = "createGroup", description = "Provide name for new group")
    public void addGroup(String name) {
        groupManager.createGroup(name);
    }

    @ExposeMethod(name = "List Users", link = "listUsers", description = "List all users")
    public List<User> listUsers() {
        return userManager.listUsers();
    }

    @ExposeMethod(name = "List Groups", link = "listGroups", description = "List all groups")
    public List<Group> listGroups() {
        return groupManager.listGroups();
    }

    @ExposeMethod(name = "List Users in Group", link = "listUsersInGroup", description = "List all users in a group, pass groupId")
    public List<User> listUsers(Integer groupId) {
        return new ArrayList<>(groupManager.getGroup(groupId).getMembers());
    }

    @ExposeMethod(name = "Add Member", link = "addMember", description = "Add member to group, pass groupId and userId")
    public void addMember(Integer groupId, Integer userId) {
        groupManager.getGroup(groupId).addMember(userManager.getUser(userId));
    }

    @ExposeMethod(name = "Add Expense", link = "addExpense", description = "Add expense to group, pass groupId, payeeId, consumerIds and amounts. ConsumerIds and amounts should be comma separated")
    public String addExpense(Integer groupId, Integer payeeId, String consumerIds, String amounts) {
        List<Integer> consumers = Stream.of(consumerIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> amount = Stream.of(amounts.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        if (consumers.size() != amount.size()) {
            throw new RuntimeException("Invalid input");
        }

        HashSet<ExpenseSplit> splits = new HashSet<>();
        for (int i = 0; i < consumers.size(); i++) {
            splits.add(new ExpenseSplit(userManager.getUser(consumers.get(i)), amount.get(i)));
        }

        try {
            groupManager.getGroup(groupId).addExpense(new Expense(userManager.getUser(payeeId), splits));
            return "SUCCESS";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ExposeMethod(name = "Get Balance", link = "getBalance", description = "Get balance of user in group, pass groupId and userId")
    public int getBalance(Integer groupId, Integer userId) {
        return groupManager.getGroup(groupId).getBalance(userManager.getUser(userId));
    }

}
