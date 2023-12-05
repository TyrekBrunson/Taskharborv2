package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import library.App;

public class HomeController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    

}
