package org.context.module.space;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.context.module.Module;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


/**
 * Author:  Alec Lehmphul
 * This class is creates a Space Game module
 * and keeps track of scores
 */


public class SpaceGame extends Module {

    public MediaPlayer mediaPlayer;
    public static Rectangle2D primaryScreenBounds;

    public static int stageDamage = 0, question = 0, guesses = 0;
    public static double progress = 0;

    private static int currentRandom = randomizer(), score = 1;
    private static ArrayList<Button> buttons;
    private static String[] questions;
    private static Label questionLabel;
    private static ArrayList<Integer> possibleAnswers;

    public static Group  previousG;
    public static HBox questionContainer;
    public static ProgressBar progressBar;
    public static Text damageText;
    public static Rock currentRock;
    public static Group currentContainer;
    public static BorderPane root;

    @Override
    public Parent build() {

        Media sound = new Media(getClass().getResource("/module_assets/8bitSound.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        root = new BorderPane();
        Image backdropImage = new Image(getClass().getResourceAsStream("/module_assets/SpaceBackdrop (2).jpg"));

        BackgroundImage backdrop = new BackgroundImage(backdropImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));


        possibleAnswers = new ArrayList<Integer>();


        HBox hbox = new HBox();   // contain that will hold the buttons
        hbox.setSpacing(primaryScreenBounds.getMaxX()/8.0);

        buttons = new ArrayList<Button>();
        buttons.add(new Button());
        buttons.add(new Button());
        buttons.add(new Button());
        buttons.add(new Button());


        questionContainer = new HBox();
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
        //questionContainer.setLayoutX(primaryScreenBounds.getMaxX()/3.0);
        //questionContainer.setLayoutY(primaryScreenBounds.getMaxY()/3.0);


        currentContainer = new Group();

        currentRock = new Rock();
        currentContainer.getChildren().add(currentRock.getImageView());
        currentRock.addTransition(currentContainer);
        root.getChildren().add(currentContainer);


        progressBar = new ProgressBar(0);

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
                            progress += (1.0/9.0);
                            progressBar.setProgress(progress);
                        }
                        if (question == 9) {
                            currentContainer.setVisible(false);
                            endGame();
                        }
                        else {
                            question++;
                            nextQuestion(question, root);
                        }
                    }
                    else {
                        guesses++;
                        damageText.setText("Guesses/Rock Damage Left: " + (4 - (stageDamage + guesses)));
                        if (guesses + stageDamage > 4)
                            Rock.gameOver(root, false);
                    }
                    //  System.out.println(p + "\t" + currentRandom);   -- For debugging with the randomizer
                }
            });
        }

        Image spacePerson = new Image(getClass().getResourceAsStream("/module_assets/PlayerSpriteAlien.png"));
        ImageView personView = new ImageView(spacePerson);
        personView.setFitHeight(primaryScreenBounds.getMaxX()/10.0);
        personView.setFitWidth(primaryScreenBounds.getMaxX()/10.0);
        Group personContainer = new Group();
        personContainer.getChildren().add(personView);
        personContainer.setLayoutX(primaryScreenBounds.getMaxX()/2.0 - primaryScreenBounds.getMaxX()/3.0);
        personContainer.setLayoutY(primaryScreenBounds.getMaxY() - primaryScreenBounds.getMaxY()/6.5);

        Image spacePerson1 = new Image(getClass().getResourceAsStream("/module_assets/PlayerSprite.png"));
        ImageView personView1 = new ImageView(spacePerson1);
        personView1.setFitHeight(primaryScreenBounds.getMaxX()/8);
        personView1.setFitWidth(primaryScreenBounds.getMaxX()/8);
        Group personContainer1 = new Group();
        personContainer1.getChildren().add(personView1);
        personContainer1.setLayoutX(primaryScreenBounds.getMaxX()/2.0 + primaryScreenBounds.getMaxX()/7.0);
        personContainer1.setLayoutY(primaryScreenBounds.getMaxY() - primaryScreenBounds.getMaxY()/5);

        Image rockMountain = new Image(getClass().getResourceAsStream("/module_assets/rockMountain.png"));
        ImageView rockMountainView = new ImageView(rockMountain);
        rockMountainView.setFitHeight(primaryScreenBounds.getMaxX()/3.0);
        rockMountainView.setFitWidth(primaryScreenBounds.getMaxX()/3.0);
        Group rockMountainContainer = new Group();
        rockMountainContainer.getChildren().add(rockMountainView);
        rockMountainContainer.setLayoutX(primaryScreenBounds.getMinX() - primaryScreenBounds.getMaxX()/7.5);
        rockMountainContainer.setLayoutY(primaryScreenBounds.getMaxY()/1.65);

        root.getChildren().addAll(rockMountainContainer, personContainer, personContainer1);


        damageText = new Text("Incorrect Answers/Rock Damage Left: " + (4 - (stageDamage + guesses)));
        damageText.setFill(Color.WHITE);
        damageText.setStyle("-fx-font: 26 arial;");

        HBox progress = new HBox();
        Text progressText = new Text("Progress: ");
        progressText.setFill(Color.WHITE);
        progressText.setStyle("-fx-font: 26 arial;");

        progress.getChildren().addAll(progressText, progressBar);
        progress.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(damageText, progress);
        vBox.setSpacing(25);
        //damageIndicator.setTranslateX(primaryScreenBounds.getMaxX()/2.0);
        vBox.setAlignment(Pos.CENTER);
        root.setTop(vBox);


        hbox.setLayoutX(primaryScreenBounds.getMinX()/4.0);
        hbox.setLayoutY((primaryScreenBounds.getMaxY()/3.0)*2);
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(buttons.get(0), buttons.get(1), buttons.get(2), buttons.get(3));
        hbox.setPadding(new Insets(0, 0, primaryScreenBounds.getMaxY()/4.0, 0));

        questionContainer.setAlignment(Pos.CENTER);
        root.setCenter(questionContainer);
        root.setBottom(hbox);


        root.getStylesheets().addAll(getClass().getResource(
                "/module_assets/textBorder.css"
        ).toExternalForm());

        root.setBackground(new Background(backdrop));

        return root;
       // stage.setScene(scene);
       // stage.setFullScreen(true);
       // stage.show();
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


    public static void nextQuestion(int q, BorderPane root) {
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

            currentContainer = new Group();

            currentRock = new Rock();
            currentContainer.getChildren().add(currentRock.getImageView());
            currentRock.addTransition(currentContainer);
            root.getChildren().add(currentContainer);


            Group a = FractionQuestions.getCurrentQuestion(q);
            root.setCenter(a);
        }
    }



    public static void incrementScore() {
        score++;
    }



    public void destroyRock(Rock rock, Group rockContainer, HBox hbox, HBox currentQuestionContainer)  {

        Media sound1 = new Media(getClass().getResource("/module_assets/8-bit-explosion.mp3").toString());
        MediaPlayer rockExplosionSound = new MediaPlayer(sound1);
        rockExplosionSound.play();

        rock.setImage(new Image(getClass().getResourceAsStream("/module_assets/RockExplosion.png")));
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

        Rectangle rect = new Rectangle(primaryScreenBounds.getMaxX() - primaryScreenBounds.getMaxX()/3.0, primaryScreenBounds.getMaxY() - primaryScreenBounds.getMaxY()/3.0);
        rect.setFill(Color.DARKBLUE);
        rect.setStroke(Color.HONEYDEW);

        Image star = new Image(SpaceGame.class.getResourceAsStream("/module_assets/Star.png"));
        ImageView starView = new ImageView(star);
        starView.setFitHeight(primaryScreenBounds.getMaxX()/8);
        starView.setFitWidth(primaryScreenBounds.getMaxX()/8);
        Group starContainer = new Group();
        starContainer.getChildren().add(starView);

        starContainer.translateYProperty().setValue(primaryScreenBounds.getMaxY()/-5.0);
        starContainer.translateXProperty().setValue(primaryScreenBounds.getMaxX()/-5.0);
        StackPane stack = new StackPane();
        stack.getChildren().add(starContainer);

        Text text = new Text("GREAT JOB!\n\nYour Score: " + (score-(stageDamage+guesses)) + "\nStage Damage: "
                            + stageDamage + "\nIncorrect Answers: " + guesses);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font: 30 arial;");
        stack.getChildren().addAll(rect, text);

        stack.maxWidthProperty().bind(root.widthProperty());
        stack.minWidthProperty().bind(root.widthProperty());
        stack.maxHeightProperty().bind(root.heightProperty());
        stack.minHeightProperty().bind(root.heightProperty());
        root.setCenter(stack);
    }
}
