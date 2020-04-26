package org.context.module;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

/**
  *  Author: Alec Lehmphul
  * This class holds the module for the adding fractions video
 *  and will play using the media player once the module is loaded.
 */

public class AddingFractionsVideo extends Module {
    @Override
    public Parent build() throws IOException {
        Media media = new Media(getClass().getResource("/module_assets/Adding Fractions with Different Denominators.mp4").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);

        //  Makes mediaView size fit to full screen
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

        //setting group and scene
        Group group = new Group();
        group.getChildren().add(mediaView);

        StackPane root = new StackPane();
        root.getChildren().add(group);

        return root;
        //stage.setScene(scene);
        //stage.setTitle("Playing video");
        //stage.show();
    }
}
