package com.prjvt.intellifitn.domain;


import android.os.Parcel;
import android.os.Parcelable;

import com.prjvt.intellifitn.enumerator.EGrupoMuscular;


/**
 * Created by Vitor on 21/05/2015.
 */
public class Exercicio {
    private int Id;
    private String Nome;
    private String Descricao;
    private String Imagem;
    private EGrupoMuscular GrupoMuscular;

    public Exercicio() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }

    public EGrupoMuscular getGrupoMuscular() {
        return GrupoMuscular;
    }

    public void setGrupoMuscular(EGrupoMuscular grupoMuscular) {
        GrupoMuscular = grupoMuscular;
    }
}
