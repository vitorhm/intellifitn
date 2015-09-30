package com.prjvt.intellifitn.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.prjvt.intellifitn.enumerator.EDias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitor on 28/05/2015.
 */
public class DiasExercicio implements Parcelable {
    private EDias Dia;
    private List<ExercicioDetalhe> listaExercicio;

    public DiasExercicio() {
        listaExercicio = new ArrayList<ExercicioDetalhe>();
    }

    public DiasExercicio(Parcel source) {
        Dia = EDias.values()[source.readInt()];
        listaExercicio = new ArrayList<ExercicioDetalhe>();
        source.readList(listaExercicio, Exercicio.class.getClassLoader());
    }

    public EDias getDia() {
        return Dia;
    }

    public void setDia(EDias dia) {
        Dia = dia;
    }

    public List<ExercicioDetalhe> getExercicio() {
        return listaExercicio;
    }

    public void setExercicio(List<ExercicioDetalhe> exercicio) {
        this.listaExercicio = exercicio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Dia.getDia());
        dest.writeList(this.listaExercicio);
    }


    public static final Parcelable.Creator<DiasExercicio> CREATOR = new Parcelable.Creator<DiasExercicio>() {

        @Override
        public DiasExercicio createFromParcel(Parcel source) {
            return new DiasExercicio(source); // RECREATE VENUE GIVEN SOURCE
        }

        @Override
        public DiasExercicio[] newArray(int size) {
            return new DiasExercicio[size]; // CREATING AN ARRAY OF VENUES
        }

    };
}
