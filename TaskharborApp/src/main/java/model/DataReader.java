package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataReader {

    public static ArrayList<User> getUsers() throws org.json.simple.parser.ParseException, ParseException {
        ArrayList<User> userList = new ArrayList<>();

        try {
            File usersFile = new File("TaskharborApp/src/main/java/model/json/users.json");
            if (!usersFile.exists()) {
                System.err.println("Error: Users file not found at path: " + usersFile.getAbsolutePath());
                return userList;
            }

            try (FileReader reader = new FileReader(usersFile)) {
                JSONParser parser = new JSONParser();
                JSONArray userListJSON = (JSONArray) parser.parse(reader);

                for (Object userObj : userListJSON) {
                    if (userObj instanceof JSONObject) {
                        JSONObject userJSON = (JSONObject) userObj;
                        User user = createUserFromJSON(userJSON);
                        userList.add(user);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }
    

    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projectList = new ArrayList<>();
    
        try {
            File projectFile = new File("TaskharborApp/src/main/java/model/json/project.json");
            if (!projectFile.exists()) {
                System.err.println("Error: Project file not found at path: " + projectFile.getAbsolutePath());
                return projectList;  
            }
    
            FileReader reader = new FileReader(projectFile);
            JSONParser parser = new JSONParser();
            JSONArray projectListJSON = (JSONArray) parser.parse(reader);
    
            for (int i = 0; i < projectListJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectListJSON.get(i);
    
                Project project = deserializeProject(projectJSON);
    
                projectList.add(project);
            }
            return projectList;
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return projectList;  
    }
    

    public static ArrayList<Column> getColumns(ArrayList<Project> projects) {
        ArrayList<Column> columnList = new ArrayList<>();
    
        try {
            for (Project project : projects) {
                ArrayList<Column> columns = project.getColumns();
                columnList.addAll(columns);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return columnList;
    }

    public static ArrayList<Task> getTasks(JSONArray taskListJSON) {
        ArrayList<Task> taskList = new ArrayList<>();

        if (taskListJSON != null) {
            for (int i = 0; i < taskListJSON.size(); i++) {
                JSONObject taskJSON = (JSONObject) taskListJSON.get(i);
                String taskName = (String) taskJSON.get("taskName");
                int taskPriority = ((Long) taskJSON.get("taskPriority")).intValue();
                Date taskDueDate = parseDate((String) taskJSON.get("taskDueDate"));
                String taskNotes = (String) taskJSON.get("taskNotes");

                Task task = new Task(taskName, taskPriority, taskDueDate, taskNotes, taskListJSON);
                taskList.add(task);
            }
        }

        return taskList;
    }

    private static User createUserFromJSON(JSONObject userJSON) {
        String firstName = (String) userJSON.get("firstName");
        String lastName = (String) userJSON.get("lastName");
        String userName = (String) userJSON.get("userName");
        String password = (String) userJSON.get("password");
        String userRoleString = (String) userJSON.get("userRole");

        Role userRole = Role.valueOf(userRoleString); 

        return new User(null, firstName, lastName, userName, password, userRole);
    }

    private static Project deserializeProject(JSONObject projectJSON) {
        String projectName = (String) projectJSON.get("projectName");
        String projectDateString = (String) projectJSON.get("projectDate");
        Date projectDate = parseDate(projectDateString);
    
        return new Project(projectName, projectDate, null); 
    }
    

    private static Date parseDate(String dateString) {
        if (dateString == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
