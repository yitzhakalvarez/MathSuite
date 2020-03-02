package org.context;

import org.context.structures.User;
import org.ini4j.Ini;
import org.ui.SceneManager;

import java.io.*;
import java.net.URISyntaxException;

public final class SessionContext {

    private SceneManager manager;

    private static SessionContext instance;

    private User user;

    /* preference settings that can be read in, and written out */
    private Ini prefs;

    private boolean remembering;

    public SessionContext()  {
        try {
            prefs = new Ini(new File(SessionContext.class.getResource("/data/config.ini").toURI()));
            remembering = Boolean.parseBoolean(prefs.get("login", "remember"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        instance = new SessionContext();
        //instance.manager = manager;
    }

    public static void setManager(SceneManager manager) {
        instance.manager = manager;
    }

    public static SessionContext get() {
        return instance;
    }

    public static SceneManager getManager() {
        return instance.manager;
    }

    /**
     * Resets the session context
     */
    public void reset() {
        user = null;
        try {
            prefs.put("login", "remember", isRemembering());
            if (!isRemembering()) {
                prefs.put("login", "user", "");
                prefs.put("login", "pass", "");
            }
            prefs.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void autoSetUser(String user) {
        instance.prefs.put("login", "user", user);
    }

    public static void autoSetPass(String pass) {
        instance.prefs.put("login", "pass", pass);
    }

    public static boolean isRemembering() {
        return instance.remembering;
    }

    public static void setRemembering(boolean value) {
        instance.remembering = value;
    }

    public static String getPreference(String section, String key) {
        return instance.prefs.get(section, key);
    }
}
