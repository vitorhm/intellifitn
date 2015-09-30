package com.prjvt.intellifitn.enumerator;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.prjvt.intellifitn.R;

/**
 * Created by vitor on 21/09/2015.
 */
public enum EGrupoMuscular {
    BICEPS(0), TRICEPS(1), TRAPEZIUS(2), SHOULDER(3), BACK(4), CHEST(5);
    private int value;

    private EGrupoMuscular(int value) {
        this.value = value;
    }

    public int getGrupo() {
        return this.value;
    }

    public static EGrupoMuscular fromInteger(int x) {
        switch (x) {
            case 0:
                return BICEPS;
            case 1:
                return TRICEPS;
            case 2:
                return TRAPEZIUS;
            case 3:
                return SHOULDER;
            case 4:
                return BACK;
            case 5:
                return CHEST;
        }

        return BICEPS;
    }

    public int getImagem(Context c) {
        switch (this.value) {
            case 0:
                return R.drawable.ic_arm;
            case 1:
                return R.drawable.ic_arm;
            case 5:
                return R.drawable.ic_chest;
        }

        return R.drawable.ic_arm;
    }
}
