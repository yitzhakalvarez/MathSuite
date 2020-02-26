package org;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.context.SessionContext;
import org.ui.SceneManager;

public final class App extends Application {

    private SceneManager manager;

    public static void main(String[] args) {
        SessionContext.init();
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        final Scene scene = new Scene(new StackPane());
        (manager = new SceneManager(scene)).showView("Login");

        stage = new Stage();
        stage.setTitle("Math Suite");
        stage.setOnCloseRequest((event) -> {
            SessionContext.get().reset();
        });
        stage.setScene(scene);
        stage.show();
    }
}
