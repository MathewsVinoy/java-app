package com.cafe.management;

import com.cafe.models.User;
import com.cafe.database.UserDAO;
import java.time.LocalDate;
import java.util.List;

public class UserManager {

    public User login(String username, String password) {
        return UserDAO.authenticateUser(username, password);
    }

    public void addUser(String username, String password, String fullName, String email, String role) {
        if (UserDAO.userExists(username)) {
            System.out.println("Username already exists!");
            return;
        }
        User user = new User(0, username, password, fullName, email, role, LocalDate.now(), true);
        UserDAO.addUser(user);
    }

    public void updateUser(int userId, String username, String password, String fullName, String email, String role, boolean active) {
        User user = new User(userId, username, password, fullName, email, role, LocalDate.now(), active);
        UserDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        UserDAO.deleteUser(userId);
    }

    public User getUser(int userId) {
        return UserDAO.getUserById(userId);
    }

    public User getUserByUsername(String username) {
        return UserDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return UserDAO.getAllUsers();
    }

    public boolean userExists(String username) {
        return UserDAO.userExists(username);
    }

    public void changePassword(int userId, String newPassword) {
        User user = UserDAO.getUserById(userId);
        if (user != null) {
            user.setPassword(newPassword);
            UserDAO.updateUser(user);
        }
    }
}
