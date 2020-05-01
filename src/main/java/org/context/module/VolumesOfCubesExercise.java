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
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.IOException;

/**
 * Author: Yitzhak Alvarez
 */

public class VolumesOfCubesExercise extends Module {

    private Text correct1, correct2, correct3, correct4, correct5;
    int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0,
            incorrectCount1 = 0, incorrectCount2 = 0, incorrectCount3, incorrectCount4 = 0, incorrectCount5;

    @FXML
    private HBox Hb1;

    @FXML
    private TextField cuberoot;

    @FXML
    private HBox Hb2;

    @FXML
    private HBox Hb3;

    @FXML
    private HBox Hb4;

    @FXML
    private HBox Hb5;

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

    public void checkAnswers(ActionEvent actionEvent) {
        //  if question 1 is correct
    }
}