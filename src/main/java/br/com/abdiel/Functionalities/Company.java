package br.com.abdiel.Functionalities;


import br.com.abdiel.Functionalities.Model.Equipment;
import br.com.abdiel.Functionalities.Model.Message;
import br.com.abdiel.Functionalities.Model.Providers;
import br.com.abdiel.Functionalities.Model.Settings;
import br.com.abdiel.Managers.FileReaderManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Company {

    private Settings configuracao;
    private List<Providers> prestadores;
    private Equipment equipamentos;
    private Message mensagens;

    public Company() {}


}
