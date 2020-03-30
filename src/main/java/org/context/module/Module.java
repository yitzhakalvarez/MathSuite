package org.context.module;

import javafx.scene.Parent;

import java.io.IOException;

/**
 * Author: Jacob Stempin
 *
 * General Module class with one method that returns a Parent
 */
public abstract class Module {

    public Module() {
    }

    //not to be overridden
    public final void init() {
        System.out.println(getClass().getSimpleName() + " module has started");
    }

    public abstract Parent build() throws IOException;
}
