package com.prjvt.intellifitn.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitor on 13/10/2015.
 */
public class DietaHorarioLista {
    private Long Horario;
    private List<DietaHorario> DietaHorarioList;

    public DietaHorarioLista() {
        DietaHorarioList = new ArrayList<DietaHorario>();
    }

    public Long getHorario() {
        return Horario;
    }

    public void setHorario(Long horario) {
        Horario = horario;
    }

    public List<DietaHorario> getDietaHorarioList() {
        return DietaHorarioList;
    }

    public void setDietaHorarioList(List<DietaHorario> dietaHorarioList) {
        DietaHorarioList = dietaHorarioList;
    }
}
