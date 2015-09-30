package com.prjvt.intellifitn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vitor on 21/05/2015.
 */
public class Treino {
    private Integer Id;
    private String Descricao;
    private String DataLanc;
    private Objetivo objetivo;
    private List<DiasExercicio> ListaExercDias;

    public Treino(Integer id, String descricao, String dataLanc, Objetivo obj) {
        setId(id);
        setDescricao(descricao);
        setDataLanc(dataLanc);
        setObjetivo(obj);

        ListaExercDias = new ArrayList<DiasExercicio>();
    }

    public Treino() {
        this(0, "", "", new Objetivo());
    }

    public Integer getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getDataLanc() {
        return DataLanc;
    }

    public void setDataLanc(String dataLanc) {
        DataLanc = dataLanc;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public List<DiasExercicio> getListaExercicio() {
        return ListaExercDias;
    }

    public void setListaExercicio(List<DiasExercicio> listaExercicio) {
        ListaExercDias = listaExercicio;
    }
}
