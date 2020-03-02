package org.context.module;

import javafx.scene.Parent;

public abstract class Module {

    public Module() {
    }

    public final void init() {
        System.out.println(getClass().getSimpleName() + " module has started");
    }

    public abstract Parent build();
}
