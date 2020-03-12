package org.context.module;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class FractionExercise extends Module {

    @Override
    public Parent build() throws IOException {
        return (Parent) FXMLLoader.load(getClass().getResource("/fxml/FractionExercise.fxml"));
    }
}
