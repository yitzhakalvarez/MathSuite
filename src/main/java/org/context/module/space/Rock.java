package org.context.module.space;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class Rock {

    private PathTransition pathTransition;
    private Image image;
    private ImageView imageView;
    int x1, x2;
    double statingX, startingY;


    public Rock() {
        pathTransition = new PathTransition();
        image = new Image(getClass().getResourceAsStream("/module_assets/SpaceRock.png"));
        imageView = new ImageView(image);
    }



    public Path createPath() {
        Random r = new Random();
        int random = r.nextInt(1000);
        Path path = new Path();
        x1 = 1500- random;
        x2 = 1500- random;
        path.getElements().add(new MoveTo(x1,-110));
        path.getElements().add(new LineTo(x2, 1100));

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


        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (++SpaceGame.stageDamage < 5) {
                    SpaceGame.currentRock = new Rock();
                    SpaceGame.currentContainer.getChildren().add(SpaceGame.currentRock.getImageView());
                    SpaceGame.currentRock.addTransition(SpaceGame.currentContainer);
                    SpaceGame.root.getChildren().add(SpaceGame.currentContainer);
                } else
                    System.out.println("Game Over.");
            }
        });
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
