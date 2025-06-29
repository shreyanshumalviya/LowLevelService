package com.make73.annotation_processor.lld_drivers.BookMyShow.managers;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void addUser(String name) {
        User user = new User(users.size(), name);
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int userId) {
        return users.get(userId);
    }
}
