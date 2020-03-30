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
                picture = new Image(new File("src/q5.PNG").toURI().toString());
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 6:
                picture = new Image(new File("src/q6.PNG").toURI().toString());
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 7:
                picture = new Image(new File("src/q7.PNG").toURI().toString());
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 8:
                picture = new Image(new File("src/q8.PNG").toURI().toString());
                viewQuestion = new ImageView(picture);
                question.getChildren().add(viewQuestion);
                question.setVisible(true);
                question.setLayoutX(870);
                question.setLayoutY(600);
                return question;

            case 9:
                picture = new Image(new File("src/q9.PNG").toURI().toString());
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
