package org;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.context.SessionContext;
import org.ui.SceneManager;


public final class App extends Application {

    public static void main(String[] args) {
        SessionContext.init();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            stage = new Stage();
            stage.setTitle("Math Suite");
            stage.setOnCloseRequest((event) -> {
                SessionContext.get().reset();
            });

            final StackPane pane = FXMLLoader.load(getClass().getResource(("/fxml/Splash.fxml")));
            final Scene scene = new Scene(pane);
            final SceneManager manager = new SceneManager(scene, stage);
            SessionContext.setManager(manager);

            final FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            final FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeIn.play();

            fadeIn.setOnFinished((actionEvent) ->
                    fadeOut.play()
            );

            fadeOut.setOnFinished((actionEvent) -> {
                manager.showView("Login");
            });

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
