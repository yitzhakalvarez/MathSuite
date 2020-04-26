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
 * Author:  Alec Lehmphul
 * This class is the controller class for "FractionExercise".  It holds
 * all the components and actionEvents for the exercise.
 */

public class FractionExercise extends Module {

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

    @Override
    public Parent build() throws IOException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Pane parent = FXMLLoader.load(getClass().getResource("/fxml/FractionExercise.fxml"));
        parent.setPadding(new Insets(primaryScreenBounds.getMaxY()/2.0 - 395/2.0, primaryScreenBounds.getMaxX()/2.0 - 565/2.0, primaryScreenBounds.getMaxY()/2.0 - 395/2.0, primaryScreenBounds.getMaxX()/2.0 - 565/2.0));
        return parent;
    }

    public void checkAnswers(ActionEvent actionEvent) {
        //  if question 1 is correct
        if (q1Num.getText().equals("2") && q1Denom.getText().equals("3")) {
            if (count1 == 0) {
                hbox1.getChildren().removeAll(correct1);
                correct1 = new Text("    CORRECT");
                correct1.setTranslateY(6);
                correct1.setTextAlignment(TextAlignment.CENTER);
                correct1.setFont(new Font(20));
                hbox1.getChildren().add(correct1);
                count1++;
            }

            q1Num.setEditable(false);
            q1Denom.setEditable(false);
        }
        else {
            if (incorrectCount1 == 0) {
                correct1 = new Text("     Incorrect, try again");
                correct1.setTranslateY(6);
                correct1.setTextAlignment(TextAlignment.CENTER);
                correct1.setFont(new Font(20));
                hbox1.getChildren().add(correct1);
                incorrectCount1++;
            }
        }

        //  if question 2 is correct
        if ((q2Num.getText().equals("4") && q2Denom.getText().equals("6")) ||
                (q2Num.getText().equals("2") && q2Denom.getText().equals("3"))) {
            if (count2 == 0) {
                hbox2.getChildren().removeAll(correct2);
                correct2 = new Text("    CORRECT");
                correct2.setTranslateY(6);
                correct2.setTextAlignment(TextAlignment.CENTER);
                correct2.setFont(new Font(20));
                hbox2.getChildren().add(correct2);
                count2++;
            }

            q2Num.setEditable(false);
            q2Denom.setEditable(false);
        }
        else {
            if (incorrectCount2 == 0) {
                correct2 = new Text("     Incorrect, try again");
                correct2.setTranslateY(6);
                correct2.setTextAlignment(TextAlignment.CENTER);
                correct2.setFont(new Font(20));
                hbox2.getChildren().add(correct2);
                incorrectCount2++;
            }
        }

        //  if question 3 is correct
        if ((q3Num.getText().equals("4") && q3Denom.getText().equals("12")) ||
                (q3Num.getText().equals("1") && q3Denom.getText().equals("3"))) {
            if (count3 == 0) {
                hbox3.getChildren().removeAll(correct3);
                correct3 = new Text("    CORRECT");
                correct3.setTranslateY(6);
                correct3.setTextAlignment(TextAlignment.CENTER);
                correct3.setFont(new Font(20));
                hbox3.getChildren().add(correct3);
                count3++;
            }

            q3Num.setEditable(false);
            q3Denom.setEditable(false);
        }
        else {
            if (incorrectCount3 == 0) {
                correct3 = new Text("     Incorrect, try again");
                correct3.setTranslateY(6);
                correct3.setTextAlignment(TextAlignment.CENTER);
                correct3.setFont(new Font(20));
                hbox3.getChildren().add(correct3);
                incorrectCount3++;
            }
        }

        //  if question 4 is correct
        if (q4Num.getText().equals("9") && q4Denom.getText().equals("100")) {
            if (count4 == 0) {
                hbox4.getChildren().removeAll(correct4);
                correct4 = new Text("    CORRECT");
                correct4.setTranslateY(6);
                correct4.setTextAlignment(TextAlignment.CENTER);
                correct4.setFont(new Font(20));
                hbox4.getChildren().add(correct4);
                count4++;
            }

            q4Num.setEditable(false);
            q4Denom.setEditable(false);
        }
        else {
            if (incorrectCount4 == 0) {
                correct4 = new Text("     Incorrect, try again");
                correct4.setTranslateY(6);
                correct4.setTextAlignment(TextAlignment.CENTER);
                correct4.setFont(new Font(20));
                hbox4.getChildren().add(correct4);
                incorrectCount4++;
            }
        }

        //  if question 5 is correct
        if (q5Num.getText().equals("7") && q5Denom.getText().equals("10")) {
            if (count5 == 0) {
                hbox5.getChildren().removeAll(correct5);
                correct5 = new Text("    CORRECT");
                correct5.setTranslateY(6);
                correct5.setTextAlignment(TextAlignment.CENTER);
                correct5.setFont(new Font(20));
                hbox5.getChildren().add(correct5);
                count5++;
            }

            q5Num.setEditable(false);
            q5Denom.setEditable(false);
        }
        else {
            if (incorrectCount5 == 0) {
                correct5 = new Text("     Incorrect, try again");
                correct5.setTranslateY(6);
                correct5.setTextAlignment(TextAlignment.CENTER);
                correct5.setFont(new Font(20));
                hbox5.getChildren().add(correct5);
                incorrectCount5++;
            }
        }
    }
}
