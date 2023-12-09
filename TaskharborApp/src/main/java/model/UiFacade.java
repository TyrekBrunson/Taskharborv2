package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UiFacade {
    private User currentUser;
    private static UiFacade facade;
    private UserManagement userManagement;
    private ProjectManager projectManager;
    private ObservableList<Project> observableProjectList;


    public static UiFacade getInstance() {
        if (facade == null) {
            facade = new UiFacade();
        }
        return facade;
    }

    public UiFacade() {
        userManagement = UserManagement.getInstance();
        projectManager = ProjectManager.getInstance();
        observableProjectList = FXCollections.observableArrayList(projectManager.getAllProjects());


        try {
            loadUserData();
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        loadProjectData();
    }

    private void loadUserData() throws ParseException, java.text.ParseException {
        ArrayList<User> users = DataReader.getUsers();
        if (users != null) {
            userManagement.setUserList(users);
        }
    }

    private void loadProjectData() {
        ArrayList<Project> projects = DataReader.getProjects();
        if (projects != null) {
            projectManager.setProjects(projects);
            System.out.println("Projects loaded: " + projects.size());
            for (Project project : projects) {
                System.out.println("Loaded Project: " + project.getProjectName());
            }
        }
    }
    

    public boolean createAccount(String firstName, String lastName, String userName, String password) {
        try {
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

    public List<Project> getProjects() {
        User currentUser = getCurrentUser();

        if (currentUser == null || currentUser.getProjectManager() == null) {
            return Collections.emptyList();
        }

        ProjectManager projectManager = currentUser.getProjectManager();
        List<Project> projects = projectManager.getAllProjects();
        System.out.println("Number of projects: " + projects.size());
        return projects;
    }

    public void saveData() {
        try {
            DataWriter.saveUsers(userManagement);
            DataWriter.saveProjects(projectManager.getAllProjects()); // Pass the ArrayList<Project>
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void logout() {
        currentUser = null;
    }

    public boolean addProject(String projectName, Date projectDate, ArrayList<Column> columns) {
        System.out.println("UiFacade addProject method called");
        if (currentUser != null) {
            ProjectManager projectManager = this.projectManager;
            Project project = new Project(projectName, projectDate, columns);
            projectManager.addProject(project);
    
            List<Project> updatedProjects = updateProjectList();
            
            System.out.println("Project added to ProjectManager");
            return true;
        }
        System.out.println("Current user is null");
        return false;
    }
    
    

    public void removeProject(Project project) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            projectManager.removeProject(project);
        }
    }

    public List<Project> updateProjectList() {
        List<Project> updatedProjects = projectManager.getAllProjects();
        ObservableList<Project> updatedObservableProjects = FXCollections.observableArrayList(updatedProjects);
        
        return updatedProjects;
    }
    
    

    public boolean editProject(Project project) {
        if (currentUser != null) {
            ProjectManager projectManager = currentUser.getProjectManager();
            return projectManager.editProject(project);
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
                        updateProjectList(); // Notify listeners about the change in the project list
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean editTask(int projectIndex, int columnIndex, int taskIndex, String newTaskName,
            String newTaskDescription) {
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
                        updateProjectList(); // Notify listeners about the change in the project list
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
                    updateProjectList(); // Notify listeners about the change in the project list
                    return true;
                }
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
                project.addColumn(new Column(columnName, projectIndex));
                updateProjectList(); 
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
                    updateProjectList(); // Notify listeners about the change in the project list
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
                    updateProjectList(); // Notify listeners about the change in the project list
                    return true;
                }
            }
        }
        return false;
    }

     public ObservableList<Project> getObservableProjectList() {
        return observableProjectList;
    }
}
