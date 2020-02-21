package org;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.ui.UIContext;

public class App extends Application {

    private UIContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new StackPane());

        stage = new Stage();
        (context = new UIContext(scene, stage)).showLogin();

        stage.setScene(scene);
        stage.show();
    }
}
