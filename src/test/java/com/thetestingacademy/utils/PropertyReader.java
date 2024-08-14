package com.thetestingacademy.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static String readKey(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Amaan\\IdeaProjects\\APIAutomationATB6x\\src\\test\\resources\\data.properties");
            properties.load(fileInputStream);
            return properties.getProperty(key);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);
    }
}
