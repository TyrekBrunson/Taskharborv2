package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import library.App;
import model.Project;
import model.UiFacade;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProjectController {

    @FXML
    private TilePane projectsPage;

    @FXML
    private ListView<Project> projectListView;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;

    @FXML
    private void initialize() {
        System.out.println("Initializing ProjectController...");

        try {
            configureListView();
            populateProjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addProject() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Project");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter project name:");
    
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(projectName -> {
            UiFacade.getInstance().addProject(projectName, null, null);
            populateProjects();
        });
    }
    

    @FXML
    private void editProject(ActionEvent event) {
        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            TextInputDialog dialog = new TextInputDialog(selectedProject.getProjectName());
            dialog.setTitle("Edit Project");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter new project name:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newProjectName -> {
                if (UiFacade.getInstance().editProjectName(selectedProject, newProjectName)) {
                    populateProjects();
                } else {
                    showAlert("Edit Project", "Failed to edit project name.");
                }
            });
        } else {
            showAlert("Edit Project", "No project selected for editing.");
        }
    }



    @FXML
    private void removeProject(ActionEvent event) {
        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null && showConfirmationDialog("Confirm Deletion", "Are you sure you want to delete this project?")) {
            UiFacade.getInstance().removeProject(selectedProject);
            populateProjects();
        }
    }

    // ... (Other methods remain unchanged)

    private void populateProjects() {
        try {
            projectsPage.getChildren().clear();

            List<Project> projects = UiFacade.getInstance().getProjects();
            projects = (projects != null) ? projects : Collections.emptyList();

            for (Project project : projects) {
                TitledPane projectTile = createProjectTile(project);
                projectsPage.getChildren().add(projectTile);
            }

            ObservableList<Project> observableProjects = FXCollections.observableArrayList(projects);
            projectListView.setItems(observableProjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureListView() {
        projectListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        projectListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                setText(empty || project == null ? null : project.getProjectName());
            }
        });
    }

    private TitledPane createProjectTile(Project project) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(project.getProjectName());
        titledPane.getStyleClass().add("project-tile");
        return titledPane;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            String lastLoadedFXML = App.getLastLoadedFXML();
            if (lastLoadedFXML != null && !lastLoadedFXML.equals("Projects")) {
                System.out.println("Going back to: " + lastLoadedFXML);
                App.setRoot(lastLoadedFXML);
            } else {
                System.out.println("No previous FXML found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
