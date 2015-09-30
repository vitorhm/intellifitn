package com.prjvt.intellifitn.domain;

/**
 * Created by Vitor on 27/05/2015.
 */
public class Objetivo {
    private int Id;
    private String Descr;

    public Objetivo(int id, String descr) {
        setId(id);
        setDescr(descr);
    }

    public Objetivo() {
        setId(0);
        setDescr("");
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }
}
