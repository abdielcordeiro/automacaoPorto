package br.com.abdiel.Functionalities.Model;

import br.com.abdiel.Actions.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Providers {

    private String nomePrestador;
    private String viatura;
    private String qra;
    private String cpf;
    private List<String> regiao;
    private List<String> telefone;

    @Override
    public String toString() {
        StringBuilder telefones = new StringBuilder();
        telefone.forEach(telefones::append);
        StringBuilder regioes = new StringBuilder();
        regiao.forEach(regioes::append);
        return "Nome Prestador='" + nomePrestador + '\'' +
                ", Viatura='" + viatura + '\'' +
                ", QRA='" + qra + '\'' +
                ", CPF='" + cpf + '\'' +
                ", Regiões= " + regioes +
                ", Telefone=" + telefones;
    }

    public void editar(int opcao, String value) {
        switch (opcao) {
            case 1:
                nomePrestador = value;
                break;
            case 2:
                cpf = value;
                break;
            case 3:
                regiao.add(value);
                break;
            case 4:
                telefone.add(value);
                break;
            default:
                Message.printMessage("Não existe esta opção digitada");

        }
    }
}
