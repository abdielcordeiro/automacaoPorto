package br.com.abdiel.Services;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.Actions.Message;
import br.com.abdiel.Functionalities.Spreadsheet;
import br.com.abdiel.Managers.ExcelManager;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Managers.DriverManager;
import br.com.abdiel.Functionalities.Company;
import br.com.abdiel.Functionalities.Model.Information;
import br.com.abdiel.PageFactory.PortoHomePage;
import br.com.abdiel.PageFactory.PortoPainelServicoPage;
import br.com.abdiel.PageFactory.PortoTelaLogadaPage;
import org.openqa.selenium.support.PageFactory;

import java.util.Scanner;

import static br.com.abdiel.Managers.DriverManager.getDriver;
import static br.com.abdiel.Managers.ExcelManager.closeExcelFile;
import static br.com.abdiel.Managers.ExcelManager.startExcelManager;

public class MainSpreadsheet {

    private PortoHomePage portoHomePage;
    private PortoTelaLogadaPage portoTelaLogadaPage;
    private PortoPainelServicoPage portoPainelServicoPage;
    private final Company empresa;

    public MainSpreadsheet() {
        empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();
    }

    /**
     * Inicializa a pages só quando for começar a realizar a busca no Site da Porto
     */
    private void startPages() {
        portoHomePage = PageFactory.initElements(getDriver(), PortoHomePage.class);
        portoTelaLogadaPage = PageFactory.initElements(getDriver(), PortoTelaLogadaPage.class);
        portoPainelServicoPage = PageFactory.initElements(getDriver(), PortoPainelServicoPage.class);
        startExcelManager();
    }

    public void spreadsheet() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Information info = empresa.getConfiguracao().getInfo();


            Message.getMenuSpreadSheet();
            info.setMesPreenchimento(scanner.next());
            empresa.getConfiguracao().setInfo(info);
            FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);

            Data data = new Data();
            if (data.checkMonthValid()) {
                startPages();
                portoHomePage.logar(
                        empresa.getConfiguracao().getAcessos().getUser(),
                        empresa.getConfiguracao().getAcessos().getSenha());

                portoTelaLogadaPage.acessarConsultaServico();
                portoPainelServicoPage.selectService("Combinada com Cliente");

                while (data.checkNextDays()) {
                    Spreadsheet planilha = new Spreadsheet();
                    Message.printMessage(portoPainelServicoPage.insertData());
                    int qntServico = portoPainelServicoPage.returnQuantityOfService();
                    Message.printMessage("Quantidade Serviços: " + qntServico);

                    if (portoPainelServicoPage.returnQuantityOfService() > 0) {
                        ExcelManager.checkWorksheetRows(portoPainelServicoPage.returnQuantityOfService());
                        for (int i = 1; i <= qntServico; i++) {
                            Message.printMessage("=============================================");
                            Message.printMessage("\tServiço: " + i);
                            Message.printMessage("\tFalta: " + (qntServico - i));
                            planilha.insertWorksheetInformation(i);
                            Message.printMessage("=============================================");
                        }
                        Message.showMessage("Concluido inserção das informações","Inclusão do dia completa com sucesso!! ");
                    } else
                        planilha.setEmptyLine(Data.getDay());
                }

                Message.showMessage("Planilha preenchida com sucesso","Planilha atualizada com sucesso!!!");
                LogSystem.setLog("INFO","Planilha atualizada com sucesso!!!");
                closeExcelFile();
//                portoHomePage.sair();
                DriverManager.closeBrowser();
                break;
            } else {
                Message.showMessage("Mês invalido","Mês selecionado não é valido por ser superior ao mês atual");
            }
        }
    }
}
