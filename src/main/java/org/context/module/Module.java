package org.context.module;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.context.SessionContext;

import java.io.IOException;

/**
 * Author: Jacob Stempin
 *
 * General Module class with one method that returns a Parent
 */
public abstract class Module {

    public Module() {
    }

    //not to be overridden
    public final void init() {
        System.out.println(getClass().getSimpleName() + " module has started");
    }

    public abstract Parent setup() throws IOException;

    public final Parent build() throws IOException {
        final JFXButton backButton = new JFXButton("", new ImageView(new Image(getClass().getResourceAsStream("/images/back.png"), 40.0, 40.0, false, false)));
        backButton.setOnAction(event -> {
            SessionContext.getManager().showView("Module");
        });
        return new VBox(setup(), backButton);
    }
}
