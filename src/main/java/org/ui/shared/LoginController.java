package org.ui.shared;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import org.context.SessionContext;


public class LoginController {

    public LoginController() {
    }

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
        usernameField.setText((String) SessionContext.credentials.getOrDefault("user", ""));
        passwordField.setText((String) SessionContext.credentials.getOrDefault("pass", ""));

        loginButton.setOnAction((action) -> {

        });
    }
}
