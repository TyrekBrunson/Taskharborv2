package controllers;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import library.App;

public class HomeController implements Initializable{

    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane Library_pane;

    @FXML
    private void onLoginClicked(ActionEvent event) {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onSignupClicked(ActionEvent event) {
        try {
            App.setRoot("signup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.fitWidthProperty().bind(Bindings.subtract(Library_pane.widthProperty(), 100.0));
        imageView.fitHeightProperty().bind(Bindings.subtract(Library_pane.heightProperty(), 100.0));
    }
}
