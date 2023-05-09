package br.com.abdiel.Functionalities.Model;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Enum.weekends;
import br.com.abdiel.Functionalities.PortalInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class InformationReturnService extends PortalInformation {

    private Double valorServico;
    private boolean outraBase = false;

    public boolean validateAdditionalTime() {
        Data data = new Data();
        long dataSemana = Data.convertTime("18:00:00").getTime(),
                dataSabado = Data.convertTime("14:00:00").getTime(),
                dataPa = Data.convertTime(getHoraServico() + ":00").getTime();
        return (dataPa >= dataSemana) ||
                (data.checkWeekends(Data.convertTimeDate(getDiaServico()), weekends.SABADO) && dataPa >= dataSabado) ||
                (data.checkWeekends(Data.convertTimeDate(getDiaServico()), weekends.DOMINGO));
    }
}
