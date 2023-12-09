package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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


}
