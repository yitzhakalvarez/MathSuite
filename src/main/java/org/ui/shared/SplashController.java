package org.ui.shared;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SplashController {

    @FXML
    private JFXButton button;

    @FXML
    private Label label;

    public void initialize() {

        button.setOnAction((action) -> {
            System.out.println("I was clicked");
        });
    }
}

