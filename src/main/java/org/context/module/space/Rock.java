package org.context.module.space;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

/**
 * Author:  Alec Lehmphul
 * This class creates the Rock objects for the Space Game module
 */

public class Rock {

    private PathTransition pathTransition;
    private Image image;
    private ImageView imageView;
    double x1, x2;
    double statingX, startingY;


    public Rock() {
        pathTransition = new PathTransition();
        image = new Image(getClass().getResourceAsStream("/module_assets/SpaceRock.png"));
        imageView = new ImageView(image);
    }



    public Path createPath() {
        Random r = new Random();
        int random = r.nextInt((int)SpaceGame.primaryScreenBounds.getMaxX()/2);
        Path path = new Path();
        x1 = SpaceGame.primaryScreenBounds.getMaxX() - random;
        x2 = SpaceGame.primaryScreenBounds.getMaxY() - random;
        path.getElements().add(new MoveTo(x1,SpaceGame.primaryScreenBounds.getMinY()));
        path.getElements().add(new LineTo(x2, SpaceGame.primaryScreenBounds.getMaxY()));

        return path;
    }



    public void addTransition(Group rockContainer) {
        if (SpaceGame.question < 5)
            pathTransition.setDuration(Duration.millis(6500));
        else
            pathTransition.setDuration(Duration.millis(10000));

        pathTransition.setPath(createPath());
        pathTransition.setNode(rockContainer);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

        statingX = rockContainer.getLayoutX();
        startingY = rockContainer.getLayoutY();


        if (SpaceGame.question < 9) {
            pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    SpaceGame.stageDamage++;
                    SpaceGame.damageText.setText("Guesses/Rock Damage Left: " + (4 - (SpaceGame.stageDamage + SpaceGame.guesses)));
                    if ((SpaceGame.stageDamage + SpaceGame.guesses) < 5) {
                        SpaceGame.currentRock = new Rock();
                        SpaceGame.currentContainer.getChildren().add(SpaceGame.currentRock.getImageView());
                        SpaceGame.currentRock.addTransition(SpaceGame.currentContainer);
                        SpaceGame.root.getChildren().add(SpaceGame.currentContainer);
                    } else
                        gameOver(SpaceGame.root, true);
                }
            });
        }
    }

    public static void gameOver(BorderPane root, boolean isGuess) {
        root.getChildren().clear();

        Rectangle rect = new Rectangle(SpaceGame.primaryScreenBounds.getMaxX() - SpaceGame.primaryScreenBounds.getMaxX()/3.0, SpaceGame.primaryScreenBounds.getMaxY() - SpaceGame.primaryScreenBounds.getMaxY()/3.0);
        rect.setFill(Color.DARKBLUE);
        rect.setStroke(Color.HONEYDEW);

        Text text;

        if (isGuess)
            text = new Text("OH NO!\n\nToo many rocks hit the ground.\nPlease try again!");
        else
            text = new Text("OH NO!\n\nYou took too many guesses.\nPlease try again!");

        text.setFill(Color.WHITE);
        text.setStyle("-fx-font: 30 arial;");
        StackPane stack = new StackPane();
        stack.getChildren().addAll(rect, text);

        stack.maxWidthProperty().bind(root.widthProperty());
        stack.minWidthProperty().bind(root.widthProperty());
        stack.maxHeightProperty().bind(root.heightProperty());
        stack.minHeightProperty().bind(root.heightProperty());
        root.setCenter(stack);
    }







    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
