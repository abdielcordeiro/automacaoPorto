package br.com.abdiel.Functionalities.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InfoEquipment {

    private double valor;
    private String nome;
    private List<String> modelo;

    public void setModelo(String modelo) {
        this.modelo.add(modelo);
    }
    public void setModelo(List<String> modelo) {
        this.modelo = modelo;
    }
}
