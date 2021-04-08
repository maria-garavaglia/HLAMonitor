module com.github.tylergaravaglia.hlamonitor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.tylergaravaglia.hlamonitor to javafx.fxml;
    exports com.github.tylergaravaglia.hlamonitor;
}