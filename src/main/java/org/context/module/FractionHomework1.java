package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Author:  Alec Lehmphul
 *   This class is an example exercise or homework assignment
 *   that the teacher can assign.  This was built using scene builder
 *   and needs the classes "Fraction1Scene.fxml" as the fxml file and
 *   "Fraction1Scene.java" as the controller class to run.
 */

public class FractionHomework1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //  Reads in the fxml file which holds all components and action events
        Parent root = FXMLLoader.load(getClass().getResource("Fraction1Scene.fxml"));
        primaryStage.setTitle("Faction Homework 1");
        primaryStage.setScene(new Scene(root, 605, 395));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
