package com.prjvt.intellifitn.enumerator;

import android.content.Context;

import com.prjvt.intellifitn.R;

/**
 * Created by vitor on 14/10/2015.
 */
public enum ETipoAlimento {
    LIQUIDO(0), SOLIDO(1);
    private int value;

    private ETipoAlimento(int value) {
        this.value = value;
    }

    public int getTipoAlimento() {
        return this.value;
    }

    public static ETipoAlimento fromInteger(int x) {
        switch (x) {
            case 0:
                return LIQUIDO;
            case 1:
                return SOLIDO;
        }

        return SOLIDO;
    }

    public int getImagem(Context c) {
        switch(this.value) {
            case 0:
                return R.drawable.ic_food_filled;
            case 1:
                return R.drawable.ic_restaurant;
        }

        return R.drawable.ic_restaurant;
    }
}
