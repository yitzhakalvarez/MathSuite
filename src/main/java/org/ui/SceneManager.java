package org.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Author: Jacob Stempin
 *
 * This Manager class provides Scene switching within the JavaFX application.
 *
 * Ex. Login Screen -> Admin logs in -> Scene switches to Admin Dashboard
 *
 * The Scene Manager handles this by loading the FXML files once into a map,
 * then switching between them via the scene's setRoot() method.
 */
public final class SceneManager {

    /*The map of different UI views*/
    private HashMap<String, Parent> views = new HashMap<>();
    /*the current Scene shown by the Application's Stage*/
    private Scene scene;

    private Stage stage;

    public SceneManager(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
        construct();
    }

    /**
     * Loads all views into the views map
     *
     * Called exactly once, upon initialization
     */
    private void construct() {
        /* Add the Login view to the manager */
        loadFXMLView("Login");
        /* Add the Module loader view to the manager */
        loadFXMLView("Module");
    }

    /**
     * Adds a view to our views via an FXMLLoader
     * Only the manager is allowed to load views
     *
     * @param name of the FXML file (without the .fxml extension)
     */
    private void loadFXMLView(String name) {
        try {
            /* Load the file from the marked resources folder */
            views.put(name, FXMLLoader.load(getClass().getResource("/fxml/" + name + ".fxml")));
        } catch (IOException e) {
            System.out.println("Problem loading resource " + name + " \n Make sure it is located within the /resources/fxml directory");
        }
    }

    /**
     * Shows the specified view within the views map
     * @param name the name of the view to be shown
     *
     *        Ex. showView("Login")
     *             will set the root view to the Login Screen
     */
    public void showView(String name) {
        scene.setRoot(views.get(name));
    }
}
