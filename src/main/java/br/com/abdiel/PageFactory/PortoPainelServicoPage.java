package br.com.abdiel.PageFactory;

import br.com.abdiel.Actions.Action;
import br.com.abdiel.Actions.Data;
import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.ConfigProvider.elementImage;
import br.com.abdiel.ConfigProvider.visualValidationComponent;
import br.com.abdiel.Functionalities.Model.InformationReturnService;
import br.com.abdiel.Functionalities.PortalInformation;
import br.com.abdiel.Managers.DriverManager;
import br.com.abdiel.Managers.ExcelManager;
import br.com.abdiel.Managers.models.InformationReturnSpreadsheet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.*;


public class PortoPainelServicoPage {

    private static String dia;

    @FindBy(how = How.XPATH, using = "/html/body/form[4]/span/table/tbody/tr[2]/td[1]/select")
    private WebElement listaDeOpcao;
    @FindBy(how = How.ID, using = "dataInicialInputDate")
    private WebElement input_dataInicial;
    @FindBy(how = How.ID, using = "dataFinalInputDate")
    private WebElement input_dataFinal;
    @FindBy(how = How.ID, using = "pesquisar")
    private WebElement bnt_pesquisar;
    @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelDetalheServicoGrupo\"]/table[2]/tbody/tr/td[3]/label/a")
    private WebElement link_imagemProduto;
    @FindBy(how = How.CLASS_NAME, using = "alinhada-esquerda")
    private List<WebElement> txt_comentario;
    @FindBy(how = How.XPATH, using = "//*[@id=\"j_id51_body\"]/div[2]/div")
    private WebElement txt_qntServicos;
    @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelServicoOriginalMoov\"]/table[2]/tbody/tr[2]/td[2]/a")
    private WebElement bnt_codigoServico;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelSemBotoes\"]/table[1]/tbody/tr/td/h3/span"),
            @FindBy(how = How.NAME, using = "VOCÊ NÃO POSSUI AUTORIZAÇÃO PARA VISUALIZAR ESTE SERVIÇO!")
    })
    private WebElement txt_validaOutraBase;

    @FindBy(how = How.ID, using = "formDetalheServico:j_id46")
    private WebElement txt_NumeroServico;

    @FindAll({
            @FindBy(how = How.ID, using = "voltar"),
            @FindBy(how = How.ID, using = "//*[@id=\"formDetalheServico:painelSemBotoes\"]/table[2]/tbody/tr/td/button"),
            @FindBy(how = How.CSS, using = "#formDetalheServico\\:painelSemBotoes > table:nth-child(3) > tbody > tr > td > button")
    })
    private WebElement bnt_voltar;
    @FindBy(how = How.ID, using = "imgLaudo")
    private WebElement bnt_imgLaudo;
    @FindBy(how = How.ID, using = "imgFotos")
    private WebElement bnt_imgFoto;
    @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelServicoOriginalMoov\"]/table[1]/tbody/tr/td/h3")
    private WebElement txt_ServicoOriginal;
    @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelServicoVinculado\"]/table[2]/tbody/tr")
    private List<WebElement> txt_NumerosServicos;
    @FindBy(how = How.XPATH, using = "//*[@id=\"formDetalheServico:painelDetalheServicoGrupo\"]/table[3]/tbody/tr[1]/td[2]/span")
    private WebElement txt_StatusServicoInterno;
    @FindBy(how = How.ID, using = "tabelaLazy")
    private WebElement boardingServicos;

    public int returnQuantityOfService() {
        Action.waitForSeconds(3);
        String resposta = "0";
        try {
            String test = txt_qntServicos.getText().substring(txt_qntServicos.getText().length() - 2);
            int inicio = txt_qntServicos.getText().length() - 1;
            if (test.matches("[+-]?\\d*(\\.\\d+)?"))
                inicio = txt_qntServicos.getText().length() - 2;

            resposta = txt_qntServicos.getText().substring(inicio);
        } catch (NoSuchElementException nsee) {
            LogSystem.setLog("INFO", "Neste dia não havia dias para serem preenchidos");
        }
        return Integer.parseInt(resposta);
    }

    public void selectService(String servico) {
        Action.waitForSeconds(1);
        DriverManager.getDriver().switchTo().frame("centro");
        Select combobox = new Select(listaDeOpcao);
        combobox.selectByVisibleText(servico);
    }

    public String insertData() {
        Action.waitForSeconds(1);

        Data d1 = new Data();
        String diaPreencher = d1.nextDay();

        input_dataInicial.click();
        input_dataInicial.sendKeys(diaPreencher);

        input_dataFinal.click();
        input_dataFinal.sendKeys(diaPreencher);

        dia = d1.nextDay();
        bnt_pesquisar.click();

        return diaPreencher;
    }

    /**
     * @param numLinha Enviar o número da linha que deseja retornar
     * @return IDs das lista ( numOP, nomeService, numViatura, hora, status, retorno)
     */
    public @NotNull PortalInformation returnInformationPortal(int numLinha) {
        Action.waitForSeconds(4);
        PortalInformation portal = new PortalInformation();
        try {
            Action.scrollToElement(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[2]/a")));
            WebElement numOP = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[2]/a"));

            portal.setNumeroServico(numOP.getText());
            portal.setNomeEquipamento(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[3]")).getText());
            portal.setNumeroViatura(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[4]")).getText());
            portal.setHoraServico(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"j_id86:" + (numLinha - 1) + ":cap_horaProgramadaAtendimento\"]")).getText() + ":00");
            portal.setStatusServico(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[9]")).getText());
            portal.setRetorno(DriverManager.getDriver().findElement(By.xpath("//*[@id=\"tabelaLazy\"]/tbody/tr[" + numLinha + "]/td[11]")).getText());
            portal.setDiaServico(Objects.requireNonNull(Data.convertDate(dia)));
            numOP.click();

            validElementPortal(portal);
            portal.setMensagemPrestador(getProviderMessage());
            List<InformationReturnService> returnService = new ArrayList<>();
            if (Action.isVisibleElement(txt_ServicoOriginal, 5)) {
                InformationReturnService r1 = new InformationReturnService();
                InformationReturnSpreadsheet returnSpreadsheet = new InformationReturnSpreadsheet();

                r1.setNumeroServico(bnt_codigoServico.getText());
                bnt_codigoServico.click();


                if (!Action.isVisibleElement(txt_validaOutraBase, 5)) {
                    r1.setDiaServico(ExcelManager.getInfo(r1.getNumeroServico(), 2, returnSpreadsheet, 0, 1).getDataServico());
                    r1.setHoraServico(ExcelManager.getInfo(r1.getNumeroServico(), 3, returnSpreadsheet, 0, 1).getHoraServico());
                    returnService.add(r1);
                    portal.setAdicional(r1.validateAdditionalTime());

                    for (int i = 1; i <= txt_NumerosServicos.size(); i++) {
                        InformationReturnService r = new InformationReturnService();
                        InformationReturnSpreadsheet returnSpreadsheet1 = new InformationReturnSpreadsheet();
                        WebElement servico = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"formDetalheServico:painelServicoVinculado\"]/table[2]/tbody/tr[" + i + "]/td[2]/a"));

                        if (servico.getText().equals(portal.getNumeroServico())) {
                            returnPage();
                            break;
                        } else {
                            r.setNumeroServico(servico.getText());
                            servico.click();

                            if (!portal.getStatusServico().equalsIgnoreCase("cancelado") && !Action.isVisibleElement(txt_validaOutraBase, 5)) {
                                r.setDiaServico(ExcelManager.getInfo(r.getNumeroServico(), 2, returnSpreadsheet1, 0, 1).getDataServico());
                                r.setHoraServico(ExcelManager.getInfo(r.getNumeroServico(), 3, returnSpreadsheet1, 0, 1).getHoraServico());
                                returnService.add(r);
                            } else {
                                r1.setOutraBase(true);
                                returnService.add(r1);
                            }
                            returnPage();
                        }
                    }
                } else {
                    r1.setOutraBase(true);
                    returnService.add(r1);
                    returnPage();
                }
            }

            portal.setInformacoesRetorno(returnService);
        } catch (NoSuchElementException nsee) {
            LogSystem.setLog("ERROR", nsee.getMessage());
        }
        returnPageService(boardingServicos);
        return portal;
    }

    private List<InformationReturnService> returnChildrenOfService(PortalInformation portalInformation) {
        List<InformationReturnService> returnService = new ArrayList<>();
        if (Action.isVisibleElement(txt_ServicoOriginal, 5)) {
            InformationReturnService r1 = new InformationReturnService();
            InformationReturnSpreadsheet returnSpreadsheet = new InformationReturnSpreadsheet();

            r1.setNumeroServico(bnt_codigoServico.getText());
            bnt_codigoServico.click();

            if (!txt_validaOutraBase.isDisplayed()) {
                r1.setDiaServico(ExcelManager.getInfo(r1.getNumeroServico(), 2, returnSpreadsheet, 0, 1).getDataServico());
                r1.setHoraServico(ExcelManager.getInfo(r1.getNumeroServico(), 3, returnSpreadsheet, 0, 1).getHoraServico());
                returnService.add(r1);
                portalInformation.setAdicional(r1.validateAdditionalTime());

                for (int i = 1; i <= txt_NumerosServicos.size(); i++) {
                    InformationReturnService r = new InformationReturnService();
                    InformationReturnSpreadsheet returnSpreadsheet1 = new InformationReturnSpreadsheet();
                    WebElement servico = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"formDetalheServico:painelServicoVinculado\"]/table[2]/tbody/tr[" + i + "]/td[2]/a"));

                    if (servico.getText().equals(portalInformation.getNumeroServico())) {
                        returnPage();
                        break;
                    } else {
                        r.setNumeroServico(servico.getText());
                        servico.click();
                        if (!txt_StatusServicoInterno.getText().equalsIgnoreCase("cancelado") || !Action.isVisibleElement(txt_validaOutraBase, 5)) {
                            r.setDiaServico(ExcelManager.getInfo(r.getNumeroServico(), 2, returnSpreadsheet1, 0, 1).getDataServico());
                            r.setHoraServico(ExcelManager.getInfo(r.getNumeroServico(), 3, returnSpreadsheet1, 0, 1).getHoraServico());
                            returnService.add(r);
                        } else {
                            r1.setOutraBase(true);
                            returnService.add(r1);
                        }
                        returnPage();
                    }
                }
            } else {
                r1.setOutraBase(true);
                returnService.add(r1);
                returnPage();
            }
        }
        return returnService;
    }


    private void validElementPortal(PortalInformation portal) {
        visualValidationComponent visualValidationComponent = new visualValidationComponent();
        if (visualValidationComponent.waitUntilTrue(() -> bnt_imgFoto.isDisplayed())
                .checkpoint(elementImageValidation(bnt_imgFoto), "valida-botoes-foto")
                && visualValidationComponent.waitUntilTrue(() -> bnt_imgLaudo.isDisplayed())
                .checkpoint(elementImageValidation(bnt_imgLaudo), "valida-botoes-laudo")) {
            portal.setObsLaudo("Preenchido");
        } else {
            portal.setObsLaudo("Não Preenchido");
        }
    }

    public void returnPageService(WebElement element) {
        if (!Action.isVisibleElement(element, 5)) {
            Action.scrollToElement(bnt_voltar);
            bnt_voltar.click();
            returnPageService(element);
        }
    }

    private void returnPage() {
        Action.waitForSeconds(4);
        if (Action.isVisibleElement(bnt_voltar, 5))
            bnt_voltar.click();
    }

    @Contract(value = "_ -> new", pure = true)
    private @NotNull elementImage elementImageValidation(WebElement element) {
        return new elementImage(element);
    }

    private @NotNull String getProviderMessage() {
        Action.waitForSeconds(2);

        StringBuilder resposta = new StringBuilder();
        for (WebElement element : txt_comentario) {
            if (element.getText().contains("QRA:")) {
                resposta.append(element.getText());
            }
        }
        return resposta.toString();
    }

}
