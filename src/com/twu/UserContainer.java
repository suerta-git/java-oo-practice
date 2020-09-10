package com.twu;

import java.util.HashMap;
import java.util.Map;

public class UserContainer {
    private final Map<String, User> userMap = new HashMap<>();

    public boolean contains(String userName) {
        return userMap.containsKey(userName);
    }

    public NormalUser getNormalUser(String userName) {
        if (contains(userName)) {
            final User user = userMap.get(userName);
            if (!(user instanceof AdminUser)) {
                return (NormalUser) user;
            }
        }
        NormalUser newUser = new NormalUser(userName);
        userMap.put(userName, newUser);
        return newUser;
    }

    public AdminUser getAdminUser(String userName) {
        if (contains(userName)) {
            final User user = userMap.get(userName);
            if (user instanceof AdminUser) {
                return (AdminUser) user;
            }
        }
        throw new IllegalArgumentException("this admin not exist");
    }

    public void registerUser(User user) {
        userMap.put(user.getUserName(), user);
    }
}
