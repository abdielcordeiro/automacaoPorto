package br.com.abdiel.Functionalities;

import br.com.abdiel.Functionalities.Model.InformationReturnService;
import br.com.abdiel.Functionalities.Model.InsertSpreadsheet;
import br.com.abdiel.Managers.ExcelManager;
import br.com.abdiel.Managers.models.InformationReturnSpreadsheet;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Warranty {

    public Warranty() {
    }

    public void identifyWarranty(@NotNull PortalInformation information, InsertSpreadsheet spreadsheet, double valorEquipamento, String nomeEquipamento) {
        List<InformationReturnService> retorno = information.getInformacoesRetorno();
        InformationReturnSpreadsheet returnSpreadsheet = new InformationReturnSpreadsheet();
        double valor = 0;

        for (int i = 0; i < information.getInformacoesRetorno().size(); i++) {
            if (!information.getNumeroServico().equals(retorno.get(i).getNumeroServico())) {
                information.getInformacoesRetorno().get(i).setValorServico(ExcelManager.getInfo(retorno.get(i).getNumeroServico(), 5,returnSpreadsheet,0,1).getValorServico());
                valor += information.getInformacoesRetorno().get(i).getValorServico();
            } else {
                valor += 0.0;
            }
        }
        if (valor > 0.0) {
            if (retorno.get(0).isAdicional()) {
                if (valor < valorEquipamento) {
                    spreadsheet.setNomeEquipamento(nomeEquipamento);
                    spreadsheet.setValorServico(valorEquipamento - valor);
                } else {
                    spreadsheet.setNomeEquipamento("Garantia");
                    spreadsheet.setValorServico(0.0);
                }
            } else {
                if (valor < valorEquipamento) {
                    spreadsheet.setNomeEquipamento(nomeEquipamento);
                    spreadsheet.setValorServico(valorEquipamento - valor);
                } else {
                    spreadsheet.setNomeEquipamento("Garantia");
                    spreadsheet.setValorServico(0.0);
                }
            }
        } else {
            spreadsheet.setNomeEquipamento("Sem valor");
            spreadsheet.setValorServico(0.0);
        }

    }
}
