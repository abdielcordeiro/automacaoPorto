package br.com.abdiel.Managers;

import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.Functionalities.Company;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;
    private final static Company empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();

    private static WebDriver createDriver(String tipo) {
        switch (tipo) {
            case "LOCAL":
                driver = createLocalDriver();
                break;
            case "HEADLESS":
                driver = createHeadlessDriver();
                break;
        }
        return driver;
    }

    private static WebDriver createLocalDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(empresa.getConfiguracao().getAcessos().getUrl());

        return driver;
    }

    private static WebDriver createHeadlessDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");

        try {
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(empresa.getConfiguracao().getAcessos().getUrl());

        } catch (SessionNotCreatedException snce) {
            LogSystem.setLog("ERROR", "Necessario atualizar o driver");
            snce.getStackTrace();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null)
            driver = createDriver(FileReaderManager.getInstance().getConfigReader().getInfo("navegador"));
        return driver;
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}