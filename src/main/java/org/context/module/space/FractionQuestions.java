package org.context.module.space;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class FractionQuestions {

    public static Group getCurrentQuestion(int number) {

        Group question = new Group();
        Image picture;
        ImageView viewQuestion;


        switch (number) {
            case 5:
                picture = new Image(FractionQuestions.class.getResourceAsStream("/module_assets/q5.PNG"));
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 6:
                picture = new Image(FractionQuestions.class.getResourceAsStream("/module_assets/q6.PNG"));
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 7:
                picture = new Image(FractionQuestions.class.getResourceAsStream("/module_assets/q7.PNG"));
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 8:
                picture = new Image(FractionQuestions.class.getResourceAsStream("/module_assets/q8.PNG"));
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 9:
                picture = new Image(FractionQuestions.class.getResourceAsStream("/module_assets/q9.PNG"));
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            default:
                return null;
        }

    }

}
