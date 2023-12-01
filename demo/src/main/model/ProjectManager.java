import java.util.ArrayList;
import java.util.HashMap;

/**
 * The ProjectManager class is responsible for managing project information.
 */
public class ProjectManager {
    private static ProjectManager projectManager;
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Column> columnsList = new ArrayList<Column>();
    private String user;
    private comments Comments;

    ProjectManager() {
        projects = DataReader.getProjects();
        columnsList = DataReader.getColumns();
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


    public ArrayList<Project> getAllProjects(){
        return projects;
    }

    public ArrayList<Column> getAllColumns(){
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
        return null; // or handle the case when the index is out of bounds
    }
    
    

    public void saveProjects() {
        DataWriter.saveProjects();
    }

    public void saveColumns() {
        DataWriter.saveColumns();
    }
    public boolean addCommet(int projectIndex, int columIndex, String commentText) {
    if (projectIndex >= 0 && projectIndex < projects.size()){
            Project project = projects.get(projectIndex);
            
            if (columIndex >= 0 && columIndex < project.getColumns().size()){
                Column column = project.getColumns().get(columIndex);
                comments comment = new comments(user, commentText);
                column.addComment(comment);
                return true;
            }
        }
        return false;
    }
}