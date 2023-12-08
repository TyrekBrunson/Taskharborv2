package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.*;
import library.App;
import model.*;

public class ProjectController {

    @FXML
    private TilePane tilePane;

    @FXML
    private Label projectLabel;

    @FXML
    private Button switchToLoginButton;

    @FXML
    private Button backButton;

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

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Get the last loaded FXML from the App class
            String lastLoadedFXML = App.getLastLoadedFXML();

            // Check if there is a previous FXML
            if (lastLoadedFXML != null && !lastLoadedFXML.equals("Projects")) {
                // Print for debugging purposes
                System.out.println("Going back to: " + lastLoadedFXML);

                // Update lastLoadedFXML before navigating back
                App.setRoot(lastLoadedFXML);
            } else {
                // Print for debugging purposes
                System.out.println("No previous FXML found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void logOut(ActionEvent event) {
        try {
            // Navigate to the login screen
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    


}
