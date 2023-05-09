package br.com.abdiel.Actions;

import br.com.abdiel.Enum.direction;
import br.com.abdiel.Managers.DriverManager;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;

import java.time.Duration;

public class Action {

    private static WebDriver driver = DriverManager.getDriver();

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ie) {
            LogSystem.setLog("ERROR", "Mensagem de erro: " + ie.getMessage());
        }
    }

    public static void scroll(int qnt, direction direcao) {
        switch (direcao) {
            case UP:
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(" + qnt + ",0)", "");
                break;
            case DOWN:
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(0," + qnt + ")", "");
                break;
            default:
                LogSystem.setLog("WARNING", "Não é possivel rolar a tela para outra direção alem de UP DOWN");
        }
    }

    public static void scrollToElement(WebElement element) {
        String scrollElementMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0); var elementTop = arguments[0].getBoundingClientRect().top/ window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(scrollElementMiddle, element);
    }

    public static boolean isVisibleElement(WebElement element, int timeWaitInSeconds) {
        try {
            return Awaitility.await()
                    .timeout(Duration.ofSeconds(timeWaitInSeconds))
                    .ignoreException(NoSuchElementException.class)
                    .until(element::isDisplayed, displayed -> displayed);
        } catch (ConditionTimeoutException ignoree) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement e) {
        try {
            return e.isDisplayed();
        } catch (WebDriverException ex) {
            return false;
        }
    }
    public boolean isClickElement(WebElement element) {
        return isDisplayed(element);
    }

}
