package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import model.Column;
import model.Project;
import model.Task;
import model.UiFacade;

import java.util.ArrayList;

public class TasksController {

    @FXML
    private TilePane tasksPage;

    private Project currentProject; 
    private int currentProjectIndex; 

    public void initData(int projectIndex) {
        this.currentProjectIndex = projectIndex;

        this.currentProject = UiFacade.getInstance().getProjects().get(projectIndex);
        ArrayList<Column> columns = currentProject.getColumns();

        for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
            Column column = columns.get(columnIndex);
            ArrayList<Task> tasks = column.getColumnTaskList();

            for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
                Task task = tasks.get(taskIndex);
                Label taskLabel = new Label(task.getTaskName());
                tasksPage.getChildren().add(taskLabel);
            }
        }
    }

}
