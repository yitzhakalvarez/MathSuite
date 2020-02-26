package org.io.db;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionManager {

    private final String host = "";
    private final String user = "";
    private final String pass = "";

    public ConnectionManager() {
        try {
            Connection connection = DriverManager.getConnection(host, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
