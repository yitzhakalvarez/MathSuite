import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class SpaceGame extends Application {

    public MediaPlayer mediaPlayer;

    public static int stageDamage = 0, question = 0;

    private static int currentRandom = randomizer(), score = 1;
    private static ArrayList<Button> buttons;
    private static String[] questions;
    private static Label questionLabel;
    private static ArrayList<Integer> possibleAnswers;

    public static Group questionContainer, previousG;

    public static Rock currentRock;
    public static Group currentContainer;
    public static AnchorPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Media sound = new Media(new File("src/8bitSound.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        root = new AnchorPane();
        Scene scene = new Scene(root);

        Image backdropImage = new Image(new File("src/SpaceBackdrop (2).jpg").toURI().toString());

        BackgroundImage backdrop = new BackgroundImage(backdropImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));


        possibleAnswers = new ArrayList<Integer>();


        HBox hbox = new HBox();   // contain that will hold the buttons
        hbox.setSpacing(125);

        buttons = new ArrayList<Button>();
        buttons.add(new Button());
        buttons.add(new Button());
        buttons.add(new Button());
        buttons.add(new Button());


        questionContainer = new Group();
        previousG = null;

        questions = new String[5];
        questions[0] = "Round 21 to the nearest 10.";
        questions[1] = "Round 55 to the nearest 10.";
        questions[2] = "Round 93 to the nearest 10.";
        questions[3] = "Round 8 to the nearest 10.";
        questions[4] = "Round 36 to the nearest 10.";

        questionLabel = new Label(questions[0]);
        questionLabel.getStyleClass().add("outline");
        questionLabel.setTextFill(Color.WHITE);
        questionContainer.getChildren().add(questionLabel);
        questionContainer.setLayoutX(625);
        questionContainer.setLayoutY(675);


        currentContainer = new Group();

        currentRock = new Rock();
        currentContainer.getChildren().add(currentRock.getImageView());
        currentRock.addTransition(currentContainer);
        root.getChildren().add(currentContainer);


        for (int i = 0; i < 4; i++) {
            buttons.get(i).setMinSize(75, 75);
            buttons.get(i).setMaxSize(75, 75);
            buttons.get(i).setPrefSize(75, 75);

            buttons.get(i).setStyle("-fx-font-size:22");

            //  For the correct answer button
            if (currentRandom == i)
                buttons.get(i).setText(Integer.toString(answer(question)));
            else
                buttons.get(i).setText(Integer.toString(randomAnswer()));

            int p = i;
            buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (currentRandom == p) {
                        if (currentRock.getPathTransition().getStatus().equals(Animation.Status.RUNNING) && question < 9) {
                            incrementScore();
                            destroyRock(currentRock, currentContainer, hbox, questionContainer);
                        }
                        if (question == 9) {
                            endGame();
                        }
                        else {
                            question++;
                            nextQuestion(question, root);
                        }
                    }
                    System.out.println(p + "\t" + currentRandom);   // For debugging with the randomizer
                }
            });
        }

        Image spacePerson = new Image(new File("src/PlayerSpriteAlien.png").toURI().toString());
        ImageView personView = new ImageView(spacePerson);
        personView.setFitHeight(200);
        personView.setFitWidth(200);
        Group personContainer = new Group();
        personContainer.getChildren().add(personView);
        personContainer.setLayoutX(500);
        personContainer.setLayoutY(900);

        Image spacePerson1 = new Image(new File("src/PlayerSprite.png").toURI().toString());
        ImageView personView1 = new ImageView(spacePerson1);
        personView1.setFitHeight(300);
        personView1.setFitWidth(300);
        Group personContainer1 = new Group();
        personContainer1.getChildren().add(personView1);
        personContainer1.setLayoutX(1000);
        personContainer1.setLayoutY(800);

        Image rockMountain = new Image(new File("src/rockMountain.png").toURI().toString());
        ImageView rockMountainView = new ImageView(rockMountain);
        rockMountainView.setFitHeight(800);
        rockMountainView.setFitWidth(800);
        Group rockMountainContainer = new Group();
        rockMountainContainer.getChildren().add(rockMountainView);
        rockMountainContainer.setLayoutX(-200);
        rockMountainContainer.setLayoutY(550);

        root.getChildren().addAll(personContainer, personContainer1, rockMountainContainer);

        hbox.setLayoutX(625);
        hbox.setLayoutY(800);
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(buttons.get(0), buttons.get(1), buttons.get(2), buttons.get(3));
        root.getChildren().add(hbox);
        root.getChildren().add(questionContainer);


        scene.getStylesheets().addAll(getClass().getResource(
                "textBorder.css"
        ).toExternalForm());

        root.setBackground(new Background(backdrop));
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


    //  randomizer for each question/level
    public static int randomizer() {
        Random r = new Random();
        return r.nextInt(4);
    }

    public static int answer(int q) {
        switch (q) {
            case 0:
                return 20;
            case 1:
                return 60;
            case 2:
                return 90;
            case 3:
                return 10;
            case 4:
                return 40;
            case 5:
                return 0;
            case 6:
                return 6;
            case 7:
                return 4;
            case 8:
                return 3;
            default:   //  Also answer to last question so 9 will be default
                return 1;
        }
    }

    public static int randomAnswer() {   //   needs fixing... possible to get the same answer twice
        if (possibleAnswers.size() == 4)
            possibleAnswers.clear();
        if (possibleAnswers.size() == 0)
            possibleAnswers.add(answer(question));
        Random r = new Random();
        int rInt;

        if (question < 5) {
            do {
                rInt = r.nextInt(10) * 10;
            } while (rInt == answer(question) || possibleAnswers.contains(rInt));
        }
        else {
            do {
                rInt = r.nextInt(10);
            } while (rInt == answer(question) || possibleAnswers.contains(rInt));
        }

        possibleAnswers.add(rInt);
        return rInt;
    }


    public static void nextQuestion(int q, AnchorPane root) {
        currentRandom = randomizer();

        for (int i = 0; i < 4; i++) {
            //  For the correct answer button
            if (currentRandom == i)
                buttons.get(i).setText(Integer.toString(answer(q)));
            else
                buttons.get(i).setText(Integer.toString(randomAnswer()));
        }

        if (q < 5) {
            questionLabel.setText(questions[q]);
            currentContainer = new Group();
            currentRock = new Rock();
            currentContainer.getChildren().add(currentRock.getImageView());
            currentRock.addTransition(currentContainer);
            root.getChildren().add(currentContainer);
        }
        else {
            questionLabel.setText("Find the equivalent fraction:");
            questionContainer.setLayoutX(625);
            questionContainer.setLayoutY(400);

            currentContainer = new Group();

            currentRock = new Rock();
            currentContainer.getChildren().add(currentRock.getImageView());
            currentRock.addTransition(currentContainer);
            root.getChildren().add(currentContainer);


            Group a = FractionQuestions.getCurrentQuestion(q);

            root.getChildren().add(a);
        }
    }



    public static void incrementScore() {
        score++;
    }



    public void destroyRock(Rock rock, Group rockContainer, HBox hbox, Group currentQuestionContainer)  {

        Media sound1 = new Media(new File("src/8-bit-explosion.mp3").toURI().toString());
        MediaPlayer rockExplosionSound = new MediaPlayer(sound1);
        rockExplosionSound.play();

        rock.setImage(new Image(new File("src/RockExplosion.png").toURI().toString()));
        rock.setImageView(new ImageView(rock.getImage()));

        rockContainer.getChildren().add(rock.getImageView());


        rock.getPathTransition().stop();

        FadeTransition ft = new FadeTransition(Duration.millis(1000), rockContainer);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(rockContainer);
                root.getChildren().remove(hbox);
                root.getChildren().add(hbox);
                root.getChildren().remove(currentQuestionContainer);
                root.getChildren().add(currentQuestionContainer);
                mediaPlayer.play();
            }
        });
    }

    public static void endGame() {
        root.getChildren().clear();

        Rectangle rect = new Rectangle(900, 600);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.HONEYDEW);

        Text text = new Text("GREAT JOB!\n\nYour Score: " + (score-stageDamage) + "\nStage Damage: " + stageDamage);
        text.setFill(Color.BLACK);
        text.setStyle("-fx-font: 30 arial;");
        StackPane stack = new StackPane();
        stack.getChildren().addAll(rect, text);

        stack.maxWidthProperty().bind(root.widthProperty());
        stack.minWidthProperty().bind(root.widthProperty());
        stack.maxHeightProperty().bind(root.heightProperty());
        stack.minHeightProperty().bind(root.heightProperty());
        root.getChildren().add(stack);
    }
}
