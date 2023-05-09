package br.com.abdiel.Managers;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Actions.LogSystem;
import br.com.abdiel.Actions.Message;
import br.com.abdiel.Functionalities.Company;
import br.com.abdiel.Managers.models.InformationReturnSpreadsheet;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@Log4j2
public class ExcelManager {

    private static Company empresa;
    private static FileInputStream ExcelFile;
    private static XSSFSheet ExcelWSheet;
    private static Workbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static void startExcelManager() {
        empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();
        try {
            ZipSecureFile.setMinInflateRatio(0);
            ExcelFile = new FileInputStream(empresa.getConfiguracao().getInfo().getPlanilha());
            ExcelWBook = new XSSFWorkbook(ExcelFile);

            if (ExcelWBook.getSheetIndex(empresa.getConfiguracao().getInfo().getMesPreenchimento()) == -1) {
                XSSFSheet sheet = (XSSFSheet) ExcelWBook.cloneSheet(Integer.parseInt(empresa.getConfiguracao().getInfo().getMesPreenchimento()) - 1);
                ExcelWBook.setSheetName(ExcelWBook.getSheetIndex(sheet.getSheetName()), empresa.getConfiguracao().getInfo().getMesPreenchimento());
                XSSFSheet sheetNova = (XSSFSheet) ExcelWBook.getSheet(empresa.getConfiguracao().getInfo().getMesPreenchimento());
                writeExcelFile();
                clearNewSheet(sheetNova);
            }
            ExcelWSheet = (XSSFSheet) ExcelWBook.getSheet(empresa.getConfiguracao().getInfo().getMesPreenchimento());
        } catch (Exception e) {
            LogSystem.setLog("ERROR", e.getMessage());
        }
    }

    private static void clearNewSheet(XSSFSheet sheet) {
        int count = 1;
        boolean conti = true;

        while (conti) {
            Row = sheet.getRow(count);
            if (!Row.getCell(0).getStringCellValue().toLowerCase(Locale.ROOT).equals("total")
                    && (Row.getCell(0) != null || Cell.getCellType() != CellType.BLANK)) {

                XSSFCell cellOperacao = Row.createCell(0),
                        cellViatura = Row.createCell(1),
                        cellData = Row.createCell(2),
                        cellHora = Row.createCell(3),
                        cellServico = Row.createCell(4),
                        cellValor = Row.createCell(5),
                        cellObs = Row.createCell(6);

                cellOperacao.setBlank();
                cellViatura.setBlank();
                cellData.setBlank();
                cellHora.setBlank();
                cellServico.setBlank();
                cellValor.setBlank();
                cellObs.setBlank();

                writeExcelFile();
                count++;
            } else {
                conti = false;
            }
        }
    }

    public static void writeExcelFile() {
        try {
            FileOutputStream ExcelFile = new FileOutputStream(empresa.getConfiguracao().getInfo().getPlanilha());
            ExcelWBook.write(ExcelFile);
            ExcelFile.close();
        } catch (IOException e) {
            LogSystem.setLog("INFO", "Problema para escrever na planilha!!!!");
            LogSystem.setLog("ERROR", e.getMessage());
        }

    }

    public static void closeExcelFile() {
        try {
            ExcelWBook.close();
            ExcelFile.close();
        } catch (IOException ioe) {
            LogSystem.setLog("INFO", "Dificuldade para encerrar sessão com o Excel!!!!");
            LogSystem.setLog("ERROR", ioe.getMessage());
        }
    }

    /**
     * Método utilizado para geração de novas linhas dentro da planilha excel, respeitando o limitador do cabeçalho e o total na aba inferior
     * metodo já atualizada a fórmula de soma dos valores totais de serviço.
     *
     * @param linha inclusão da quantidade de linha que devem ser adicionadas a planilha
     */
    public static void createRow(int linha) {
        int countLinha = 1, totalLinhas = 1;
        while (true) {
            Row = ExcelWSheet.getRow(countLinha);
            Cell = Row.getCell(0);
            if (Cell != null) {
                totalLinhas++;
                if (Cell.getStringCellValue().equalsIgnoreCase("total")) {
                    for (int i = 0; i <= linha; i++) {
                        ExcelWSheet.shiftRows(countLinha + i, (countLinha + i) + 50, 1);
                        Row = ExcelWSheet.createRow(countLinha + i);

                        CellStyle style = ExcelWBook.createCellStyle();
                        style.setAlignment(HorizontalAlignment.CENTER);
                        style.setVerticalAlignment(VerticalAlignment.CENTER);
                        XSSFCell cellOperacao = Row.createCell(0),
                                cellViatura = Row.createCell(1),
                                cellData = Row.createCell(2),
                                cellHora = Row.createCell(3),
                                cellServico = Row.createCell(4),
                                cellValor = Row.createCell(5),
                                cellObs = Row.createCell(6);

                        cellOperacao.setBlank();
                        cellViatura.setBlank();
                        cellData.setBlank();
                        cellHora.setBlank();
                        cellServico.setBlank();
                        cellValor.setBlank();
                        cellObs.setBlank();
                        Row.setRowStyle(style);
                        writeExcelFile();
                    }
                    break;
                }
            }
            countLinha++;
        }
        while (true) {
            Row = ExcelWSheet.getRow(totalLinhas);
            Cell = Row.getCell(0);
            if (Cell != null) {
                if (Cell.getStringCellValue().equalsIgnoreCase("total")) {
                    Cell = Row.getCell(5);
                    String newForm = Cell.getCellFormula().replace(Cell.getCellFormula().substring(Cell.getCellFormula().lastIndexOf("F") + 1, Cell.getCellFormula().lastIndexOf(")")), String.valueOf(totalLinhas));
                    Cell.setCellFormula(newForm);
                    break;
                }
            }
            totalLinhas++;
        }
    }

    public static int whiteLinesCounter() {
        int contadorLinha = 0, linha = 1;

        while (true) {
            Row = ExcelWSheet.getRow(linha);
            Cell = Row.getCell(0);

            if (Cell == null || Cell.getCellType() == CellType.BLANK) {
                contadorLinha++;
            } else if (Cell.getStringCellValue().equals("TOTAL")) {
                break;
            }
            linha++;
        }
        return contadorLinha;
    }

    public static @Nullable Date getCellData() {
        int numRow = 1;
        while (true) {
            if ((ExcelWSheet.getRow(numRow).getCell(2) == null
                    || (ExcelWSheet.getRow(numRow).getCell(2).getCellType() == CellType.BLANK
            ))) {
                if (numRow == 1) {
                    Message.showMessage("Inicio do mês", "Primeiro dia do mês!!!");
                    return null;
                }
                break;
            } else {
                numRow++;
            }
        }
        return ExcelWSheet.getRow(numRow - 1).getCell(2).getDateCellValue();
    }

    public static void printInfos(String nServico, String nViatura, String data, String hora, String dServico, Double valor, String obs, String retorno) {
        Message.printMessage("\t\tNúmero Serviço: " + nServico +
                "\n\t\tNúmero da Viatura: " + nViatura +
                "\n\t\tData Serviço: " + data +
                "\n\t\tHora da Execução: " + hora +
                "\n\t\tDescrição Serviço: " + dServico +
                "\n\t\tValor Serviço: " + valor +
                "\n\t\tObservação: " + obs +
                "\n\t\tRetorno: " + retorno);
    }

    public static void setRow(String op, String viatura, Date data, String hora, String servico, double valor, String obs, String retorno) {
        try {
            int numRowIni = 1;
            while (true) {
                Row = ExcelWSheet.getRow(numRowIni);
                Cell = Row.getCell(0);

                if (Cell == null || Cell.getCellType() == CellType.BLANK) {

                    CellStyle style = ExcelWBook.createCellStyle();
                    style.setAlignment(HorizontalAlignment.CENTER);
                    style.setVerticalAlignment(VerticalAlignment.CENTER);


                    XSSFCell cellOperacao = Row.createCell(0),
                            cellViatura = Row.createCell(1),
                            cellData = Row.createCell(2),
                            cellHora = Row.createCell(3),
                            cellServico = Row.createCell(4),
                            cellValor = Row.createCell(5),
                            cellObs = Row.createCell(6),
                            cellRet = Row.createCell(7);


                    cellOperacao.setCellValue(op);
                    cellOperacao.setCellStyle(style);

                    cellViatura.setCellValue(viatura);
                    cellViatura.setCellStyle(style);

                    cellData.setCellValue(data);
                    cellData.setCellStyle(style);
                    cellData.setCellType(CellType.NUMERIC);

                    cellHora.setCellValue(hora);
                    cellHora.setCellStyle(style);

                    cellServico.setCellValue(servico);
                    cellServico.setCellStyle(style);

                    cellValor.setCellValue(valor);
                    cellValor.setCellStyle(style);

                    cellObs.setCellValue(obs);
                    cellObs.setCellStyle(style);

                    cellRet.setCellValue(retorno);
                    cellRet.setCellStyle(style);

                    printInfos(op, viatura, Data.convertTimeDate(data), hora, servico, valor, obs, retorno);
                    writeExcelFile();
                    break;
                } else {
                    numRowIni++;
                }
            }
        } catch (Exception e) {
            LogSystem.setLog("INFO", "Problema para inserir a linha no excel!!!");
            LogSystem.setLog("ERROR", e.getMessage());
            e.printStackTrace();
            throw (e);
        }
    }

    /**
     * Função para retornar 1 informação unica da planilha dado o número do serviço é sua linha
     *
     * @param numServico        Número do serviço que deseja buscar na planilha
     * @param linhaRetorno      qual linha da planilha deseja visualizar (0 = Número Serviço, 1 = Prestador, 2 = Data, 3 = Horário, 4 = Equipamento, 5 = Valor, 6 = Observacao )
     * @param reduzirAba        Deve ser iniciado sempre em 0
     * @param linhaPlanilha     Deve se iniciar na primeira linha da planilha com informação (1)
     * @param returnSpreadsheet variavel que vai receber o retorno da busca
     * @return Retorna apenas a linha da coluna desejada.
     */
    public static InformationReturnSpreadsheet getInfo(String numServico, int linhaRetorno, InformationReturnSpreadsheet returnSpreadsheet, int reduzirAba, int linhaPlanilha) {

        if (!((Integer.parseInt(empresa.getConfiguracao().getInfo().getMesPreenchimento()) - reduzirAba) >= 1)) {
            return returnSpreadsheet;
        }

        XSSFSheet sheetNova = (XSSFSheet) ExcelWBook.getSheet(String.valueOf(Integer.parseInt(empresa.getConfiguracao().getInfo().getMesPreenchimento()) - reduzirAba));
        Row = sheetNova.getRow(linhaPlanilha);
        Cell = Row.getCell(0);

        if (Cell == null || Cell.getCellType() == CellType.BLANK || Cell.getStringCellValue().equalsIgnoreCase("total")) {
            getInfo(numServico, linhaRetorno, returnSpreadsheet, reduzirAba + 1, 1);
        } else {
            if (Cell.getStringCellValue().equals(numServico)) {
                switch (linhaRetorno) {
                    case 0:
                        returnSpreadsheet.setCodigoServico(Row.getCell(linhaRetorno).getStringCellValue());
                        return returnSpreadsheet;
                    case 1:
                        returnSpreadsheet.setCodigoPrestador(Row.getCell(linhaRetorno).getStringCellValue());
                        return returnSpreadsheet;
                    case 3:
                        returnSpreadsheet.setHoraServico(Row.getCell(linhaRetorno).getStringCellValue());
                        return returnSpreadsheet;
                    case 4:
                        returnSpreadsheet.setNomeEquipamento(Row.getCell(linhaRetorno).getStringCellValue());
                        return returnSpreadsheet;
                    case 6:
                        returnSpreadsheet.setObservacao(Row.getCell(linhaRetorno).getStringCellValue());
                        return returnSpreadsheet;
                    case 2:
                        returnSpreadsheet.setDataServico(Row.getCell(linhaRetorno).getDateCellValue());
                        return returnSpreadsheet;
                    case 5:
                        returnSpreadsheet.setValorServico(Row.getCell(linhaRetorno).getNumericCellValue());
                        return returnSpreadsheet;
                    default:
                        LogSystem.setLog("WARNNING", "Opção digita para retorno de informação da planilha inexistente!!!");
                        return returnSpreadsheet;
                }
            } else {
                getInfo(numServico, linhaRetorno, returnSpreadsheet, reduzirAba, linhaPlanilha + 1);
            }
        }
        return returnSpreadsheet;
    }

    public static void checkWorksheetRows(int qntLinhas) {
        if (whiteLinesCounter() < qntLinhas)
            createRow(qntLinhas - whiteLinesCounter());
    }
}
