package br.com.abdiel.Services;

import br.com.abdiel.Actions.Message;
import br.com.abdiel.Enum.equipmentList;
import br.com.abdiel.Functionalities.Company;
import br.com.abdiel.Functionalities.Model.Bonus;
import br.com.abdiel.Functionalities.Model.Providers;
import br.com.abdiel.Functionalities.Model.Access;
import br.com.abdiel.Functionalities.Model.Information;
import br.com.abdiel.Managers.FileReaderManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainConfiguracoes {

    private final Company empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();

    public void configuracoes() {

        boolean continuar = true;
        while (continuar) {
            Scanner scanner = new Scanner(System.in);

            Message.getMenuSettings();
            int opcaoMenu = scanner.nextInt();

            switch (opcaoMenu) {
                case 1:
                    boolean continuarFuncionario = true;
                    while (continuarFuncionario) {
                        Scanner entradaPNum = new Scanner(System.in);
                        Scanner entradaPString = new Scanner(System.in);
                        Message.getMenuProvider();
                        switch (entradaPNum.nextInt()) {
                            case 1:
                                for (int i = 0; i < empresa.getPrestadores().size(); i++) {
                                    Message.printMessage(empresa.getPrestadores().get(i).toString());
                                }
                                break;
                            case 2:
                                Message.showMessage("Editar infomações funcionario", "Para editar um funcionario você precisa digitar o QRA do prestador");
                                Message.printMessage("Digite aqui: ");
                                String qraEditar = entradaPString.next();
                                for (int i = 0; i < empresa.getPrestadores().size(); i++) {
                                    if (empresa.getPrestadores().get(i).getQra().equals(qraEditar)) {
                                        Message.showProviderInformation();
                                        int opEP = entradaPNum.nextInt();
                                        Message.printMessage("Digite algo o que deseja: ");
                                        String valueEditar = entradaPString.nextLine();
                                        empresa.getPrestadores().get(i).editar(opEP, valueEditar);
                                    }
                                }
                                break;
                            case 3:
                                Providers prestadores = new Providers();
                                Message.showMessage("Cadastrar novo funcionario", "Para cadastrar um novo funcionario");
                                Message.printMessage("Digite nome: ");
                                prestadores.setNomePrestador(entradaPString.nextLine());
                                Message.printMessage("Digite codigo da Viatura: ");
                                prestadores.setViatura(entradaPString.nextLine());
                                Message.printMessage("Digite o CPF do funcionario: ");
                                prestadores.setCpf(entradaPString.nextLine());
                                Message.printMessage("Digite o QRA do prestador: ");
                                prestadores.setQra(entradaPString.nextLine());
                                Message.printMessage("Digite o número ou números de telefone");
                                List<String> telefones = new ArrayList<>();

                                do {
                                    Message.printMessage("Digite o telefone: ");
                                    telefones.add(entradaPString.nextLine());
                                    Message.printMessage("Deseja digitar mais um telefone ? ( Sim/Não ): ");
                                } while (!entradaPString.nextLine().toLowerCase(Locale.ROOT).equals("não"));
                                prestadores.setTelefone(telefones);

                                Message.printMessage("Digite as regiões ou a região que ele vai atuar");
                                List<String> regioes = new ArrayList<>();
                                do {
                                    Message.printMessage("Digite a região: ");
                                    regioes.add(entradaPString.nextLine());
                                    Message.printMessage("Deseja digitar mais uma região ? ( Sim/Não ): ");
                                } while (!entradaPString.nextLine().toLowerCase(Locale.ROOT).contains("não"));
                                prestadores.setRegiao(regioes);
                                empresa.getPrestadores().add(prestadores);
                                FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                                break;
                            case 4:
                                Message.printMessage("Digite o QRA do prestador que deseja excluir.");
                                Message.printMessage("Digite aqui: ");
                                for (int i = 0; i < empresa.getPrestadores().size(); i++) {
                                    if (empresa.getPrestadores().get(i).getQra().equals(entradaPString.next())) {
                                        empresa.getPrestadores().remove(i);
                                        break;
                                    }
                                }
                                FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                                break;
                            case 5:
                                continuarFuncionario = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean continuarEquip = true;
                    while (continuarEquip) {
                        Scanner entradaEString = new Scanner(System.in);
                        Scanner entradaENum = new Scanner(System.in);
                        Message.getMenuEquipament();

                        switch (entradaENum.nextInt()) {
                            case 1:
                                Message.showEquipamentList();
                                int equipa = entradaENum.nextInt();
                                    switch (equipa) {
                                        case 0:
                                            break;
                                        case 1:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.GELADEIRA, entradaEString.nextLine().trim());
                                            break;
                                        case 2:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.SIDEBYSIDE, entradaEString.nextLine().trim());
                                            break;
                                        case 3:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.LAVAROUPAS, entradaEString.nextLine().trim());
                                            break;
                                        case 4:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.LAVAESECA, entradaEString.nextLine().trim());
                                            break;
                                        case 5:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.MICROONDAS, entradaEString.nextLine().trim());
                                            break;
                                        case 6:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.FREEZER, entradaEString.nextLine().trim());
                                            break;
                                        case 7:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.FRIGOBAR, entradaEString.nextLine().trim());
                                            break;
                                        case 8:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.ARCONDICIONADO, entradaEString.nextLine().trim());
                                            break;
                                        case 9:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.FOGAO, entradaEString.nextLine().trim());
                                            break;
                                        case 10:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.LAVALOUCAS, entradaEString.nextLine().trim());
                                            break;
                                        case 11:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.TANQUINHO, entradaEString.nextLine().trim());
                                            break;
                                        case 12:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.SECADORA, entradaEString.nextLine().trim());
                                            break;
                                        case 13:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.AQUECEDORAGUA, entradaEString.nextLine().trim());
                                            break;
                                        case 14:
                                            Message.printMessage("Digite o modelo que deseja inserir: ");
                                            empresa.getEquipamentos().adicionar(equipmentList.DEPURADOREXAUSTOR, entradaEString.nextLine().trim());
                                            break;
                                        default:
                                            Message.printMessage("Não foi digitado uma opção valida!!!");
                                    }
                                break;
                            case 2:
                                empresa.getEquipamentos().verificarDuplicidade();
                                break;
                            case 3:
                                Message.printMessage("Digite o modelo do equipamento que deseja remover:");
                                empresa.getEquipamentos().remover(entradaEString.nextLine().trim());
                                break;
                            case 4:
                                continuarEquip = false;
                                break;
                            default:
                                Message.printMessage("Não foi digitado uma opção valida!!!!");
                        }
                    }
                    FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                    break;
                case 3:
                    empresa.getMensagens().menuMessage();
                    FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                    break;
                case 4:
                    boolean continuarProp = true;
                    Scanner entradaStringProp = new Scanner(System.in), entradaNumProp = new Scanner(System.in);
                    while (continuarProp) {
                        Message.showPaddingSettings();
                        switch (entradaNumProp.nextInt()) {
                            case 1:
                                Message.printMessage("Digite o usuário: ");
                                String user = entradaStringProp.nextLine();
                                Message.printMessage("Digite a senha: ");
                                String senha = entradaStringProp.nextLine();
                                Access acessos = empresa.getConfiguracao().getAcessos();
                                acessos.setUser(user);
                                acessos.setSenha(senha);
                                FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                                break;
                            case 2:
                                Message.printMessage("Digite o valor que deve ser acrescentado ao valor de serviço: ");
                                double valor = entradaStringProp.nextDouble();
                                Information i = empresa.getConfiguracao().getInfo();
                                i.setValorAdicional(valor);
                                break;
                            case 3:
                                Message.printMessage("\nDigite o ano que sera preenchido, deve ser alterado sempre que trocar de ano \nDigite aqui: ");
                                String ano = entradaStringProp.next();
                                Information info = empresa.getConfiguracao().getInfo();
                                info.setAnoPreenchimento(ano);
                                FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                                break;
                            case 4:
                                Message.printMessage("\nPara o Bonus você precisa digitar a partir de qual dia vai começar e qual dia vai encerrar ");
                                Message.printMessage("como também a porcentagem do bonus que deve ser aplicado");
                                Message.printMessage("Digite o dia de inicio Ex: (XX/XX/XXXXX): ");
                                String diaIni = entradaStringProp.next();
                                Message.printMessage("Digite o dia final Ex: (XX/XX/XXXXX): ");
                                String diaFim = entradaStringProp.next();
                                Message.printMessage("Digite a porcentagem do bonus Ex:(10... 20... 30...): ");
                                double bonus = entradaStringProp.nextDouble();
                                Bonus b = empresa.getConfiguracao().getBonus();
                                b.setValor(bonus);
                                b.setInicioBonus(diaIni);
                                b.setFimBonus(diaFim);
                                FileReaderManager.getInstance().getConfigReaderJson().writeJson(empresa);
                                break;
                            case 5:
                                continuarProp = false;
                                break;
                            default:
                                Message.printMessage("Opção não cadastrada no sistema!!!");
                        }
                    }
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    Message.printMessage("Foi digitado um número invalido");
            }
        }
    }
}
