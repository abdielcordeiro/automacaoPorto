package br.com.abdiel;

import br.com.abdiel.Actions.Message;
import br.com.abdiel.Services.MainSpreadsheet;
import br.com.abdiel.Services.MainConfiguracoes;

import java.util.Scanner;

public class mainMenu {

    public static void main(String[] args) {
        boolean valida = true;
        while (valida) {
            Scanner entrada = new Scanner(System.in);

            Message.getMenuMessage();
            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    MainSpreadsheet planilha = new MainSpreadsheet();
                    planilha.spreadsheet();
                    break;
                case 2:
                    MainConfiguracoes configuracoes = new MainConfiguracoes();
                    configuracoes.configuracoes();
                    break;
                case 3:
                    valida = false;
                    break;
                default:
                    Message.showMessage("Erro na digitação","\"Não foi digitado uma opção valida. \\nDigite novamente!!!\"");
            }
        }
    }
}
