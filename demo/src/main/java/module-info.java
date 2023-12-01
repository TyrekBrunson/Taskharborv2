module taskharbor {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens taskharbor to javafx.fxml;

    exports taskharbor;

    opens controllers to javafx.fxml;

    exports controllers;

    opens model to javafx.fxml;
}
