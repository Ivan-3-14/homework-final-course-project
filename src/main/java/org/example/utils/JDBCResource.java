package org.example.utils;

import java.util.ResourceBundle;

public class JDBCResource {
    public static final String DATABASE_RESOURCE = "resource";

    private static final String URL_KEY = "url";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";

    private static final ResourceBundle resource = ResourceBundle.getBundle(DATABASE_RESOURCE);

    private static final String URL = getValue(URL_KEY);
    private static final String USER = getValue(USER_KEY);
    private static final String PASSWORD = getValue(PASSWORD_KEY);

    private static String getValue(String key) {
        return resource.getString(key);
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
