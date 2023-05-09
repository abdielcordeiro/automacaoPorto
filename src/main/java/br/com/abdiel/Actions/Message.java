package br.com.abdiel.Actions;

public class Message {

    public static void showMessage(String titule, String msn) {
        String header = "*********************************************************************";
        String t, m;

        int tmTt = (((header.length() - titule.length()) - 5) / 2);
        String esTt = header.substring(0, tmTt).replace("*", " ");
        t = "***" + esTt + titule + esTt + "***";

        if (msn.length() >= header.length()) {
            int tmMs = (((header.length() - (msn.length() / 2)) - 2) / 2) ;
            String esMs = header.substring(0, tmMs).replace("*", " ");
            m = "*" + esMs + msn.substring(0, msn.length() / 2) + esMs + "*" +
                    "\n*" + esMs + msn.substring(msn.length() / 2) + esMs + "*";
        } else {
            int dp = (((header.length() - msn.length()) - 2) /2 );
            String espaco = header.substring(0, dp).replace("*", " ");
            m = "*" + espaco + msn + espaco + "*";
        }

        System.out.println();
        System.out.println(header);
        System.out.println(t);
        System.out.println(m);
        System.out.println(header);
    }

    public static void getMenuMessage() {
        System.out.println();
        System.out.print("\n****************** INICIO DO PROGRAMA ******************" +
                "\n*                                                      *" +
                "\n* O que deseja fazer hoje ?                            *" +
                "\n* Selecione uma das opções abaixo                      *" +
                "\n*                                                      *" +
                "\n********************************************************" +
                "\n*                                                      *" +
                "\n* 1- Preencher Planilha                                *" +
                "\n* 2- Configurações                                     *" +
                "\n* 3- Sair                                              *" +
                "\n*                                                      *" +
                "\n********************************************************" +
                "\nDigite aqui: ");
    }

    public static void getMenuSpreadSheet() {
        System.out.print("\n****************** Preencher Planilha ******************" +
                "\n*                                                      *" +
                "\n* Digite o número da aba referente ao mês desejado.    *" +
                "\n* Obs.: Aba precisa já esta previamente criada.        *" +
                "\n*                                                      *" +
                "\n********************************************************" +
                "\nDigite aqui: ");
    }

    public static void printMessage(String messsage) {
        System.out.println();
        System.out.print(messsage);
    }

    public static void getMenuEquipament() {
        System.out.print("\n***********************************************" +
                "\n*                                             *" +
                "\n*               Equipamentos                  *" +
                "\n*                                             *" +
                "\n***********************************************" +
                "\n* Selecione uma das opções abaixa             *" +
                "\n* 1- Adicionar equipamento                    *" +
                "\n* 2- Verificar duplicidade                    *" +
                "\n* 3- Excluir equipamento                      *" +
                "\n* 4- Voltar                                   *" +
                "\n***********************************************" +
                "\nDigite aqui: "
        );
    }

    public static void showEquipamentList() {
        System.out.print("\nSelecione qual o equipamento vai adicionar: " +
                "\n0 - Sair" +
                "\n1- Geladeira " +
                "\n2- SideBySide " +
                "\n3- Lava Roupas " +
                "\n4- Lava e Seca " +
                "\n5- Microondas " +
                "\n6- Freezer " +
                "\n7- Frigobar " +
                "\n8- Ar Condicionado " +
                "\n9- Fogao " +
                "\n10- Lava Loucas " +
                "\n11- Tanquinho " +
                "\n12- Secadora" +
                "\n13- Aquecedor de Água " +
                "\nDigite aqui: ");
    }

    public static void showPaddingSettings() {
        System.out.print("\n***********************************************" +
                "\n*                                             *" +
                "\n*                 Configurações               *" +
                "\n*                De Preenchimento             *" +
                "\n*                                             *" +
                "\n***********************************************" +
                "\n* Selecione o que deseja alterar              *" +
                "\n* 1- Usuário e Senha                          *" +
                "\n* 2- Valor adicional serviço                  *" +
                "\n* 3- Ano de preenchimento                     *" +
                "\n* 4- Bonus                                    *" +
                "\n* 5- Sair                                     *" +
                "\n***********************************************" +
                "\n* Digite aqui: ");
    }

    public static void showProviderInformation() {

        System.out.print("\n********************************************" +
                "\n* Qual informação deseja alterar ?         *" +
                "\n* 1- Nome                                  *" +
                "\n* 2- CPF                                   *" +
                "\n* 3- Região                                *" +
                "\n* 4- Telefone                              *" +
                "\n********************************************" +
                "\n* Digite aqui: ");
    }

    public static void getMenuProvider() {
        System.out.print("\n***************************************************" +
                "\n*                                                 *" +
                "\n*                  Funcionarios                   *" +
                "\n*                                                 *" +
                "\n***************************************************" +
                "\n* Selecione uma das opções abaixo para continuar! *" +
                "\n* 1- Listar Funcionarios                          *" +
                "\n* 2- Editar funcionario                           *" +
                "\n* 3- Cadastrar novo funcionario                   *" +
                "\n* 4- Excluir funcionario                          *" +
                "\n* 5- Voltar                                       *" +
                "\n***************************************************" +
                "\n* Digite aqui: ");
    }

    public static void getMenuSettings() {
        System.out.print("\n***************************************************" +
                "\n*                                                 *" +
                "\n*                  Configurações                  *" +
                "\n*                                                 *" +
                "\n***************************************************" +
                "\n* Selecione uma das opções abaixo para continuar! *" +
                "\n* 1- Funcionarios                                 *" +
                "\n* 2- Equipamentos                                 *" +
                "\n* 3- Mensagens                                    *" +
                "\n* 4- Configuracoes                                *" +
                "\n* 5- Voltar                                       *" +
                "\n***************************************************" +
                "\n* Digite aqui: ");
    }

    public static void showMenuMessage() {
        System.out.print("\n***********************************************" +
                "\n*                                             *" +
                "\n*                 Mensagens                   *" +
                "\n*                                             *" +
                "\n***********************************************" +
                "\n* Selecione uma das opções abaixa             *" +
                "\n* 1- Adicionar                                *" +
                "\n* 2- Remover                                  *" +
                "\n* 3- Listar                                   *" +
                "\n* 4- Voltar                                   *" +
                "\n***********************************************" +
                "\n* Digite aqui: ");
    }

    public static void getRemoveMessage() {
        System.out.print("\n*****************************************************************" +
                "\n* Selecione se vai remover mensagem de Conclusão ou de Visita   *" +
                "\n* 1- Conclusao                                                  *" +
                "\n* 2- Visita                                                     *" +
                "\n*****************************************************************" +
                "\n* Digite aqui: ");
    }

    public static void getAddMessage() {
        System.out.print("\n*****************************************************************" +
                "\n* Selecione se vai adicionar mensagem de Conclusão ou de Visita *" +
                "\n* 1- Conclusao                                                  *" +
                "\n* 2- Visita                                                     *" +
                "\n*****************************************************************" +
                "\n* Digite aqui: ");
    }
}
