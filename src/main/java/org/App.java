package org;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.context.SessionContext;
import org.ui.SceneManager;

public final class App extends Application {

    public static void main(String[] args) {
        SessionContext.init();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = new Stage();
        stage.setTitle("Math Suite");
        stage.setOnCloseRequest((event) -> {
            SessionContext.get().reset();
        });

        final Scene scene = new Scene(new StackPane());
        final SceneManager manager = new SceneManager(scene, stage);
        SessionContext.setManager(manager);
        manager.showView("Login");

        stage.setScene(scene);

        stage.show();
    }
}
