package com.dolnikova;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {

    public List<User> usersList = new ArrayList<User>();
    private static int unicId = 0;

    public FileStorage(String fileName) {
        StringBuilder sb = null;

        try {
            InputStream inputStream = new FileInputStream(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));

            String line = buf.readLine();
            sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            String json = sb.toString();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            usersList = gson.fromJson(json, listType);
            System.out.println(usersList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addUser(User user) {
        usersList.add(user);
        updateJson();
    }

    public void updateUser(User user) {

        if (user.getId() >= 0 && user.getId() < usersList.size()) {
            usersList.set(user.getId(), user);
        } else {
            System.out.println("Exception. This user does not exist.");
        }

        updateJson();
    }

    public void removeAll() {
        usersList.clear();
        unicId = 0;
        updateJson();
    }

    public void removeUser(int id) {

        try {
            usersList.remove(id);
            updateJson();
        } catch (Exception e) {
            System.out.println("Index out of bounds exception. ");
        }

    }

    public void removeUserByName(String name) {

        for (User user : usersList) {
            if (user.getName() == name) {
                usersList.remove(user);
                updateJson();
                break;
            }
        }

    }

    public User getUser(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public List<User> getAllUsers() {
        return usersList;
    }

    public static int createUnicId() {
        int id = unicId;
        unicId++;
        return id;
    }

    private void updateJson() {
        Gson gson = new Gson();
        String json = gson.toJson(usersList);
        System.out.println(json);

        try {

            File JSON = new File("users.json");
            JSON.delete();

            PrintWriter writer = new PrintWriter(JSON, "UTF-8");
            writer.println(json);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "allUsers=" + usersList +
                '}';
    }
}
