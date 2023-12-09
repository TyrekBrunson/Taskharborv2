package model;

import java.util.*;

import org.json.simple.parser.ParseException;

public class UserManagement {
    private static UserManagement userManager;
    private ArrayList<User> usersList;

    private UserManagement() {
        try {
            this.usersList = DataReader.getUsers();
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public static synchronized UserManagement getInstance() {
        if (userManager == null) {
            userManager = new UserManagement();
        }
        return userManager;
    }

    public boolean addUser(String firstName, String lastName, String userName, String password) {
        User user = new User(firstName, lastName, userName, password);
        usersList.add(user);
        return true;
    }

    public boolean removeUser(String userName, String password) {
        for (User user : usersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                usersList.remove(user);
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName, String password) {
        for (User user : usersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUserList() {
        return usersList;
    }

    public boolean hasUser(String userName, String password) {
        for (User user : usersList) {
            if (Objects.equals(user.getUserName(), userName) && Objects.equals(user.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }
    


    public void setUserList(ArrayList<User> userList) {
        this.usersList = userList;
    }

    public boolean editUserPassword(String userName, String newPassword) {
        for (User user : usersList) {
            if (user.getUserName().equals(userName)) {
                user.updatePassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public synchronized void saveUsers() {
        DataWriter.saveUsers(this);
    }
}
