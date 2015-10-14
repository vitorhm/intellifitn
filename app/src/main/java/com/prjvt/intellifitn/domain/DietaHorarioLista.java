package com.prjvt.intellifitn.domain;

import java.util.List;

/**
 * Created by Vitor on 13/10/2015.
 */
public class DietaHorarioLista {
    private Long Horario;
    private List<DietaHorario> DietaHorarioList;

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
