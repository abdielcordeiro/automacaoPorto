package br.com.abdiel.Actions;

import br.com.abdiel.Enum.weekends;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Managers.ExcelManager;
import br.com.abdiel.Functionalities.Company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class Data {

    private final Company empresa;
    private static String dia;

    public Data() {
        empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();
    }

    public boolean checkMonthValid() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fristDayOfTheMonth());

        Calendar calendarCurrentDay = Calendar.getInstance();
        calendarCurrentDay.setTime(getCurrentDay());
        return calendar.get(Calendar.MONTH) <= calendarCurrentDay.get(Calendar.MONTH);
    }

    public String nextDay() {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            if (ExcelManager.getCellData() == null) {
                c.setTime(fristDayOfTheMonth());
                dia = format.format(c.getTime());
                return format.format(c.getTime());
            } else {
                c.setTime(ExcelManager.getCellData());
                c.add(Calendar.DATE, 1);
                dia = format.format(c.getTime());
                return format.format(c.getTime());
            }
        } catch (Exception pe) {
            LogSystem.setLog("ERROR", pe.getMessage());
            return "Erro ao gerar a proxima da data!!!";
        }
    }

    public Date getCurrentDay() {
        return new java.util.Date();
    }

    public Date getYesterday() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c = Calendar.getInstance();
        c.setTime(getCurrentDay());
        c.add(Calendar.DATE, -1);

        return convertDate(format.format(c.getTime()));
    }

    public static Date convertTime(String hora) {
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(hora);
        } catch (ParseException pe) {
            LogSystem.setLog("ERROR", "Problema para converter hora!!");
            LogSystem.setLog("ERROR", pe.getMessage());
            return null;
        }
    }

    public static Date convertDate(String data) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException pe) {
            LogSystem.setLog("ERROR", "Problema para converter data: " + data);
            LogSystem.setLog("ERROR", pe.getMessage());
            LogSystem.setLog("ERROR", Arrays.toString(pe.getStackTrace()));
            pe.printStackTrace();
            return null;
        }
    }

    public static String convertTimeDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public boolean checkWeekends(String data, weekends finsDeSemana) {
        try {
            Date format = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            switch (finsDeSemana) {
                case DOMINGO:
                    return format.toString().substring(0, 4).contains("Sun");
                case SABADO:
                    return format.toString().substring(0, 4).contains("Sat");
            }
        } catch (ParseException e) {
            LogSystem.setLog("ERROR", e.getMessage());
        }
        return false;
    }

    private Date fristDayOfTheMonth() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, Integer.parseInt(empresa.getConfiguracao().getInfo().getAnoPreenchimento()));
        instance.set(Calendar.MONTH, Integer.parseInt(empresa.getConfiguracao().getInfo().getMesPreenchimento()) - 1);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMinimum(Calendar.DAY_OF_MONTH));

        return convertDate(convertTimeDate(instance.getTime()));
    }

    public boolean checkNextDays() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, Integer.parseInt(empresa.getConfiguracao().getInfo().getAnoPreenchimento()));
        instance.set(Calendar.MONTH, Integer.parseInt(empresa.getConfiguracao().getInfo().getMesPreenchimento()) - 1);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ultimaDiaMes = instance.getTime();

        Date data = ExcelManager.getCellData() == null ? fristDayOfTheMonth() : convertDate(nextDay());

        assert data != null;
        if (data.getTime() < ultimaDiaMes.getTime()) {
            return data.getTime() <= getYesterday().getTime();
        }
        return false;
    }

    public static String getDay() {
        return dia;
    }

}
