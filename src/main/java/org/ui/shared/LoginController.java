package org.ui.shared;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane loginPane;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXToggleButton rememberSwitch;

    /**
     * Component initialization goes here.
     * THis method is called internally by the JavaFX Library.
     */
    @FXML
    private void initialize() {
        rememberSwitch.setOnAction((toggle) -> System.out.println(rememberSwitch.isPressed()));
    }
}
