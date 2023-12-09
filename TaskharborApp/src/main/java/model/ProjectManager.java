package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The ProjectManager class is responsible for managing project information.
 */
public class ProjectManager {
    private static ProjectManager projectManager;
    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<Column> columnsList = new ArrayList<>();
    private String user; // Assuming user should be associated with a project
    private comments Comments;

    public ProjectManager() {
        projects = DataReader.getProjects();
        columnsList = DataReader.getColumns(projects);
    }

    /**
     * Gets the instance of the ProjectManager using the Singleton pattern.
     *
     * @return The ProjectManager instance.
     */
    public static ProjectManager getInstance() {
        if (projectManager == null) {
            projectManager = new ProjectManager();
        }
        return projectManager;
    }

    /**
     * Sets the list of projects for the ProjectManager.
     *
     * @param projects The list of projects to be set.
     */
    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    /**
     * Adds a new project to the appropriate list based on its completion status.
     *
     * @param project The project to be added.
     */
    public void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Removes a project from the appropriate list based on its completion status.
     *
     * @param project The project to be removed.
     */
    public void removeProject(Project project) {
        projects.remove(project);
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
        DataWriter.saveProjects(this);
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
