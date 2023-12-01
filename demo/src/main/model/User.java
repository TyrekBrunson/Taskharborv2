import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private ProjectManager projectManager;

    private Role userRole;

    // Constructor for creating a new user with a random UUID
    public User(String firstName, String lastName, String userName, String password) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userRole = Role.Contributor; // Default role
        this.projectManager = new ProjectManager();
    }

    // Constructor for creating a user from JSON data with a predefined UUID
    public User(UUID id, String firstName, String lastName, String userName, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userRole = role;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

	public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean updatePassword(String newPassword) {
        this.password = newPassword;
        return true;
    }

    @Override
    public String toString() {
        return "User ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nUsername: " + userName  + "\nUser Role: " + userRole;
    }

    public boolean confirmUser(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

	public String getUserName() {
		return userName;
	}


// Save user data to a JSON file
 // Save user data to a JSON file (append to the existing file)
 public void saveToJsonArray(String filePath) {
    try (Writer writer = new FileWriter(filePath, true)) {
        if (new File(filePath).length() == 0) {
            // If the file is empty, write the opening bracket for the array
            writer.write("[\n");
        } else {
            // If the file is not empty, add a comma before the new user
            writer.write("\n");
        }

        // Write the user details to the file
        writer.write("    {\n");
        writer.write("        \"id\": \"" + id + "\",\n");
        writer.write("        \"firstName\": \"" + firstName + "\",\n");
        writer.write("        \"lastName\": \"" + lastName + "\",\n");
        writer.write("        \"userName\": \"" + userName + "\",\n");
        writer.write("        \"password\": \"" + password + "\",\n");
        writer.write("        \"userRole\": \"" + userRole + "\",\n"); 
        // Add any other fields you want to include
        writer.write("    },\n");

        // Close the array if this is the last user
        if (new File(filePath).length() > 0) {
            writer.write("\n]");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public UUID getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPassword(){
        return password;
    }
    
    public Role getUserRole(){
        return userRole;
    }
}

