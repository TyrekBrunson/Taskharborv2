package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.App;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text loginMessage;

    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        try {
            // Add login logic here
            // For now, just navigate to the "Projects" view
            App.setRoot("Projects");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            App.setRoot("Home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
