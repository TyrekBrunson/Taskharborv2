import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Project {
    public Column Columns;
    private String projectName;
    private Date projectDate;
    private UUID projectId;
    private ArrayList<Column> columns;
    private boolean completed; // New field to track completion status

    // Constructor for the Project class
    public Project(String projectName, Date projectDate, ArrayList<Column> columns) {
        this.projectName = projectName;
        this.projectDate = projectDate;
        this.columns = new ArrayList<>();
        this.projectId = UUID.randomUUID();
        this.completed = false; // A new project is not completed by default
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Method to mark the project as completed
    public void markCompleted() {
        completed = true;
    }

    public Object getProjectId() {
        return projectId;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public void displayProjectDetails() {
        System.out.println("Project Name: " + projectName);
        System.out.println("Columns:");
        for (Column column : columns) {
            displayColumnDetails(column);
        }
    }

    private void displayColumnDetails(Column column) {
        System.out.println(column.toString());

    }

    public comments getTaskByName(String string) {
        return null;
    }
    


}
 