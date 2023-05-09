package br.com.abdiel.Functionalities;

import br.com.abdiel.Actions.Action;
import br.com.abdiel.Actions.Data;
import br.com.abdiel.Functionalities.Model.InsertSpreadsheet;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Managers.ExcelManager;
import br.com.abdiel.PageFactory.PortoPainelServicoPage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;

import static br.com.abdiel.Managers.DriverManager.getDriver;


@Getter
@Setter
@Log4j2
public class Spreadsheet {

    private PortoPainelServicoPage portoPainelServicoPage;
    private PortalInformation informationPortal;
    private Company empresa;

    public Spreadsheet() {
        portoPainelServicoPage = PageFactory.initElements(getDriver(), PortoPainelServicoPage.class);
        informationPortal = new PortalInformation();
        empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();
    }

    public void insertWorksheetInformation(int linhaPortal) {
        Action.waitForSeconds(1);
        informationPortal = portoPainelServicoPage.returnInformationPortal(linhaPortal);

        if (informationPortal.getStatusServico().equalsIgnoreCase("cancelado")) {
            informationPortal.cleanServicesInfos();
        } else {
            InsertSpreadsheet spreadsheet = informationPortal.analisaServicos(empresa, informationPortal);
            ExcelManager.setRow(spreadsheet.getCodigoServico(),
                    spreadsheet.getCodigoPrestador(),
                    spreadsheet.getDataServico(),
                    spreadsheet.getHorarioServico(),
                    spreadsheet.getNomeEquipamento(),
                    spreadsheet.getValorServico(),
                    spreadsheet.getObservacao(),
                    spreadsheet.getRetorno());
        }
    }

    public void setEmptyLine(String data) {
        ExcelManager.setRow("0000", "0000", Data.convertDate(data), "00:00:00", "00000", 0.0, "", "");
    }


}
