module library {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires json.simple;

    opens library to javafx.fxml;
    exports library;

    opens controllers to javafx.fxml;
    exports controllers;

    opens model to javafx.fxml;
    exports model;
}