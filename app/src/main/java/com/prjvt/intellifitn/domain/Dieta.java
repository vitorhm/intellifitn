package com.prjvt.intellifitn.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitor on 13/10/2015.
 */
public class Dieta {
    private Integer Id;
    private String Descricao;
    private String DataLanc;
    private List<DietaHorarioLista> ListaDietaHorario;

    public Dieta() {
        ListaDietaHorario = new ArrayList<DietaHorarioLista>();
        this.Id = 0;
        this.Descricao = "";
        this.DataLanc = "";
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
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

    public List<DietaHorarioLista> getListaDietaHorario() {
        return ListaDietaHorario;
    }

    public void setListaDietaHorario(List<DietaHorarioLista> listaDietaHorario) {
        ListaDietaHorario = listaDietaHorario;
    }
}
