package org.ui.shared;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import org.context.SessionContext;

/**
 * Author: Jacob Stempin
 *
 * Handles user login / remembering credentials
 */
public class LoginController {

    @FXML
    private StackPane mainPane;

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
        rememberSwitch.setSelected(SessionContext.isRemembering());

        if (SessionContext.isRemembering()) {
            usernameField.setText(SessionContext.getPreference("login", "user"));
            passwordField.setText(SessionContext.getPreference("login", "pass"));
        }

        rememberSwitch.setOnAction((action) -> {
            SessionContext.setRemembering(rememberSwitch.selectedProperty().getValue());
        });

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            SessionContext.autoSetUser(newValue);
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            SessionContext.autoSetPass(newValue);
        });

        loginButton.setOnAction((action) -> {
            //just for now
            SessionContext.getManager().showView("Module");
        });
    }
}
