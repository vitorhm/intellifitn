package com.prjvt.intellifitn.domain;

import java.util.List;

/**
 * Created by Vitor on 21/05/2015.
 */
public class ExercicioDetalhe {
    private Integer Id;
    private Exercicio exercicio;
    private String Series;
    private String Rep;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getRep() {
        return Rep;
    }

    public void setRep(String rep) {
        Rep = rep;
    }
}
