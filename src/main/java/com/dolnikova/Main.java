package com.dolnikova;

import com.google.gson.Gson;

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<User>();

        User user1 = createUsers("Alex", 23);
        userList.add(user1);

        User user2 = createUsers("Olga", 40);
        userList.add(user2);

        User user3 = createUsers("Yuli", 17);
        userList.add(user3);

        String json = listOfUsersToJson(userList);
        FileStorage fileStorage = new FileStorage("users.json");

        User user4 = createUsers("Ivan", 37);
        fileStorage.addUser(user4);

        User user5 = createUsers("Irina", 30);
        fileStorage.addUser(user5);

        User user6 = createUsers("Mary", 10);
        fileStorage.addUser(user6);

        user2.setAge(45);
        fileStorage.updateUser(user2);

        fileStorage.removeUser(0);
        fileStorage.removeUserByName("Ivan");

        System.out.println(fileStorage.getUser(5));
        userList = fileStorage.getAllUsers();
        System.out.println(userList);

        fileStorage.removeAll();
    }

    public static User createUsers(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setId(FileStorage.createUnicId());

        return user;
    }

    public static String listOfUsersToJson(List<User> users) {
        String json = "";

        try {
            Gson gson = new Gson();
            json = gson.toJson(users);

            File JSON = new File("users.json");
            PrintWriter writer = new PrintWriter(JSON, "UTF-8");
            writer.println(json);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
}
