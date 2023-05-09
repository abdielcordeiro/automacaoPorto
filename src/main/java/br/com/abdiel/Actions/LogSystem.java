package br.com.abdiel.Actions;

import br.com.abdiel.Managers.FileReaderManager;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
public class LogSystem {

    private static final String localArquivo = FileReaderManager.getInstance().getConfigReader().getInfo("localLog");

    private static FileWriter getLog() {
        try {
            File diretorio = new File(localArquivo);
            if (diretorio.exists()) {
                return new FileWriter(localArquivo, true);
            } else {
                if (diretorio.createNewFile()) {
                    return new FileWriter(localArquivo, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setLog(String tipo, String mensagem) {
        PrintWriter printWriter = new PrintWriter(Objects.requireNonNull(getLog()));
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String linha = format.format(data) + " " + tipo + ": " + mensagem + ";";

        printWriter.append(linha);
        printWriter.println();
        printWriter.close();
    }
}
