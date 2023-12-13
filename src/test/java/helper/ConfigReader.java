package helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    private static ConfigReader configReader;

    private ConfigReader(){
        BufferedReader reader;
        String propertyFilePath = "src/test/resources/configuration.properties";
        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try{
                properties.load(reader);
                reader.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }catch(FileNotFoundException fne){
            fne.printStackTrace();
        }
    }

    public static ConfigReader getInstance(){
        if(configReader == null){
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getBaseUrl(){
        String baseUrl = properties.getProperty("base_url");
        return propertyValue(baseUrl);
    }

    public String getToken(){
        String token = properties.getProperty("token");
        return propertyValue(token);

    }

    public String getEmail(){
        String email = properties.getProperty("email");
        return propertyValue(email);
    }

    public String getPassword(){
        String password = properties.getProperty("password");
        return propertyValue(password);
    }

    private String propertyValue(String property){
        if(property!= null) return property;
        else throw new RuntimeException((property + " not specified in config"));
    }
}
