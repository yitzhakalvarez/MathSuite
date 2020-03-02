package org.context.module;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Author:  Alec Lehmphul
 * This class is creates a multiplication table which
 * will be part of the student's resources
 */
public class MultiplicationTable extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Multiplication Table");

        //  Creates the rectangle boxes for my grid(columns) and saves it in an ArrayList
        ArrayList<Rectangle> columns = new ArrayList<Rectangle>();
        for (int i = 0; i < 13; i++) {
            if (i == 0)
                columns.add(new Rectangle(30, 30, Color.SEAGREEN));
            else
                columns.add(new Rectangle(30, 30, Color.MEDIUMSPRINGGREEN));
        }

        //  Creates the rectangle boxes for my grid(rows) and saves it in an ArrayList
        ArrayList<Rectangle> rows = new ArrayList<Rectangle>();
        for (int i = 0; i < 13; i++) {
            if (i == 0)
                rows.add(new Rectangle(30, 30, Color.SEAGREEN));
            else
                rows.add(new Rectangle(30, 30, Color.MEDIUMSPRINGGREEN));
        }

        //  Text for the grid stored in an ArrayList
        ArrayList<Text> text = new ArrayList<Text>();
        text.add(new Text("   X"));
        text.add(new Text("   1"));
        text.add(new Text("   2"));
        text.add(new Text("   3"));
        text.add(new Text("   4"));
        text.add(new Text("   5"));
        text.add(new Text("   6"));
        text.add(new Text("   7"));
        text.add(new Text("   8"));
        text.add(new Text("   9"));
        text.add(new Text("  10"));
        text.add(new Text("  11"));
        text.add(new Text("  12"));

        //  Text for the grid stored in an ArrayList
        ArrayList<Text> text1 = new ArrayList<Text>();
        text1.add(new Text("   X"));
        text1.add(new Text("   1"));
        text1.add(new Text("   2"));
        text1.add(new Text("   3"));
        text1.add(new Text("   4"));
        text1.add(new Text("   5"));
        text1.add(new Text("   6"));
        text1.add(new Text("   7"));
        text1.add(new Text("   8"));
        text1.add(new Text("   9"));
        text1.add(new Text("  10"));
        text1.add(new Text("  11"));
        text1.add(new Text("  12"));

        //  Creates my grid pane, centers it within the window,
        //  and fills the background with the color white
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        //  This loop adds my columns and rows to the grid.
        //  (My rectangles and text are merged together)
        for (int i = 0; i < 13; i++) {
            pane.add(columns.get(i), i, 0);
            pane.add(text.get(i), i, 0);
            pane.add(rows.get(i), 0, i);
            pane.add(text1.get(i), 0, i);
        }

        //  This loop creates the rest of my table
        //  The rectangles in yellow_green are the squares of the number
        for (int k = 1; k < 13; k++) {
            for (int j = 1; j < 13; j++) {
                if (k == j)
                    pane.add(new Rectangle(30, 30, Color.YELLOWGREEN), k, j);
                else
                    pane.add(new Rectangle(30, 30, Color.WHITE), k, j);
                String s = "  " + (k*j);
                pane.add(new Text(s), k, j);
            }
        }

        //  Makes grid lines visible
        pane.setGridLinesVisible(true);

        //  Adds grid to window
        primaryStage.setScene(new Scene(pane, 500, 600));

        //  Makes window visible
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
