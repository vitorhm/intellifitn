package com.prjvt.intellifitn.domain;

/**
 * Created by Vitor on 13/10/2015.
 */
public class DietaHorario {
    private Integer Id;
    private Integer IdDieta;
    private String Alimento;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdDieta() {
        return IdDieta;
    }

    public void setIdDieta(Integer idDieta) {
        IdDieta = idDieta;
    }

    public String getAlimento() {
        return Alimento;
    }

    public void setAlimento(String alimento) {
        Alimento = alimento;
    }
}
