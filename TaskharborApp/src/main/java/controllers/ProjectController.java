package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import java.util.List;
import library.App;
import model.Project;
import model.UiFacade;

public class ProjectController {

    @FXML
    private TilePane tilePane;

    @FXML
    private Label projectLabel;

    @FXML
    private Button switchToLoginButton;

    @FXML
    private void initialize() {
        // Set up any initializations if needed
    }

    @FXML
    private void addProject() {
        // Handle the button click to add a new project
        UiFacade.getInstance().addProject(null, null, null);
    }

    @FXML
    private void populateProjects() {
        // Clear existing content
        tilePane.getChildren().clear();

        // Retrieve project information from the model
        List<Project> projects = UiFacade.getInstance().getProjects();

        // Iterate through projects and create a box for each
        for (Project project : projects) {
            VBox projectBox = createProjectBox(project);
            tilePane.getChildren().add(projectBox);
        }
    }

    private VBox createProjectBox(Project project) {
        // Implement the creation of VBox for a project here
        // You can set labels, buttons, or any other UI elements based on your project object
        VBox box = new VBox();

        Label projectNameLabel = new Label(project.getProjectName());
        Button editButton = new Button("Edit");
        // Set appropriate actions, styles, and other properties for UI elements

        // Add UI elements to the VBox
        box.getChildren().addAll(projectNameLabel, editButton);

        // Set styles or CSS classes for the VBox if needed
        box.getStyleClass().add("project-box"); // Example CSS class

        return box;
    }
}
