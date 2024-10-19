package controller;

import model.User;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    // Đăng ký người dùng mới
    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Tài khoản đã tồn tại
        }
        users.put(username, new User(username, password, 1000.0)); // Mặc định có 1000 tiền
        return true;
    }

    // Đăng nhập người dùng
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

