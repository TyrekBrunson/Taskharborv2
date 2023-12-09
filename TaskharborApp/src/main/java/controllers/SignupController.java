package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import library.App;
import model.UiFacade;

public class SignupController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text signupMessage;

    @FXML
    private VBox centeredContainer;

    @FXML
    private void onSignupClicked(ActionEvent event) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                if (UiFacade.getInstance().createAccount(firstName, lastName, username, password)) {
                    // User created successfully, navigate to the "Projects" view
                    App.setRoot("Projects");
                } else {
                    signupMessage.setText("Failed to create account. Please try again.");
                }
            } else {
                signupMessage.setText("Please fill in all the fields.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onLoginClicked(ActionEvent event) {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
         try {
            App.setRoot("home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        System.out.println("Initializing SignupController...");

        try {
            // Add this line to apply styles directly to the VBox
            centeredContainer.getStyleClass().add("login-pane");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
