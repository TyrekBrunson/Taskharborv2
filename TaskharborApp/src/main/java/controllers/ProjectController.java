package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import library.App;
import model.UiFacade;

public class ProjectController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label projectLabel;

    @FXML
    private Button switchToLoginButton;

    @FXML
    private void initialize() {
        // Initialize your controller, if needed
    }

    @FXML
    private void addProject(ActionEvent event) {
        // Handle the button click to switch to the login view
        UiFacade.addProject(null, null, null);
    }
}
