package com.example.nguyenductaidemo1;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static final List<User> users = new ArrayList<>();

    static {
        // Thêm người dùng mẫu
        users.add(new User("admin", "admin", "Admin User", "admin@example.com"));
        users.add(new User("user1", "password1", "User One", "user1@example.com"));
        users.add(new User("user2", "password2", "User Two", "user2@example.com"));
        users.add(new User("user3", "password3", "User Three", "user3@example.com"));
    }

    public static List<User> getUsers() {
        return users;
    }
    public static boolean addUser(User user) {
        if (isUsernameTaken(user.getUsername()) || isEmailTaken(user.getEmail())) {
            return false; // Username hoặc email đã tồn tại
        }
        users.add(user);
        return true;
    }

    public static boolean updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            User existingUser = users.get(i);
            if (existingUser.getUsername().equals(user.getUsername())) {
                // Cập nhật thông tin người dùng
                existingUser.setPassword(user.getPassword());
                existingUser.setFullName(user.getFullName());
                existingUser.setEmail(user.getEmail());
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(User user) {
        return users.remove(user);
    }

    public static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}

