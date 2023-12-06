package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import model.UiFacade;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("home");
        scene = new Scene(root, 640, 480);
    
        stage.setScene(scene);
        stage.setTitle("Taskharbor");
        stage.setResizable(true);
        stage.show();
        stage.toFront();
        stage.setOnCloseRequest(e -> {
        UiFacade.getInstance();
        });
    }
    

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}