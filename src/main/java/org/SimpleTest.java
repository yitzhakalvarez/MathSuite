package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleTest {

    static Connection createConnection() {
        Connection dbConnection = null;

        try {
            // Setup connection to the database
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://cs.newpaltz.edu:3306/se_20f_g06_db?serverTimezone=EST", "se_20s_g06", "uijo4r"
            );
        } catch (SQLException e) {
            System.out.println("Error connecting to server/database!");
        }

        return dbConnection;
    }
    public static void main(String... args) {
        final Connection db = createConnection();
    }
}
