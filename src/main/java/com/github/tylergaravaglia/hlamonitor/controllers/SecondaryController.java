package com.github.tylergaravaglia.hlamonitor.controllers;

import java.io.IOException;

import com.github.tylergaravaglia.hlamonitor.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}