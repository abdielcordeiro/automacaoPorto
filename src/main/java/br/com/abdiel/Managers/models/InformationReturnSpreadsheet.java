package br.com.abdiel.Managers.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InformationReturnSpreadsheet {

    private String codigoServico;
    private String codigoPrestador;
    private Date dataServico;
    private String horaServico;
    private String nomeEquipamento;
    private Double valorServico = 0.0;
    private String observacao;
    private String retorno;
}
