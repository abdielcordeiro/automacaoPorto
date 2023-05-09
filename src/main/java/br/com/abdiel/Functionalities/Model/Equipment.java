package br.com.abdiel.Functionalities.Model;

import br.com.abdiel.Actions.Data;
import br.com.abdiel.Actions.Message;
import br.com.abdiel.Enum.equipmentList;
import br.com.abdiel.Functionalities.Warranty;
import br.com.abdiel.Functionalities.PortalInformation;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Functionalities.Company;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class Equipment {

    private InfoEquipment garantia, visita, apoioBracal, lavaSeca, sidebyside, geladeira,
            lavaRoupas, frigobar, fogao, arCondicionado, microondas, freezer, lavaLoucas,
            tanquinho, secadora, aquecedor, depuradorExaustor;

    private InsertSpreadsheet spreadsheet;

    public Equipment() {
        spreadsheet = new InsertSpreadsheet();
    }

    public InsertSpreadsheet buscarFreezer(PortalInformation portal) {
        for (int i = 0; i < getFreezer().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getFreezer().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getFreezer().getValor()), (getFreezer().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getFreezer().getValor(), getFreezer().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getFreezer().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getFreezer().getValor()));
                        } else {
                            getSpreadsheet().setValorServico(getFreezer().getValor());
                            getSpreadsheet().setNomeEquipamento(getFreezer().getNome());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarLavaSeca(PortalInformation portal) {
        for (int i = 0; i < getLavaSeca().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getLavaSeca().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getLavaSeca().getValor()), (getLavaSeca().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getLavaSeca().getValor(), getLavaSeca().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getLavaSeca().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getLavaSeca().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getLavaSeca().getNome());
                            getSpreadsheet().setValorServico(getLavaSeca().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarLavaLoucas(PortalInformation portal) {
        for (int i = 0; i < getLavaLoucas().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getLavaLoucas().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getLavaLoucas().getValor()), (getLavaLoucas().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getLavaLoucas().getValor(), getLavaLoucas().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getLavaLoucas().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getLavaLoucas().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getLavaLoucas().getNome());
                            getSpreadsheet().setValorServico(getLavaLoucas().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarFogao(PortalInformation portal) {
        for (int i = 0; i < fogao.getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase(Locale.ROOT).contains(fogao.getModelo().get(i).toLowerCase(Locale.ROOT))) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, spreadsheet, somarAdicional(fogao.getValor()), (fogao.getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, spreadsheet, fogao.getValor(), fogao.getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            spreadsheet.setNomeEquipamento(this.visita.getNome() + " Adc");
                            spreadsheet.setValorServico(somarAdicional(this.visita.getValor()));
                            portal.setExisteEquipamento(true);
                        } else {
                            spreadsheet.setNomeEquipamento(this.visita.getNome());
                            spreadsheet.setValorServico(this.visita.getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            spreadsheet.setNomeEquipamento(fogao.getNome() + " Adc");
                            spreadsheet.setValorServico(somarAdicional(fogao.getValor()));
                            portal.setExisteEquipamento(true);
                        } else {
                            spreadsheet.setNomeEquipamento(fogao.getNome());
                            spreadsheet.setValorServico(fogao.getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                spreadsheet.setNomeEquipamento("Sem Modelo");
                spreadsheet.setValorServico(0.0);
            }
        }
        return spreadsheet;
    }

    public InsertSpreadsheet buscarLavaRoupas(PortalInformation portal) {
        for (int i = 0; i < lavaRoupas.getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(lavaRoupas.getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, spreadsheet, somarAdicional(lavaRoupas.getValor()), (lavaRoupas.getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, spreadsheet, lavaRoupas.getValor(), lavaRoupas.getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            spreadsheet.setNomeEquipamento(this.visita.getNome() + " Adc");
                            spreadsheet.setValorServico(somarAdicional(this.visita.getValor()));
                        } else {
                            spreadsheet.setValorServico(this.visita.getValor());
                            spreadsheet.setNomeEquipamento(this.visita.getNome());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            spreadsheet.setNomeEquipamento(lavaRoupas.getNome() + " Adc");
                            spreadsheet.setValorServico(somarAdicional(lavaRoupas.getValor()));
                        } else {
                            spreadsheet.setNomeEquipamento(lavaRoupas.getNome());
                            spreadsheet.setValorServico(lavaRoupas.getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                spreadsheet.setNomeEquipamento("Sem Modelo");
                spreadsheet.setValorServico(0.0);
            }
        }
        return spreadsheet;
    }

    public InsertSpreadsheet buscarTanquinho(PortalInformation portal) {
        for (int i = 0; i < getTanquinho().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getTanquinho().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getTanquinho().getValor()), (getTanquinho().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getTanquinho().getValor(), getTanquinho().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getTanquinho().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getTanquinho().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getTanquinho().getNome());
                            getSpreadsheet().setValorServico(getTanquinho().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarGeladeira(PortalInformation portal) {
        for (int i = 0; i < getGeladeira().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getGeladeira().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getGeladeira().getValor()), (getGeladeira().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getGeladeira().getValor(), getGeladeira().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getGeladeira().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getGeladeira().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getGeladeira().getNome());
                            getSpreadsheet().setValorServico(getGeladeira().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarSideBySide(PortalInformation portal) {
        for (int i = 0; i < getSidebyside().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getSidebyside().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getSidebyside().getValor()), (getSidebyside().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getSidebyside().getValor(), getSidebyside().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getSidebyside().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getSidebyside().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getSidebyside().getNome());
                            getSpreadsheet().setValorServico(getSidebyside().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarFrigobar(PortalInformation portal) {
        for (int i = 0; i < getFrigobar().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getFrigobar().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getFrigobar().getValor()), (getFrigobar().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getFrigobar().getValor(), getFrigobar().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getFrigobar().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getFrigobar().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getFrigobar().getNome());
                            getSpreadsheet().setValorServico(getFrigobar().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarArCondicionado(PortalInformation portal) {
        for (int i = 0; i < getArCondicionado().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getArCondicionado().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getArCondicionado().getValor()), (getArCondicionado().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getArCondicionado().getValor(), getArCondicionado().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getArCondicionado().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getArCondicionado().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getArCondicionado().getNome());
                            getSpreadsheet().setValorServico(getArCondicionado().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarMicroondas(PortalInformation portal) {
        for (int i = 0; i < getMicroondas().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getMicroondas().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getMicroondas().getValor()), (getMicroondas().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getMicroondas().getValor(), getMicroondas().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getMicroondas().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getMicroondas().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getMicroondas().getNome());
                            getSpreadsheet().setValorServico(getMicroondas().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarSecadora(PortalInformation portal) {
        for (int i = 0; i < getSecadora().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getSecadora().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getSecadora().getValor()), (getSecadora().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getSecadora().getValor(), getSecadora().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getSecadora().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getSecadora().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getSecadora().getNome());
                            getSpreadsheet().setValorServico(getSecadora().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarAquecedorAgua(PortalInformation portal) {
        for (int i = 0; i < getAquecedor().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getAquecedor().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getAquecedor().getValor()), (getAquecedor().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getAquecedor().getValor(), getAquecedor().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getAquecedor().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getAquecedor().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getAquecedor().getNome());
                            getSpreadsheet().setValorServico(getAquecedor().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
                break;
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }

        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarDepuradorExaustor(PortalInformation portal) {
        for (int i = 0; i < getDepuradorExaustor().getModelo().size(); i++) {
            if (portal.getMensagemPrestador().toLowerCase().contains(getDepuradorExaustor().getModelo().get(i).toLowerCase())) {
                if (portal.getRetorno().equalsIgnoreCase("sim") && !portal.getInformacoesRetorno().get(0).isOutraBase()) {
                    Warranty warranty = new Warranty();
                    if (portal.isAdicional()) {
                        warranty.identifyWarranty(portal, getSpreadsheet(), somarAdicional(getDepuradorExaustor().getValor()), (getDepuradorExaustor().getNome() + " Adc"));
                    } else {
                        warranty.identifyWarranty(portal, getSpreadsheet(), getDepuradorExaustor().getValor(), getDepuradorExaustor().getNome());
                    }
                } else {
                    if (portal.isVisita()) {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getVisita().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getVisita().getNome());
                            getSpreadsheet().setValorServico(getVisita().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    } else {
                        if (portal.isAdicional()) {
                            getSpreadsheet().setNomeEquipamento(getDepuradorExaustor().getNome() + " Adc");
                            getSpreadsheet().setValorServico(somarAdicional(getDepuradorExaustor().getValor()));
                        } else {
                            getSpreadsheet().setNomeEquipamento(getDepuradorExaustor().getNome());
                            getSpreadsheet().setValorServico(getDepuradorExaustor().getValor());
                        }
                        portal.setExisteEquipamento(true);
                    }
                }
            } else {
                getSpreadsheet().setNomeEquipamento("Sem Modelo");
                getSpreadsheet().setValorServico(0.0);
            }
        }
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarApoioBracal(PortalInformation portal) {
        getSpreadsheet().setNomeEquipamento(getApoioBracal().getNome());
        getSpreadsheet().setValorServico(getApoioBracal().getValor());
        portal.setExisteEquipamento(true);
        return getSpreadsheet();
    }

    public InsertSpreadsheet buscarPFaz(PortalInformation portal) {
        buscarArCondicionado(portal);
        buscarFogao(portal);
        buscarFrigobar(portal);
        buscarLavaSeca(portal);
        buscarMicroondas(portal);
        buscarGeladeira(portal);
        buscarLavaRoupas(portal);
        buscarSideBySide(portal);
        buscarFreezer(portal);
        buscarLavaLoucas(portal);
        buscarTanquinho(portal);
        buscarSecadora(portal);
        buscarAquecedorAgua(portal);
        buscarDepuradorExaustor(portal);
        return getSpreadsheet();
    }

    public void remover(String value) {
        for (List<String> listE : montarLista()) {
            for (int i = 0; i <= listE.size() - 1; i++) {
                for (int a = 0; a <= listE.size() - 1; a++) {
                    if (listE.get(a).equals(value)) {
                        listE.remove(a);
                        break;
                    }
                }
            }
            salvar(equipmentList.valueOf(listE.get(listE.size() - 1)), listE.subList(0, listE.size() - 1));
        }


    }

    public void adicionar(@NotNull equipmentList listEquipamento, String value) {
        switch (listEquipamento) {
            case GELADEIRA:
                geladeira.setModelo(value.toLowerCase());
                break;
            case SIDEBYSIDE:
                sidebyside.setModelo(value.toLowerCase());
                break;
            case FOGAO:
                fogao.setModelo(value.toLowerCase());
                break;
            case LAVAROUPAS:
                lavaRoupas.setModelo(value.toLowerCase());
                break;
            case LAVAESECA:
                lavaSeca.setModelo(value.toLowerCase());
                break;
            case MICROONDAS:
                microondas.setModelo(value.toLowerCase());
                break;
            case FRIGOBAR:
                frigobar.setModelo(value.toLowerCase());
            case FREEZER:
                freezer.setModelo(value.toLowerCase());
                break;
            case ARCONDICIONADO:
                arCondicionado.setModelo(value.toLowerCase());
                break;
            case LAVALOUCAS:
                lavaLoucas.setModelo(value.toLowerCase());
                break;
            case TANQUINHO:
                tanquinho.setModelo(value.toLowerCase());
                break;
            case SECADORA:
                secadora.setModelo(value.toLowerCase());
                break;
            case AQUECEDORAGUA:
                aquecedor.setModelo(value.toLowerCase());
                break;
            case DEPURADOREXAUSTOR:
                depuradorExaustor.setModelo(value.toLowerCase());
                break;
            default:
                Message.printMessage("Opção não cadastrada");
        }
        if (verificarDuplicidade())
            Message.printMessage("Modelo: " + value + " Já cadastrado!!!");
    }

    private @NotNull List<List<String>> montarLista() {
        List<List<String>> list = new ArrayList<>();

        List<String> strings = fogao.getModelo();
        strings.add(String.valueOf(equipmentList.FOGAO));
        list.add(strings);

        List<String> strings1 = geladeira.getModelo();
        strings1.add(String.valueOf(equipmentList.GELADEIRA));
        list.add(strings1);

        List<String> strings2 = microondas.getModelo();
        strings2.add(String.valueOf(equipmentList.MICROONDAS));
        list.add(strings2);

        List<String> strings3 = freezer.getModelo();
        strings3.add(String.valueOf(equipmentList.FREEZER));
        list.add(strings3);

        List<String> strings4 = frigobar.getModelo();
        strings4.add(String.valueOf(equipmentList.FRIGOBAR));
        list.add(strings4);

        List<String> strings5 = sidebyside.getModelo();
        strings5.add(String.valueOf(equipmentList.SIDEBYSIDE));
        list.add(strings5);

        List<String> strings6 = lavaRoupas.getModelo();
        strings6.add(String.valueOf(equipmentList.LAVAROUPAS));
        list.add(strings6);

        List<String> strings7 = lavaSeca.getModelo();
        strings7.add(String.valueOf(equipmentList.LAVAESECA));
        list.add(strings7);

        List<String> strings8 = arCondicionado.getModelo();
        strings8.add(String.valueOf(equipmentList.ARCONDICIONADO));
        list.add(strings8);

        List<String> strings9 = lavaLoucas.getModelo();
        strings9.add(String.valueOf(equipmentList.LAVALOUCAS));
        list.add(strings9);

        List<String> strings10 = tanquinho.getModelo();
        strings10.add(String.valueOf(equipmentList.TANQUINHO));
        list.add(strings10);

        List<String> strings11 = secadora.getModelo();
        strings11.add(String.valueOf(equipmentList.SECADORA));
        list.add(strings11);

        List<String> strings12 = aquecedor.getModelo();
        strings12.add(String.valueOf(equipmentList.AQUECEDORAGUA));
        list.add(strings12);

        List<String> strings13 = depuradorExaustor.getModelo();
        strings13.add(String.valueOf(equipmentList.DEPURADOREXAUSTOR));
        list.add(strings13);

        return list;
    }

    public boolean verificarDuplicidade() {
        boolean r = false;
        for (List<String> listE : montarLista()) {
            for (int i = 0; i <= listE.size() - 1; i++) {
                int excluir = 0;
                for (int a = 0; a <= listE.size() - 1; a++) {
                    if (listE.get(a).equals(listE.get(i))) {
                        excluir++;
                    }
                    if (excluir == 2) {
                        listE.remove(a);
                        r = true;
                        break;
                    }
                }
            }
            salvar(equipmentList.valueOf(listE.get(listE.size() - 1)), listE.subList(0, listE.size() - 1));
        }
        return r;
    }

    private void salvar(@NotNull equipmentList listEquipamento, List<String> listEquipa) {
        switch (listEquipamento) {
            case LAVAESECA:
                lavaSeca.setModelo(listEquipa);
                break;
            case GELADEIRA:
                geladeira.setModelo(listEquipa);
                break;
            case FOGAO:
                fogao.setModelo(listEquipa);
                break;
            case FREEZER:
                freezer.setModelo(listEquipa);
                break;
            case FRIGOBAR:
                frigobar.setModelo(listEquipa);
                break;
            case ARCONDICIONADO:
                arCondicionado.setModelo(listEquipa);
                break;
            case LAVAROUPAS:
                lavaRoupas.setModelo(listEquipa);
                break;
            case MICROONDAS:
                microondas.setModelo(listEquipa);
                break;
            case SIDEBYSIDE:
                sidebyside.setModelo(listEquipa);
                break;
            case LAVALOUCAS:
                lavaLoucas.setModelo(listEquipa);
                break;
            case TANQUINHO:
                tanquinho.setModelo(listEquipa);
                break;
            case SECADORA:
                secadora.setModelo(listEquipa);
                break;
            case AQUECEDORAGUA:
                aquecedor.setModelo(listEquipa);
                break;
            case DEPURADOREXAUSTOR:
                depuradorExaustor.setModelo(listEquipa);
                break;
            default:
                Message.printMessage("Equipamento ainda não cadastrado");
        }
    }

    private double somarAdicional(double valor) {
        Data data = new Data();
        Company empresa = FileReaderManager.getInstance().getConfigReaderJson().getJson();
        double bonus = empresa.getConfiguracao().getBonus().getValor();
        if (Objects.requireNonNull(Data.convertDate(empresa.getConfiguracao().getBonus().getInicioBonus())).getTime() >= data.getCurrentDay().getTime()
                && data.getCurrentDay().getTime() <= Objects.requireNonNull(Data.convertDate(empresa.getConfiguracao().getBonus().getFimBonus())).getTime()) {
            return valor + (valor * (bonus / 100));
        } else
            return (valor + empresa.getConfiguracao().getInfo().getValorAdicional());


    }

}
