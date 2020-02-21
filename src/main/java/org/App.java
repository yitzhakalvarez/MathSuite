package org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        new MathSuiteContext();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //test commit
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
        Scene scene = new Scene(parent);

       // Scene scene = new Scene(flow,1000, 550);

        stage = new Stage();
        stage.setTitle("Administrator Dashboard");
        stage.setScene(scene);
        stage.show();

        /*
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.setRotateGraphic(false);
        tabPane.setSide(Side.LEFT);
        tabPane.setPrefSize(400, 1000);

        Tab studentOverview = new Tab();
        Label studentLabel = new Label("Student Overview");
        //makes it horizontal
        studentLabel.setRotate(90);
        StackPane studentPane = new StackPane(new Group(studentLabel));
        studentOverview.setContent(new Label("Content"));
        studentOverview.setGraphic(studentPane);


        Tab classOverview = new Tab();
        Label classLabel = new Label("Class Overview");
        //makes it horizontal
        classLabel.setRotate(90);
        StackPane classPane = new StackPane(new Group(classLabel));
        classOverview.setContent(new Label("Content"));
        classOverview.setGraphic(classPane);

        tabPane.getTabs().addAll(studentOverview, classOverview);

        FlowPane flow = new FlowPane();
        flow.getChildren().add(tabPane);

        //Creating a Scene by passing the group object, height and width
        Scene scene = new Scene(flow,1000, 550);

        stage = new Stage();
        stage.setTitle("Sample Application");
        stage.setScene(scene);
        stage.show();*/
    }
}
