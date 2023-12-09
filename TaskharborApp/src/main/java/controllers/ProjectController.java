package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import library.App;
import model.Project;
import model.UiFacade;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProjectController implements Initializable{

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (UiFacade.getInstance().getCurrentUser() == null) {
            showAlert("Login Required", "Please log in to access the projects page.");
            try {
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configureListView();
                populateProjects();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
private void addProject(ActionEvent event) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Project");
    dialog.setHeaderText(null);
    dialog.setContentText("Enter project name:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(projectName -> {
        // Invoke UiFacade to add a new project
        if (UiFacade.getInstance().addProject(projectName, null, null)) {
            populateProjects(); 
        } else {
            showAlert("Add Project", "Failed to add a new project.");
        }
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



    private void populateProjects() {
        try {
            if (projectsPage == null) {
                System.err.println("TilePane (projectsPage) is null!");
                return;
            }
    
            projectsPage.getChildren().clear();
    
            List<Project> projects = UiFacade.getInstance().getProjects();
            projects = (projects != null) ? projects : Collections.emptyList();
    
            System.out.println("Number of projects retrieved: " + projects.size());
    
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
                if (empty || project == null) {
                    setText(null);
                } else {
                    setText(project.getProjectName());
                }
            }
        });
    
        // Add a listener to handle selection changes
        projectListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Handle the selection change, e.g., update details or perform actions
                System.out.println("Selected Project: " + newValue.getProjectName());
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

    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
    }

}
