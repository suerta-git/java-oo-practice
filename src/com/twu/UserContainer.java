package com.twu;

import java.util.HashMap;
import java.util.Map;

public class UserContainer {
    private final Map<String, User> userMap = new HashMap<>();

    public boolean contains(String userName) {
        return userMap.containsKey(userName);
    }

    public User getUser(String userName) {
        if (contains(userName)) {
            return userMap.get(userName);
        }
        User newUser = new User(userName);
        userMap.put(userName, newUser);
        return newUser;
    }
}
