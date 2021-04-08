module com.github.tylergaravaglia.hlamonitor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.tylergaravaglia.hlamonitor to javafx.fxml;
    exports com.github.tylergaravaglia.hlamonitor;
    exports com.github.tylergaravaglia.hlamonitor.controllers;
    opens com.github.tylergaravaglia.hlamonitor.controllers to javafx.fxml;
}