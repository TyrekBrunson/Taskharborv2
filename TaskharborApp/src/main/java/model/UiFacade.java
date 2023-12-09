package model;

import java.util.ArrayList;
import java.util.Date;

public class UiFacade {
    private User currentUser;
    private static UiFacade facade;
    private UserManagement userManagement;
    private ProjectManager projectManager;

    public static UiFacade getInstance() {
        if (facade == null) {
            facade = new UiFacade();
        }
        return facade;
    }

    private UiFacade() {
        userManagement = UserManagement.getInstance();
        projectManager = ProjectManager.getInstance();

        loadUserData();
        loadProjectData();
    }

    private void loadUserData() {
        ArrayList<User> users = DataReader.getUsers();
        if (users != null) {
            userManagement.setUserList(users);
        }
    }
    

    private void loadProjectData() {
        ArrayList<Project> projects = DataReader.getProjects();
        if (projects != null) {
            projectManager.setProjects(projects);
        }
    }

    public boolean createAccount(String firstName, String lastName, String userName, String password) {
        try {
            // Check if the username already exists
            if (userManagement.hasUser(userName, password)) {
                return false;
            }
            userManagement.addUser(firstName, lastName, userName, password);
            DataWriter.saveUsers(userManagement);  
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }
    

    public boolean login(String userName, String password) {
        try {
            User user = userManagement.getUser(userName, password);
    
            if (user != null && user.checkPassword(password)) {
                currentUser = user;
                loadProjectData();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<Project> getProjects() {
        if (currentUser != null) {
            return projectManager.getAllProjects();
        }
        return null;
    }

    public void saveData() {
        try {
            DataWriter.saveUsers(userManagement);  
            DataWriter.saveProjects(projectManager);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
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

    public boolean editProject(Project project) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            return projectManager.editProject(project);
        }
        return false;
    }

    public boolean removeProject(Project project) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            projectManager.removeProject(project);
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

    public boolean editProjectName(Project project, String newProjectName) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            return projectManager.editProjectName(project, newProjectName);
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
