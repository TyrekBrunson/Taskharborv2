package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text loginMessage;

    @FXML
    private void onLoginClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add your login logic here
        if ("yourUsername".equals(username) && "yourPassword".equals(password)) {
            loginMessage.setText("Login successful!");
        } else {
            loginMessage.setText("Login failed. Please try again.");
        }
    }
}
