package org.context.module;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

import java.io.IOException;

/**
 * Author: Yitzhak Alvarez
 */

public class VolumesOfCubesExercise extends Module {
    private Text goodjob1, goodjob2, goodjob3, goodjob4, goodjob5;
    int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0,
            incorrectCount1 = 0, incorrectCount2 = 0, incorrectCount3, incorrectCount4 = 0, incorrectCount5;

    @FXML
    private HBox Hb1;

    @FXML
    private TextField cube1;

    @FXML
    private HBox Hb2;

    @FXML
    private TextField cube2;

    @FXML
    private HBox Hb3;

    @FXML
    private TextField cube3;

    @FXML
    private HBox Hb4;

    @FXML
    private TextField cube4;

    @FXML
    private HBox Hb5;

    @FXML
    private TextField cube5;

    @FXML
    private Button button;

    @Override
    public Parent setup() throws IOException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Pane parent = FXMLLoader.load(getClass().getResource("/fxml/VolumesOfCubesExercise.fxml"));
        parent.setPadding(new Insets(primaryScreenBounds.getMaxY() / 2.0 - 395 / 2.0, primaryScreenBounds.getMaxX() / 2.0 - 565 / 2.0, primaryScreenBounds.getMaxY() / 2.0 - 395 / 2.0, primaryScreenBounds.getMaxX() / 2.0 - 565 / 2.0));
        parent.setScaleX(1.75);
        parent.setScaleY(1.75);
        return parent;
    }

    public void check() {
        // question 1
        if (cube1.getText().equals("3")) {
            if (count1 == 0) {
                Hb1.getChildren().removeAll(goodjob1);
                goodjob1 = new Text("               EXCELLENT");
                goodjob1.setTranslateY(6);
                goodjob1.setTextAlignment(TextAlignment.CENTER);
                goodjob1.setFont(new Font(15));
                Hb1.getChildren().add(goodjob1);
                count1++;
            }

            cube1.setEditable(false);
        } else {
            if (incorrectCount1 == 0) {
                goodjob1 = new Text("           Please try again");
                goodjob1.setTranslateY(6);
                goodjob1.setTextAlignment(TextAlignment.CENTER);
                goodjob1.setFont(new Font(15));
                Hb1.getChildren().add(goodjob1);
                incorrectCount1++;
            }
        }

        // question 2
        if (cube2.getText().equals("3")) {
            if (count2 == 0) {
                Hb2.getChildren().removeAll(goodjob2);
                goodjob2 = new Text("               EXCELLENT");
                goodjob2.setTranslateY(6);
                goodjob2.setTextAlignment(TextAlignment.CENTER);
                goodjob2.setFont(new Font(15));
                Hb2.getChildren().add(goodjob2);
                count2++;
            }

            cube2.setEditable(false);

        } else {
            if (incorrectCount2 == 0) {
                goodjob2 = new Text("           Please try again");
                goodjob2.setTranslateY(6);
                goodjob2.setTextAlignment(TextAlignment.CENTER);
                goodjob2.setFont(new Font(15));
                Hb2.getChildren().add(goodjob2);
                incorrectCount2++;
            }
        }

        // question 3
        if (cube3.getText().equals("8")) {
            if (count3 == 0) {
                Hb3.getChildren().removeAll(goodjob3);
                goodjob3 = new Text("               EXCELLENT");
                goodjob3.setTranslateY(6);
                goodjob3.setTextAlignment(TextAlignment.CENTER);
                goodjob3.setFont(new Font(15));
                Hb3.getChildren().add(goodjob3);
                count3++;
            }

            cube3.setEditable(false);

        } else {
            if (incorrectCount3 == 0) {
                goodjob3 = new Text("           Please try again");
                goodjob3.setTranslateY(6);
                goodjob3.setTextAlignment(TextAlignment.CENTER);
                goodjob3.setFont(new Font(15));
                Hb3.getChildren().add(goodjob3);
                incorrectCount3++;
            }
        }

        // question 4
        if (cube4.getText().equals("4")) {
            if (count4 == 0) {
                Hb4.getChildren().removeAll(goodjob4);
                goodjob4 = new Text("               EXCELLENT");
                goodjob4.setTranslateY(6);
                goodjob4.setTextAlignment(TextAlignment.CENTER);
                goodjob4.setFont(new Font(15));
                Hb4.getChildren().add(goodjob4);
                count4++;
            }

            cube4.setEditable(false);

        } else {
            if (incorrectCount4 == 0) {
                goodjob4 = new Text("               Please try again");
                goodjob4.setTranslateY(6);
                goodjob4.setTextAlignment(TextAlignment.CENTER);
                goodjob4.setFont(new Font(15));
                Hb4.getChildren().add(goodjob4);
                incorrectCount4++;
            }
        }

        // question 5
        if (cube5.getText().equals("6")) {
            if (count5 == 0) {
                Hb5.getChildren().removeAll(goodjob5);
                goodjob5 = new Text("               EXCELLENT");
                goodjob5.setTranslateY(6);
                goodjob5.setTextAlignment(TextAlignment.CENTER);
                goodjob5.setFont(new Font(15));
                Hb5.getChildren().add(goodjob5);
                count5++;
            }

            cube5.setEditable(false);

        } else {
            if (incorrectCount5 == 0) {
                goodjob5 = new Text("               Please try again");
                goodjob5.setTranslateY(6);
                goodjob5.setTextAlignment(TextAlignment.CENTER);
                goodjob5.setFont(new Font(15));
                Hb5.getChildren().add(goodjob5);
                incorrectCount5++;
            }
        }
    }
}