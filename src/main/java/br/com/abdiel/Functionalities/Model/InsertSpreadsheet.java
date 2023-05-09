package br.com.abdiel.Functionalities.Model;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Functionalities.PortalInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class InsertSpreadsheet {

    public InsertSpreadsheet(){}
    private String codigoServico;
    private String codigoPrestador;
    private Date dataServico;
    private String horarioServico;
    private String nomeEquipamento;
    private Double valorServico;
    private String observacao;
    private String retorno;
    private boolean adicional = false;

    @Override
    public String toString() {
        return  "\n===================================================" +
                "\nCodigo Servico: " + codigoServico +
                "\nCodigo viatura: " + codigoPrestador +
                "\nData: " + dataServico +
                "\nHora: " + horarioServico +
                "\nEquipamanto: " + nomeEquipamento +
                "\nValor: " + valorServico +
                "\nObservação: " + observacao +
                "\nRetorno: " + retorno +
                "\n===================================================";
    }

    public void insertInformationPortal(PortalInformation portal){
        setCodigoServico(portal.getNumeroServico());
        setCodigoPrestador(portal.getNumeroViatura());
        setDataServico(portal.getDiaServico());
        setHorarioServico(portal.getHoraServico());
        setObservacao(portal.getObsLaudo());
        setRetorno(portal.getRetorno());
    }
}
