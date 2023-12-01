import java.util.ArrayList;
import java.util.Date;

public class UiFacade {
    private User currentUser;

    public boolean createAccount(String firstName, String lastName, String userName, String password) {
        // Check if the username already exists
        UserManagement userManagement = UserManagement.getInstance();
        if (userManagement.hasUser(userName, password)) {
            return false;
        }

        // Create a new user and add it to the user list
        userManagement.addUser(firstName, lastName, userName, password);
        return true;
    }

	public boolean login(String userName, String password) {
		UserManagement userManagement = UserManagement.getInstance();
	
		if (userManagement.hasUser(userName, password)) {
			currentUser = userManagement.getUser(userName, password);
	

			currentUser.setProjectManager(ProjectManager.getInstance());
	
			return true;
		}
	
		return false;
	}
	

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean addProject(String projectName, Date projectDate, ArrayList<Column> columns) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            Project project = new Project(projectName, projectDate, columns);
            projectManager.addProject(project);
            return true;
        }
        return false;
    }

    public boolean removeProject(int projectIndex) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                projects.remove(projectIndex);
                return true;
            }
        }
        return false;
    }

    public boolean addColumn(int projectIndex, String columnName) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                project.addColumn(new Column());
                return true;
            }
        }
        return false;
    }

    public boolean removeColumn(int projectIndex, int columnIndex) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                ArrayList<Column> columns = project.getColumns();
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    columns.remove(columnIndex);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addTask(int projectIndex, int columnIndex, String taskName, String taskDescription) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                ArrayList<Column> columns = project.getColumns();
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    Column column = columns.get(columnIndex);
                    Task task = new Task(taskName, columnIndex, null, taskDescription, null);
                    column.addColumnTask(task);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeTask(int projectIndex, int columnIndex, int taskIndex) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                ArrayList<Column> columns = project.getColumns();
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    Column column = columns.get(columnIndex);
                    ArrayList<Task> tasks = column.getColumnTaskList();
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.remove(taskIndex);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean editColumn(int projectIndex, int columnIndex, String newColumnName) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                ArrayList<Column> columns = project.getColumns();
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    Column column = columns.get(columnIndex);
                    column.setColumnName(newColumnName);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean editTask(int projectIndex, int columnIndex, int taskIndex, String newTaskName, String newTaskDescription) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            ArrayList<Project> projects = projectManager.getAllProjects();
            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project project = projects.get(projectIndex);
                ArrayList<Column> columns = project.getColumns();
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    Column column = columns.get(columnIndex);
                    ArrayList<Task> tasks = column.getColumnTaskList();
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task task = tasks.get(taskIndex);
                        task.setTaskName(newTaskName);
                        task.setTaskNotes(newTaskDescription);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
