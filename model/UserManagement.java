import java.util.ArrayList;

public class UserManagement {
    private static UserManagement userManager;
    private ArrayList<User> usersList;

    private UserManagement() {
        this.usersList = DataReader.getUsers();
    }

    public static UserManagement getInstance() {
        if(userManager == null){
            userManager = new UserManagement();
        }
        return userManager;
    }

    public boolean addUser(String firstName, String lastName, String userName, String password) {
        User user = new User( firstName, lastName, userName, password);
        usersList.add(user);
        return true;
    }

    public boolean removeUser(String userName, String password) {
        for (User user: usersList){
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                usersList.remove(user);
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName, String password) {
        //loop through user find the user with the give name and password and return it
        for (User user: usersList){
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUserList() {
        return usersList;
    }

    public boolean hasUser(String userName, String password) {
        for (User user : usersList){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean editUserPassword(String userName, String password) {
        for (User user : usersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                user.updatePassword(password);
            }
        }
        return false;
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }

}
