import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {

    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try {
            FileReader reader = new FileReader("json/users.json");  // Corrected the file path
            JSONParser parser = new JSONParser();
            JSONArray userListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < userListJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) userListJSON.get(i);
                UUID id = UUID.fromString((String) userJSON.get("id"));
                String firstName = (String) userJSON.get("firstName");
                String lastName = (String) userJSON.get("lastName");
                String userName = (String) userJSON.get("userName");
                String password = (String) userJSON.get("password");
                String email = (String) userJSON.get("email");
                String phoneNumber = (String) userJSON.get("phoneNumber");
                String address = (String) userJSON.get("address");
                Role userRole = Role.valueOf((String) userJSON.get("userRole"));  // Assuming Role is an enum

                userList.add(new User(id, firstName, lastName, userName, password, userRole));
            }
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            FileReader reader = new FileReader("json/project.json");  // Corrected the file path
            JSONParser parser = new JSONParser();
            JSONArray projectListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < projectListJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectListJSON.get(i);
                String projectName = (String) projectJSON.get("projectName");
                // Assuming projectDate is stored as a string, you may need to parse it accordingly
                String projectDateString = (String) projectJSON.get("projectDate");
                Date projectDate = parseDate(projectDateString);
                ArrayList<Column> columns = getColumns();

                projectList.add(new Project(projectName, projectDate, columns));
            }
            return projectList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Column> getColumns() {
        ArrayList<Column> columnList = new ArrayList<>();
    
        try {
            FileReader reader = new FileReader("json/project.json"); // Corrected the file path
            JSONParser parser = new JSONParser();
            JSONArray projectListJSON = (JSONArray) parser.parse(reader);
    
            for (int i = 0; i < projectListJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectListJSON.get(i);
                JSONArray columnsJSON = (JSONArray) projectJSON.get("columns");
    
                for (int j = 0; j < columnsJSON.size(); j++) {
                    JSONObject columnJSON = (JSONObject) columnsJSON.get(j);
                    String columnName = (String) columnJSON.get("columnName");
                    ArrayList<Task> columnTaskList = getTasks((JSONArray) columnJSON.get("tasks"));
    
                    // Create the Column object correctly and add it to the columnList
                    Column column = new Column();
                    columnList.add(column);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return columnList;
    }
    
    

    public static ArrayList<Task> getTasks(JSONArray taskListJSON) {
        ArrayList<Task> taskList = new ArrayList<>();
    
        if(taskListJSON != null){
        for (int i = 0; i < taskListJSON.size(); i++) {
            JSONObject taskJSON = (JSONObject) taskListJSON.get(i);
            String taskName = (String) taskJSON.get("taskName");
            int taskPriority = ((Long) taskJSON.get("priority")).intValue();
            Date taskDueDate = parseDate((String) taskJSON.get("taskDueDates"));
            String taskNotes = (String) taskJSON.get("taskNotes");
    
            // Create Task objects and add them to the taskList
            Task task = new Task(taskName, taskPriority, taskDueDate, taskNotes, taskListJSON);
            taskList.add(task);
        }
    }
    
        return taskList;
    }
    

    public static ArrayList<comments> getComments(JSONArray commentListJSON){
        ArrayList<comments> commentList = new ArrayList<>();
        
        for(int i = 0; i < commentListJSON.size(); i++){
            JSONObject commentJSON = (JSONObject) commentListJSON.get(i);
            String user = (String) commentJSON.get("user");
            String commentText = (String) commentJSON.get("commentText");

            commentList.add(new comments(user, commentText));
        }
        return commentList;
    }

    // Add a method to parse Date from a string
    private static Date parseDate(String dateString) {
        if(dateString == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // format
        try {
            Date parseDate = dateFormat.parse(dateString);
            return parseDate;
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception
            return null;
        }
    }

    public static void main(String[] args){
        ArrayList<Project> projects = getProjects();

        for(Project project : projects){
            System.out.println(project);
        }


    }
}
