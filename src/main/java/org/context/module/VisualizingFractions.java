package org.context.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * Author: Alec Lehmphul
 * This class helps students to visualize factions of circles.
 * These charts can be accessed through student resources.
 */

public class VisualizingFractions extends Module {

    @Override
    public Parent setup() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds(); // Gets screen bounds

        StackPane pane = new StackPane();  // Base pane
        PieChart chart = new PieChart();
        VBox nSize = new VBox();  // Will hold info on n size
        VBox vbox = new VBox();  // Will hol all the charts and buttons

        //  Creates buttons and adds them to grid
        HBox buttons = new HBox();
        Button half = new Button("Halves");
        half.setStyle("-fx-font: 20 arial;");

        Button third = new Button("Thirds");
        third.setStyle("-fx-font: 20" +
                " arial;");
        Button forth = new Button("Forths");
        forth.setStyle("-fx-font: 20 arial;");

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
            }
        });

        // sets gaps both horizontally and vertically
        buttons.setSpacing(20);
        vbox.setSpacing(primaryScreenBounds.getMaxY()/25);
        nSize.setSpacing(25);
        nSize.setPadding(new Insets(20, 0, 0, 0));

        // creates the chart for the full circle (1/1)
        ObservableList<PieChart.Data> fullCircle =
                FXCollections.observableArrayList(new PieChart.Data("", 1));
        chart.setData(fullCircle);
        chart.setTitle("Full Circle");
        vbox.getChildren().add(chart);


        //  creates text field and button
        //  where the circle is divided into n number
        //  of equal parts
        TextField nField = new TextField();
        nField.setText("1");
        nField.setMaxWidth(primaryScreenBounds.getMaxX()/8.0);
        nField.setScaleX(1.5);
        nField.setScaleY(1.5);
        Text nInfo = new Text("Enter a number and the circle\n" +
                " will be split into that many equal parts.\n" +
                " Press the button below to show.");
        Button nButton = new Button("1/n");
        nButton.setStyle("-fx-font: 20 arial;");
        Text tooBig = new Text("");
        nInfo.setStyle("-fx-font: 20 arial;");
        tooBig.setStyle("-fx-font: 20 arial;");

        nSize.getChildren().addAll(nInfo, nField, nButton, tooBig);
        nSize.setSpacing(10.0);

        nButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("", 1));
                int n = Integer.parseInt(nField.getText());
                if (n >= 100)
                    tooBig.setText("Please enter a number smaller than 100.");
                else if (n == 0)
                    tooBig.setText("Please enter a number greater than 0.");
                else if (n < 0)
                    tooBig.setText("Please enter a positive number.");
                else {
                    tooBig.setText("");
                    for (int i = 1; i < n; i++)
                        pieChartData.add(new PieChart.Data("", 1));
                    chart.setData(pieChartData);
                    if (n == 1)
                        chart.setTitle("Full Circle");
                    else
                        chart.setTitle("One Circle Divided into " + nField.getText() + " Equal Parts");
                }
            }
        });
        buttons.getChildren().addAll(half, third, forth);
        vbox.getChildren().addAll(buttons, nSize);
        pane.getChildren().add(vbox);

        pane.setAlignment(Pos.CENTER);
        nSize.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.setPrefSize(primaryScreenBounds.getMaxX(), primaryScreenBounds.getMaxY());

        //parent = pane;
        return pane;
    }
}