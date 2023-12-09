package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ProjectManager {
    private static ProjectManager projectManager;
    private ArrayList<Project> projects;
    private ArrayList<Column> columnsList;
    private String user;
    private comments Comments;

    public ProjectManager() {
        projects = DataReader.getProjects();
        columnsList = DataReader.getColumns(projects);
    }

    public static ProjectManager getInstance() {
        if (projectManager == null) {
            projectManager = new ProjectManager();
        }
        return projectManager;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }
    
    public void removeColumn(Column column) {
        columnsList.remove(column);
    }

    public boolean editProjectName(Project project, String newProjectName) {
        if (project != null) {
            project.setProjectName(newProjectName);
            return true;
        }
        return false;
    }

    public ArrayList<Project> getAllProjects() {
        return projects;
    }

    public ArrayList<Column> getAllColumns() {
        return columnsList;
    }

    public boolean addTask(int projectIndex, int columnIndex, String taskName, String taskDescription) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            Project project = projects.get(projectIndex);

            if (columnIndex >= 0 && columnIndex < project.getColumns().size()) {
                Column column = project.getColumns().get(columnIndex);
                Task task = new Task(taskName, columnIndex, null, taskDescription, null);
                column.addColumnTask(task);
                return true;
            }
        }
        return false;
    }

    public Project getProject(int index) {
        if (index >= 0 && index < projects.size()) {
            return projects.get(index);
        }
        return null;
    }

    public void saveProjects() {
        DataWriter.saveProjects(projects);
    }

    public ArrayList<Column> getColumns() {
        return columnsList;
    }

    public void saveColumns() {
        DataWriter.saveColumns();
    }

    public boolean addComment(int projectIndex, int columnIndex, String commentText) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            Project project = projects.get(projectIndex);

            if (columnIndex >= 0 && columnIndex < project.getColumns().size()) {
                Column column = project.getColumns().get(columnIndex);
                comments comment = new comments(user, commentText);
                column.addComment(comment);
                return true;
            }
        }
        return false;
    }

    public boolean editProject(Project project) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).equals(project)) {
                projects.set(i, project);
                return true;
            }
        }
        return false;
    }
}
