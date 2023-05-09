package br.com.abdiel.Managers;

import br.com.abdiel.ConfigProvider.ConfigFileReaderJson;
import br.com.abdiel.ConfigProvider.configFileReaderProperties;

public class FileReaderManager {

    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private static configFileReaderProperties configFileReader;
    private static ConfigFileReaderJson configFileReaderJson;
    private static ExcelManager excelUtils;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public configFileReaderProperties getConfigReader() {
        return (configFileReader == null) ? new configFileReaderProperties() : configFileReader;
    }

    public ConfigFileReaderJson getConfigReaderJson() {
        return (configFileReaderJson == null) ? new ConfigFileReaderJson() : configFileReaderJson;
    }

}