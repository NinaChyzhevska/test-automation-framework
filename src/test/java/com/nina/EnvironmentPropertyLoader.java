package com.nina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public final class EnvironmentPropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);
    private static final String DEFAULT_ENV_PROPS = "env1.properties";
    private static final String ENV_CONFIG_PARAM = "environment.config";
    private static final String PROPERTIES_PATH = "properties/";

    private static final Properties PROPERTIES = loadProperties();

    public static String getProperty(final String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }

    private static Properties loadProperties() {
        ClassLoader loader = EnvironmentPropertyLoader.class.getClassLoader();
        String env = System.getProperty(ENV_CONFIG_PARAM, DEFAULT_ENV_PROPS);
        log.info("The environment is: " + env);
        try (InputStream input = loader.getResourceAsStream(PROPERTIES_PATH + env)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException("Not able to load properties", e);
        }
    }
}
