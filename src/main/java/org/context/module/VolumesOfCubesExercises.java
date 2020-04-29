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

public class VolumesOfCubesExercises extends Module
{
    private Text correct1, correct2, correct3, correct4, correct5;
    int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0,
            incorrectCount1 = 0, incorrectCount2 = 0, incorrectCount3, incorrectCount4 = 0, incorrectCount5;

    @FXML
    private HBox hbox1;

    @FXML
    private TextField q1Num;

    @FXML
    private TextField q1Denom;

    @FXML
    private HBox hbox2;

    @FXML
    private TextField q2Num;

    @FXML
    private TextField q2Denom;

    @FXML
    private HBox hbox3;

    @FXML
    private TextField q3Num;

    @FXML
    private TextField q3Denom;

    @FXML
    private HBox hbox4;

    @FXML
    private TextField q4Num;

    @FXML
    private TextField q4Denom;

    @FXML
    private HBox hbox5;

    @FXML
    private TextField q5Num;

    @FXML
    private TextField q5Denom;

    @FXML
    private Button button;
}
