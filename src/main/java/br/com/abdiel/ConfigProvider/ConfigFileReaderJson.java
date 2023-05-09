package br.com.abdiel.ConfigProvider;


import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Functionalities.Company;
import com.google.gson.Gson;

import java.io.*;

public class ConfigFileReaderJson {

    private static Gson gson;
    private final String arquivoJson = FileReaderManager.getInstance().getConfigReader().getInfo("infosJson");
    private Company empresa = null;

    public ConfigFileReaderJson() {
    }

    public Company getJson() {
        try {
            File filePath = new File(arquivoJson);
            Reader reader = new FileReader(filePath);
            empresa = getGson().fromJson(reader, Company.class);
        } catch (FileNotFoundException fnfe) {
            LogSystem.setLog("ERROR", fnfe.getMessage());
        }
        return empresa;
    }

    public void writeJson(Company empresa) {
        if (gson != null) {
            String json = gson.toJson(empresa);
            try {
                FileWriter writer = new FileWriter(arquivoJson);
                writer.write(json);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            LogSystem.setLog("WARNING", "Classe GSON não esta inicializada!!!");
    }

    private static Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }
}
