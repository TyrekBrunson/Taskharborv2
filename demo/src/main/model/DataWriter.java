import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;;

public class DataWriter {
    private static final Project[] project = null;

	public static void saveUsers() {
		ArrayList<User> users = UserManagement.getInstance().getUserList();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(User user: users) {
			jsonUsers.add(getUserJSON(user));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter("json/users.json")) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void saveTasks() {
		ArrayList<Task> tasks = TaskManagement.getInstance().getTaskList();
		JSONArray jsonTasks = new JSONArray();

		for(Task task: tasks) {
			jsonTasks.add(getTaskJSON(task));

		}

		try(FileWriter file = new FileWriter("json/project.json")){

			file.write(jsonTasks.toJSONString());
			file.flush();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveColumns() {
		ArrayList<Column> columns = ProjectManager.getInstance().getAllColumns();
		JSONArray jsonColumns = new JSONArray();

		for (Column column : columns) {
			jsonColumns.add(getColumnJSON(column));
		}
	
		
		try (FileWriter file = new FileWriter("json/project.json")) {
			file.write(columns.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	public static void saveProjects() {
		ProjectManager projectManager = ProjectManager.getInstance();
		ArrayList<Project> projects = projectManager.getAllProjects();
		JSONArray jsonProjects = new JSONArray();
		
		for (Project project : projects) {
			jsonProjects.add(getProjectJSON(project));
		}
	
		try (FileWriter file = new FileWriter("json/projects.json")) {
			file.write(jsonProjects.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 public static JSONObject getProjectJSON(Project project) {
		JSONObject projectDetails = new JSONObject();
		projectDetails.put("id", project.getProjectId());
		projectDetails.put("projectName", project.getProjectName());
		// Add any other properties of the Project class that you want to save here.
		return projectDetails;
	}	
	
	
	public static JSONObject getColumnJSON(Column column) {
		JSONObject columnDetails = new JSONObject();
		Column columnInstance = new Column();
		columnDetails.put("id", columnInstance.getPosition());
		columnDetails.put("columnName", columnInstance.getColumnName());
		
		return columnDetails;
	}

	public static JSONObject getUserJSON(User user) {
		JSONObject personDetails = new JSONObject();
		personDetails.put("id", user.getId().toString());
		personDetails.put("firstName", user.getFirstName());
		personDetails.put("lastName", user.getLastName());
        personDetails.put("userName", user.getUserName());
		personDetails.put("password", user.getPassword());
		personDetails.put("userRole", user.getUserRole().toString());
        return personDetails;
	}

	public static JSONObject getTaskJSON(Task task){
		JSONObject taskDetails = new JSONObject();
		taskDetails.put("taskName", task.getTaskName());
		taskDetails.put("taskPriority", task.getTaskPriority());
		taskDetails.put("taskTags", task.getTaskTags());
		taskDetails.put("taskDueDate", task.getTaskDueDate());
		taskDetails.put("taskNotes", task.getTaskNotes());
		taskDetails.put("taskCompletion", task.getTaskCompletion());
		return taskDetails;
	}
}
