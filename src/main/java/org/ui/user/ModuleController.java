package org.ui.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.context.SessionContext;
import org.context.module.*;
import org.context.module.Module;
import org.context.module.space.SpaceGame;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.animation.Interpolator.EASE_BOTH;


public class ModuleController {

    @FXML
    private StackPane pane;

    @FXML
    public void initialize() {
        final JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStylesheets().addAll(getClass().getResource(
                "/css/Global.css"
        ).toExternalForm());
        //pane.\\\
        //pane.setSide(Side.RIGHT);
        Tab tab1 = new Tab("Exercises", buildExercises());
        Tab tab2 = new Tab("Lectures", buildLectures());
        Tab tab3 = new Tab("Homework");
        tabPane.getTabs().addAll(tab1,tab2,tab3);
        tabPane.tabMinWidthProperty().bind(tabPane.widthProperty().divide(tabPane.getTabs().size()).subtract(25));
        pane.getChildren().addAll(tabPane);
    }

    private ScrollPane buildExercises() {
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

        final JFXMasonryPane moduleMasonryPane = new JFXMasonryPane();
        moduleMasonryPane.setPrefSize(400, 400);
        moduleMasonryPane.getChildren().addAll(moduleNodes);

        final ScrollPane scrollPane = new ScrollPane(moduleMasonryPane);
        //prevent horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        JFXScrollPane.smoothScrolling(scrollPane);

        scrollPane.setPrefSize(700, 400);
        Platform.runLater(scrollPane::requestLayout);

        return scrollPane;
    }

    /* displays youtube videos, could be replaced by recorded lectures */
    private ScrollPane buildLectures() {
        final ArrayList<Node> videos = new ArrayList<>();
        WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/5juto2ze8Lg"
        );
        webview.setPrefSize(640, 390);

        videos.add(webview);

        final JFXMasonryPane lectureMasonryPane = new JFXMasonryPane();
        lectureMasonryPane.setPrefSize(400, 400);
        lectureMasonryPane.getChildren().addAll(videos);

        final ScrollPane scrollPane = new ScrollPane(lectureMasonryPane);
        //prevent horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        JFXScrollPane.smoothScrolling(scrollPane);

        scrollPane.setPrefSize(700, 400);
        Platform.runLater(scrollPane::requestLayout);

        return scrollPane;
    }
}
