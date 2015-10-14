package com.prjvt.intellifitn.domain;

import com.prjvt.intellifitn.enumerator.ETipoAlimento;

/**
 * Created by Vitor on 13/10/2015.
 */
public class DietaHorario {
    private Integer Id;
    private Integer IdDieta;
    private String Alimento;
    private ETipoAlimento TipoAlimento;

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

    public ETipoAlimento getTipoAlimento() {
        return TipoAlimento;
    }

    public void setTipoAlimento(ETipoAlimento tipoAlimento) {
        TipoAlimento = tipoAlimento;
    }
}
