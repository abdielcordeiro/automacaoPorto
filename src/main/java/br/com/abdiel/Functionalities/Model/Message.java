package br.com.abdiel.Functionalities.Model;

import br.com.abdiel.Enum.serviceType;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Message {

    private List<String> conclusao;
    private List<String> visita;

    public void menuMessage() {
        boolean continuar = true;
        while (continuar) {
            Scanner entradaNum = new Scanner(System.in), entradaString = new Scanner(System.in);

            br.com.abdiel.Actions.Message.showMenuMessage();
            int opcoesInicial = entradaNum.nextInt();

            switch (opcoesInicial) {
                case 1:
                    br.com.abdiel.Actions.Message.getAddMessage();
                    int opcoesAdicionar = entradaNum.nextInt();

                    br.com.abdiel.Actions.Message.printMessage("Digite a mensagem que deseja inserir: ");

                    switch (opcoesAdicionar) {
                        case 1:
                            adicionar(entradaString.nextLine(), serviceType.CONCLUSAO);
                            break;
                        case 2:
                            adicionar(entradaString.nextLine(), serviceType.VISITA);
                            break;
                        default:
                            br.com.abdiel.Actions.Message.printMessage("Opção não existente");
                    }
                    break;
                case 2:
                    br.com.abdiel.Actions.Message.getRemoveMessage();
                    int opcoesRemover = entradaNum.nextInt();

                    br.com.abdiel.Actions.Message.printMessage("Digite a mensagem que quer deletar: ");
                    String valueRemover = entradaString.nextLine();

                    switch (opcoesRemover) {
                        case 1:
                            remover(valueRemover, serviceType.CONCLUSAO);
                            break;
                        case 2:
                            remover(valueRemover, serviceType.VISITA);
                            break;
                        default:
                            br.com.abdiel.Actions.Message.printMessage("Opção não existente");
                    }
                    break;
                case 3:
                    br.com.abdiel.Actions.Message.showMessage("Digite qual das duas listas deseja ver", "\n1- Conclusao \n2- Visita \nDigite:");
                    int optionList = entradaNum.nextInt();
                    switch (optionList) {
                        case 1:
                            listar(serviceType.CONCLUSAO);
                            break;
                        case 2:
                            listar(serviceType.VISITA);
                            break;
                        default:
                            br.com.abdiel.Actions.Message.printMessage("Opção invalida digite Conclusao ou Visita");
                    }
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    br.com.abdiel.Actions.Message.printMessage("Opção não cadastrada!!!");
            }
        }
    }

    private void listar(@NotNull serviceType tipo) {
        switch (tipo) {
            case CONCLUSAO:
                br.com.abdiel.Actions.Message.printMessage("**********************************");
                conclusao.forEach(System.out::println);
                br.com.abdiel.Actions.Message.printMessage("**********************************");
                break;
            case VISITA:
                br.com.abdiel.Actions.Message.printMessage("**********************************");
                visita.forEach(System.out::println);
                br.com.abdiel.Actions.Message.printMessage("**********************************");
                break;
            default:
                br.com.abdiel.Actions.Message.printMessage("Tipo não definido");
        }
    }

    private void adicionar(String value, @NotNull serviceType tipo) {
        switch (tipo) {
            case CONCLUSAO:
                conclusao.add(value);
                break;
            case VISITA:
                visita.add(value);
                break;
            default:
                br.com.abdiel.Actions.Message.printMessage("Tipo não definido");
        }
    }

    private void remover(String value, @NotNull serviceType tipo) {
        switch (tipo) {
            case CONCLUSAO:
                conclusao.remove(value);
                break;
            case VISITA:
                visita.remove(value);
                break;
            default:
                br.com.abdiel.Actions.Message.printMessage("Tipo não definido");
        }
    }

}
