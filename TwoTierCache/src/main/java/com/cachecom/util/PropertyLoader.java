package com.cachecom.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Sarindu
 * Singleton Property Loader
 */
public class PropertyLoader {
	
    private static PropertyLoader instance = null;
    private Properties properties;


    protected PropertyLoader() throws IOException{

        properties = new Properties();
        properties.load(PropertyLoader.class.getClassLoader().getResourceAsStream("cachecom.properties"));

    }

    public static PropertyLoader getInstance() {
        if(instance == null) {
            try {
                instance = new PropertyLoader();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
