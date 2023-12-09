package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Project {
    private String projectName;
    private Date projectDate;
    private UUID projectId;
    private ArrayList<Column> columns;
    private boolean completed;

    public Project(String projectName, Date projectDate, ArrayList<Column> columns) {
        this.projectName = projectName;
        this.projectDate = projectDate;
        this.columns = (columns != null) ? columns : new ArrayList<>();
        this.projectId = UUID.randomUUID();
        this.completed = false;
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

    public void markCompleted() {
        completed = true;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public String getProjectSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Project Name: ").append(projectName).append("\n");
        summary.append("Columns:\n");
        for (Column column : columns) {
            summary.append(column.toString()).append("\n");
        }
        return summary.toString();
    }
}
