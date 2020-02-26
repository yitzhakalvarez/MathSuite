package org.ui.shared;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import org.context.SessionContext;

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
        rememberSwitch.setSelected(SessionContext.get().isRemembering());

        if (SessionContext.get().isRemembering()) {
            usernameField.setText(SessionContext.get().getPreference("login", "user"));
            passwordField.setText(SessionContext.get().getPreference("login", "pass"));
        }

        rememberSwitch.setOnAction((action) -> {
            SessionContext.get().setRemembering(rememberSwitch.selectedProperty().getValue());
        });

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            SessionContext.get().autoSetUser(newValue);
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            SessionContext.get().autoSetPass(newValue);
        });

        loginButton.setOnAction((action) -> {

        });
    }
}
