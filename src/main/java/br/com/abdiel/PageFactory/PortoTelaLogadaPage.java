package br.com.abdiel.PageFactory;

import br.com.abdiel.Actions.Action;
import br.com.abdiel.Managers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PortoTelaLogadaPage {


    @FindBy(how = How.CLASS_NAME, using = "ico-burger")
    private WebElement bnt_menuLateral;

    @FindBy(how = How.CLASS_NAME, using = "list-seta")
    private List<WebElement> bnt_opcoesServico;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Consultar Serviços")
    private WebElement bnt_consultarServico;


    public void acessarConsultaServico() {
        Action.waitForSeconds(2);
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", bnt_menuLateral);

        Action.waitForSeconds(2);

        for (int i = 0; i <= bnt_opcoesServico.size() - 1; i++) {
            if (bnt_opcoesServico.get(i).getText().equals("Serviços e Frota")) {
                bnt_opcoesServico.get(i).click();
                if (bnt_consultarServico.getText().equals("Consultar Serviços")) {
                    bnt_consultarServico.click();
                }
            }
        }
    }
}
