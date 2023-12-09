package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import library.App;
import model.UiFacade;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private HBox loginPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text loginMessage;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

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
            loginMessage.setText("Invalid login credentials!");
            loginMessage.setVisible(true);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            App.navigateBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
