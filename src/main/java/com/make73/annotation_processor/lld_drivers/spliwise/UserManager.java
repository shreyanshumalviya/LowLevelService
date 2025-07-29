package com.make73.annotation_processor.lld_drivers.spliwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    Map<Integer, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void createUser(String name) {
        User user = new User();
        user.id = users.size() + 1;
        user.name = name;
        users.put(user.id, user);
    }

    public User getUser(Integer id) {
        return users.get(id);
    }

    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }
}
