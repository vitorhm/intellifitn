package com.prjvt.intellifitn.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vitor on 03/06/2015.
 */
public class GrupoMuscular implements Parcelable {
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.descricao);
    }
}
