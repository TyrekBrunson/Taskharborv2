package controllers;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

        URL imageUrl = getClass().getResource("TaskharborApp/src/main/java/images/Anchor.png");

        if (imageUrl != null) {
            // Create an Image and set it to the ImageView
            Image image = new Image(imageUrl.toExternalForm());
            imageView.setImage(image);
        } else {
            System.err.println("Image not found");
        }
    }
}
