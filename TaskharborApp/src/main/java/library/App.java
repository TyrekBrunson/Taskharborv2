package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.util.Stack;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stack<String> navigationHistory = new Stack<>();

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the scene with a dummy root
        Parent dummyRoot = new Pane();
        scene = new Scene(dummyRoot, 800, 500);

        // Set the initial FXML to home
        setRoot("home");

        stage.setScene(scene);
        stage.setTitle("Taskharbor");
        stage.setResizable(true);
        stage.show();
        stage.toFront();
        stage.setOnCloseRequest(e -> {
            // Handle close request if needed
        });

        UiFacade facade = UiFacade.getInstance();
    }

    public static void setRoot(String fxml) throws IOException {
        if (!fxml.equals(getCurrentFXML())) {
            Parent root = loadFXML(fxml);
            scene.setRoot(root);
            navigationHistory.push(fxml);

            // Print for debugging purposes
            System.out.println("Setting root to: " + fxml);
            System.out.println("Navigation History: " + navigationHistory);
        }
    }

    public static void navigateBack() throws IOException {
        // Check if there is a previous FXML
        if (navigationHistory.size() > 1) {
            // Pop the stack to get the previous FXML
            navigationHistory.pop();

            String lastLoadedFXML = navigationHistory.peek();

            // Print for debugging purposes
            System.out.println("Going back to: " + lastLoadedFXML);

            // Update the scene root
            Parent root = loadFXML(lastLoadedFXML);
            scene.setRoot(root);

            // Print for debugging purposes
            System.out.println("Navigation History: " + navigationHistory);
        } else {
            System.out.println("No previous FXML found");
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static String getCurrentFXML() {
        if (!navigationHistory.isEmpty()) {
            return navigationHistory.peek();
        }
        return null;
    }

    public static String getLastLoadedFXML() {
        if (navigationHistory.size() > 1) {
            return navigationHistory.get(navigationHistory.size() - 2);
        }
        return null;
    }

    public static void main(String[] args) {
        launch();
    }
}
