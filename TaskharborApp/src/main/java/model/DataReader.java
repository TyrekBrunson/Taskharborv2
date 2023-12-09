package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataReader {

    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try {
            File usersFile = new File("TaskharborApp/src/main/java/model/json/users.json");
            if (!usersFile.exists()) {
                System.err.println("Error: Users file not found at path: " + usersFile.getAbsolutePath());
                return userList;  // Return an empty list or handle the error accordingly
            }

            FileReader reader = new FileReader(usersFile);
            JSONParser parser = new JSONParser();
            JSONArray userListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < userListJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) userListJSON.get(i);
                // (Rest of the code remains the same)
            }
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;  // Return an empty list or handle the error accordingly
    }

    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            File projectFile = new File("TaskharborApp/src/main/java/model/json/project.json");
            if (!projectFile.exists()) {
                System.err.println("Error: Project file not found at path: " + projectFile.getAbsolutePath());
                return projectList;  // Return an empty list or handle the error accordingly
            }

            FileReader reader = new FileReader(projectFile);
            JSONParser parser = new JSONParser();
            JSONArray projectListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < projectListJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectListJSON.get(i);
                // (Rest of the code remains the same)
            }
            return projectList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectList;  // Return an empty list or handle the error accordingly
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
