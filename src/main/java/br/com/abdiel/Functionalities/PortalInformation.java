package br.com.abdiel.Functionalities;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Enum.weekends;
import br.com.abdiel.Functionalities.Model.InformationReturnService;
import br.com.abdiel.Functionalities.Model.InsertSpreadsheet;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter

public class PortalInformation {

    public PortalInformation() {
    }

    private String numeroServico;

    private String nomeEquipamento;

    private String numeroViatura;

    private Date diaServico;

    private String horaServico;
    private String statusServico;
    private String retorno;
    private String obsLaudo;
    private String mensagemPrestador;
    private List<InformationReturnService> informacoesRetorno;
    private boolean conclusao = false, visita = false, isExisteEquipamento = false, isAdicional;

    public InsertSpreadsheet analisaServicos(@NotNull Company empresa, PortalInformation portal) {
        Data data = new Data();
        InsertSpreadsheet spreadsheet = new InsertSpreadsheet();

        for (String txtConclusao : empresa.getMensagens().getConclusao()) {
            if (portal.getMensagemPrestador().toLowerCase(Locale.ROOT).contains(txtConclusao.toLowerCase(Locale.ROOT))) {
                long dataSemana = Objects.requireNonNull(Data.convertTime("18:00:00")).getTime(),
                        dataSabado = Objects.requireNonNull(Data.convertTime("14:00:00")).getTime(),
                        dataPa = Objects.requireNonNull(Data.convertTime(getHoraServico())).getTime();
                setConclusao(true);
                if ((dataPa >= dataSemana) ||
                        (data.checkWeekends(data.convertTimeDate(getDiaServico()), weekends.SABADO) && dataPa >= dataSabado) ||
                        (data.checkWeekends(data.convertTimeDate(getDiaServico()), weekends.DOMINGO))) {
                    setAdicional(true);
                    spreadsheet = buscarServico(empresa);
                    spreadsheet.insertInformationPortal(portal);
                    if (spreadsheet.getNomeEquipamento() == null || spreadsheet.getNomeEquipamento().equals("Sem Equipamento")) {
                        spreadsheet.setValorServico(0.0);
                    }
                    break;
                } else {
                    spreadsheet = buscarServico(empresa);
                    spreadsheet.insertInformationPortal(portal);
                    if (spreadsheet.getNomeEquipamento() == null) {
                        spreadsheet.setValorServico(0.0);
                    }
                    break;
                }
            }
        }
        if (!isConclusao()) {
            for (String txtVisita : empresa.getMensagens().getVisita()) {
                if (portal.getMensagemPrestador().toLowerCase(Locale.ROOT).contains(txtVisita.toLowerCase(Locale.ROOT))) {
                    long dataSemana = Objects.requireNonNull(Data.convertTime("18:00:00")).getTime(),
                            dataSabado = Objects.requireNonNull(Data.convertTime("14:00:00")).getTime(),
                            dataPa = Objects.requireNonNull(Data.convertTime(getHoraServico())).getTime();
                    setVisita(true);
                    if ((dataPa >= dataSemana) ||
                            (data.checkWeekends(data.convertTimeDate(getDiaServico()), weekends.SABADO) && dataPa >= dataSabado) ||
                            (data.checkWeekends(data.convertTimeDate(getDiaServico()), weekends.DOMINGO))) {
                        setAdicional(true);
                        spreadsheet = buscarServico(empresa);
                        spreadsheet.insertInformationPortal(portal);
                        if (spreadsheet.getNomeEquipamento() == null || spreadsheet.getNomeEquipamento().equals("Sem Equipamento")) {
                            spreadsheet.setValorServico(0.0);
                        }
                        break;
                    } else {
                        spreadsheet = buscarServico(empresa);
                        spreadsheet.insertInformationPortal(portal);
                        if (spreadsheet.getNomeEquipamento() == null || spreadsheet.getNomeEquipamento().equals("Sem Equipamento")) {
                            spreadsheet.setValorServico(0.0);
                        }
                        break;
                    }
                }
            }
        }

        if (!isConclusao() && !isVisita()) {
            spreadsheet.setNomeEquipamento("Sem Conclusao/Visita");
            spreadsheet.insertInformationPortal(portal);
            spreadsheet.setValorServico(0.0);
        }

        return spreadsheet;
    }

    /**
     * Metodo utilizado para realizar a busca por modelo dos equipamentos, respeitando os nomes que são apresentado no portal Porto Seguro
     *
     * @param empresa variavel responsavel pela base de dados JSON
     * @return returnar a classe contendo todas as informações encontradas sobre o equipamento, modelo, valor
     */
    private InsertSpreadsheet buscarServico(@NotNull Company empresa) {
        switch (getNomeEquipamento()) {
            case "GELADEIRA SIDEBYSIDE":
            case "GELADEIRA":
            case "REFRIGERADOR MANUTEN":
                return (!empresa.getEquipamentos().buscarGeladeira(this).getNomeEquipamento().equalsIgnoreCase("Sem Modelo"))
                        ? empresa.getEquipamentos().buscarGeladeira(this)
                        : empresa.getEquipamentos().buscarSideBySide(this);
            case "REVERSAO DE FOGAO":
            case "FOGAO":
                return empresa.getEquipamentos().buscarFogao(this);
            case "FREEZER":
                return empresa.getEquipamentos().buscarFreezer(this);
            case "LAVA ROUPA LAVASECA":
            case "LAVA ROUPAS":
            case "LAVA ROUPAS MANUTENC":
                return (!empresa.getEquipamentos().buscarLavaRoupas(this).getNomeEquipamento().equalsIgnoreCase("Sem Modelo"))
                        ? empresa.getEquipamentos().buscarLavaRoupas(this)
                        :empresa.getEquipamentos().buscarLavaSeca(this);
            case "TANQUINHO":
            case "TANQUINHO.":
                return empresa.getEquipamentos().buscarTanquinho(this);
            case "MICROONDAS":
            case "MICROONDAS MANUTENCA":
                return empresa.getEquipamentos().buscarMicroondas(this);
            case "FRIGOBAR":
                return empresa.getEquipamentos().buscarFrigobar(this);
            case "LAVA LOUCAS":
            case "LAVA LOUCAS MANUTENC":
                return empresa.getEquipamentos().buscarLavaLoucas(this);
            case "AR COND (SPLIT)":
                return empresa.getEquipamentos().buscarArCondicionado(this);
            case "PFAZ":
                return empresa.getEquipamentos().buscarPFaz(this);
            case "APOIO OPER BRANCA":
                return empresa.getEquipamentos().buscarApoioBracal(this);
            case "SECADORA DE ROUPAS":
                return empresa.getEquipamentos().buscarSecadora(this);
            case "AQUEC AGUA GAS":
                return empresa.getEquipamentos().buscarAquecedorAgua(this);
            case "DEPURADOR":
            case "DEPURADOR/EXAUSTOR":
                return empresa.getEquipamentos().buscarDepuradorExaustor(this);
            default:
                InsertSpreadsheet spreadsheet = new InsertSpreadsheet();
                spreadsheet.setNomeEquipamento("Sem Equipamento");
                spreadsheet.setValorServico(0.0);
                return spreadsheet;

        }
    }

    public void cleanServicesInfos() {
        this.numeroServico = null;
        this.nomeEquipamento = null;
        this.numeroViatura = null;
        this.horaServico = null;
        this.statusServico = null;
        this.retorno = null;
        this.diaServico = null;
        this.obsLaudo = null;

    }

    @Override
    public String toString() {
        return "\n=================================" +
                "\n*Número Servico: " + numeroServico +
                "\n*Nome do Equipamento: " + nomeEquipamento +
                "\n*Número da viatura: " + numeroViatura +
                "\n*Dia do Serviço: " + diaServico +
                "\n*Hora do Serviço: " + horaServico +
                "\n*Status do Serviço: " + statusServico +
                "\n*Retorno: " + retorno +
                "\n*Observação: " + obsLaudo +
                "\n*Mensagem prestador: " + mensagemPrestador +
                "\n=================================";

    }
}