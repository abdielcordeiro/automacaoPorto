package br.com.abdiel.ConfigProvider;

import br.com.abdiel.Actions.LogSystem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configFileReaderProperties {

    private Properties properties;
    private final String propertyFilePath = "src/main/resources/BaseData/setup.properties";

    public configFileReaderProperties() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            LogSystem.setLog("INFO", "setup.properties não encontrado em: " + propertyFilePath);
            LogSystem.setLog("ERROR", e.getMessage());
        }
    }

    public String getInfo(String info) {
        return properties.getProperty(info);
    }

    public void setInfo(String info, String value) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(propertyFilePath);
            properties.setProperty(info, value);
            properties.store(fileOutputStream, "Autor: Abdiel Cordeiro");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}