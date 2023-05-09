package br.com.abdiel.PageFactory;

import br.com.abdiel.Actions.Action;
import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.Exceptions.automationException;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Log4j2
public class PortoHomePage {

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    private WebElement bnt_cookie;

    @FindBy(how = How.ID, using = "cpf")
    private WebElement input_cpf;

    @FindBy(how = How.NAME, using = "password")
    private WebElement input_senha;

    @FindBy(how = How.XPATH, using = "//*[@id=\"loginForm\"]/ul[1]/li[4]/input")
    private WebElement bnt_entrar;

    @FindBy(how = How.CLASS_NAME, using = "ico-burger")
    private WebElement bnt_menuLateralEsquerda;

    @FindBy(how = How.XPATH, using = "body > vgn-portal:defineobjects > page:hide-template-menu-items > page:tas-authenticator > div.line-100.menu-bg.clearfix > div > div > div.bg-passos > ul.passos-menu.menu-right > li.perfil-menu > a > div > div")
    private WebElement bnt_menuLateralDireita;
    @FindBy(how = How.XPATH, using = "//*[@id=\"conteudo-perfil\"]/div/ul/li[9]")
    private WebElement bnt_sair;


    public void logar(String login, String senha) {
        Action.waitForSeconds(1);
        if (Action.isVisibleElement(bnt_cookie,10))
            bnt_cookie.click();

        Action.waitForSeconds(1);
        input_cpf.sendKeys(login);
        input_senha.sendKeys(senha);

        Action.waitForSeconds(1);
        bnt_entrar.click();

        try {
            if (bnt_menuLateralEsquerda.isDisplayed())
                LogSystem.setLog("INFO", "Login com sucesso!!!");
        } catch (NoSuchElementException nsee) {
            LogSystem.setLog("ERROR", "Não foi possivel fazer login");
            throw new automationException("Não foi possivel fazer login");
        }
    }

    public void sair(){
        Action.waitForSeconds(1);
        bnt_menuLateralDireita.click();
        bnt_sair.click();

        try{
            if (Action.isVisibleElement(input_cpf,5))
                LogSystem.setLog("INFO","Logout com sucesso");
        }catch (NoSuchElementException nsee){
            LogSystem.setLog("ERROR","Erro ao executar logout");
            throw new automationException("Não foi possivel fazer logout");
        }
    }
}
