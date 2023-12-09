package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.App;
import model.UiFacade;

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
        boolean isValidUser = UiFacade.getInstance().login(username, password);

        if (isValidUser) {
            try {
                App.setRoot("Projects");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid login credentials!");
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
