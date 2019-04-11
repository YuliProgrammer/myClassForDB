package com.dolnikova;

import java.util.List;

public interface Storage {

    void addUser(User user);

    void updateUser(User user);

    void removeAll();

    void removeUser(int id);

    void removeUserByName(String name);

    User getUser(int id);

    List<User> getAllUsers();
}
