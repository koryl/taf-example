package io.github.koryl.test.framework.utilities.propertieshandler;

import io.github.koryl.test.framework.utilities.logger.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathHandler {

    private static Properties _properties;

    private static void init() {
        if (_properties == null) {
            _properties = new Properties();
            InputStream configurationFileIS = PathHandler.class.getClassLoader().getResourceAsStream("path.properties");
            try {
                _properties.load(configurationFileIS);
                Log.debug("Properties file was loaded.");
            } catch (IOException e) {
                Log.debug("Exception: " + e.getMessage());
            }
        }
    }

    public static String getPath(String propertyName) {
        init();
        String path;
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        if(osName.contains("Windows")) {
            path = "/Users/" + userName + "/" + _properties.getProperty(propertyName);
            return path;
        } else if (osName.contains("Linux")) {
            path = "/home/" + userName + "/" + _properties.getProperty(propertyName);
            return path;
        }
        return _properties.getProperty(propertyName);
    }
}
