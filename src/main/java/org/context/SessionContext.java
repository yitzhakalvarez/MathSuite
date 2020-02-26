package org.context;

import org.context.structures.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class SessionContext {

    public static User user = null;

    public static final Properties credentials = new Properties();

    static {
        try (InputStream inputStream = SessionContext.class.getResourceAsStream("/data/credentials.txt")) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;) {
                String[] cred = line.split("=");
                credentials.put(cred[0], cred[1]);
            }
            streamReader.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
