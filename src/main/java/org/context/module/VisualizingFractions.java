package org.context.module;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Author: Alec Lehmphul
 * This class helps students to visualize factions of circles.
 * These charts can be accessed through student resources.
 */

public class VisualizingFractions extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene;
        GridPane pane = new GridPane();
        PieChart chart = new PieChart();
        VBox vbox = new VBox();  // Will hold info on n size


        //  Creates buttons and adds them to grid
        Button half = new Button("Halves");
        pane.add(half, 0, 0);

        Button third = new Button("Thirds");
        pane.add(third, 0, 1);
        pane.setPadding(new Insets(10, 10, 10, 10));

        Button forth = new Button("Forths");
        pane.add(forth, 0, 2);


        // gives the 1st button an event handler
        // shows the circle split into halves
        half.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1));
                chart.setData(pieChartData);
                chart.setTitle("Two-Halves");
                pane.add(chart, 1, 1);
            }
        });


        // gives the 2st button an event handler
        // shows the circle split into thirds
        third.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1));
                chart.setData(pieChartData);
                chart.setTitle("Three-Thirds");
                pane.add(chart, 1, 1);
            }
        });

        // gives the 3st button an event handler
        // shows the circle split into forths
        forth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1),
                                new PieChart.Data("", 1));
                chart.setData(pieChartData);
                chart.setTitle("Four-Forths");
                pane.add(chart, 1, 1);
            }
        });

        // sets gaps both horizontally and vertically
        pane.setHgap(10);
        pane.setVgap(12);

        // creates the chart for the full circle (1/1)
        ObservableList<PieChart.Data> fullCircle =
                FXCollections.observableArrayList(new PieChart.Data("", 1));
        chart.setData(fullCircle);
        chart.setTitle("Full Circle");
        pane.add(chart, 1, 1);


        //  creates text field and button
        //  where the circle is divided into n number
        //  of equal parts
        TextField nField = new TextField();
        nField.setText("1");
        Label nInfo = new Label("Enter a number and the circle\n" +
                " will be split into that many equal parts.\n" +
                " Press the button below to show.");
        Button nButton = new Button("1/n");

        vbox.getChildren().addAll(nInfo, nField, nButton);
        vbox.setSpacing(10.0);
        pane.add(vbox, 1, 5);

        nButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("", 1));
                int n = Integer.parseInt(nField.getText());

                for (int i = 1; i < n; i++)
                    pieChartData.add(new PieChart.Data("", 1));
                chart.setData(pieChartData);
                if (n == 1)
                    chart.setTitle("Full Circle");
                else
                    chart.setTitle("One Circle Divided into " + nField.getText() + " Equal Parts");
                pane.add(chart, 1, 1);
            }
        });


        //  sets and finalizes all the pane and stage attributes
        stage.setTitle("Factions Represented Through a Circle");
        stage.setWidth(800);
        stage.setHeight(800);
        pane.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(pane, 700, 700);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}