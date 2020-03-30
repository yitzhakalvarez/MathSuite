package org.ui.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.effects.JFXDepthManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.context.SessionContext;
import org.context.module.*;
import org.context.module.Module;
import org.context.module.space.SpaceGame;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.animation.Interpolator.EASE_BOTH;


public class ModuleController {

    private ScrollPane scrollPane;

    @FXML
    private StackPane pane;

    /* The pane that holds each "module" */
    private JFXMasonryPane masonryPane;

    @FXML
    public void initialize() {
        AtomicInteger count = new AtomicInteger();

        /* the actual module's themselves */
        final ArrayList<Module> modules = new ArrayList<>();

        modules.add(new VisualizingFractions());
        modules.add(new MultiplicationTable());
        modules.add(new FractionExercise());
        modules.add(new SpaceGame());

        /* The module represented as a JavaFX scene node */
        final ArrayList<Node> moduleNodes = new ArrayList<>();

        modules.forEach(module -> {
            final StackPane child = new StackPane();
            child.setPadding(new Insets(10, 5, 0, 10));
            child.setPrefSize(175, 150);
            JFXDepthManager.setDepth(child, 1);

            final StackPane header = new StackPane();
            header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: rgb(30, 30, 30)");
            VBox.setVgrow(header, Priority.ALWAYS);

            final Label version = new Label();
            version.setAlignment(Pos.TOP_LEFT);
            version.setPadding(new Insets(0, 165, 65, 0));

            header.getChildren().addAll(version, new ImageView( new Image(getClass().getResourceAsStream("/images/math.png"), 80, 80, true, false)));

            final StackPane body = new StackPane();
            body.setMinHeight(40);
            body.getChildren().add(new Label(module.getClass().getSimpleName()));
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: #2d6a71;");

            final VBox content = new VBox(header, body);

            final JFXButton play = new JFXButton();
            play.setButtonType(JFXButton.ButtonType.RAISED);
            play.setStyle("-fx-background-radius: 40; -fx-background-color: #3e6689");// + String.format("#%06x", nextInt) + ";");
            play.setPrefSize(40, 40);
            play.setScaleX(0);
            play.setScaleY(0);
            play.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/play.png"), 20, 20, false, false)));
            play.translateYProperty().bind(Bindings.createDoubleBinding(() -> header.getBoundsInParent().getHeight()
                    - play.getHeight() / 2, header.boundsInParentProperty(), play.heightProperty()));

            play.setOnAction(action -> {
                try {
                    SessionContext.getManager().showView(module.build());
                    SessionContext.getManager().setFullScreen(true);
                    module.init();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Problem Loading Module");
                }
            });

            StackPane.setMargin(play, new Insets(0, 12, 0, 0));
            StackPane.setAlignment(play, Pos.TOP_RIGHT);

            final Timeline animation = new Timeline(new KeyFrame(Duration.millis(500),
                    new KeyValue(play.scaleXProperty(), 1, EASE_BOTH),
                    new KeyValue(play.scaleYProperty(), 1, EASE_BOTH)));
            animation.setDelay(Duration.millis(200 * count.get() + 500));
            animation.play();

            child.getChildren().addAll(content, play);
            moduleNodes.add(child);

            count.getAndIncrement();
        });

        masonryPane = new JFXMasonryPane();
        masonryPane.setPrefSize(400, 400);
        masonryPane.getChildren().addAll(moduleNodes);

        scrollPane = new ScrollPane(masonryPane);
        //prevent horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        JFXScrollPane.smoothScrolling(scrollPane);

        scrollPane.setPrefSize(700, 400);
        Platform.runLater(() -> scrollPane.requestLayout());

        pane.getChildren().addAll(scrollPane);
    }
}
