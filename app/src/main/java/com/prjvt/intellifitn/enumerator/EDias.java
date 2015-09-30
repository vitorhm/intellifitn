package com.prjvt.intellifitn.enumerator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.prjvt.intellifitn.R;

import java.util.Calendar;

/**
 * Created by Vitor on 01/06/2015.
 */
public enum EDias implements Parcelable {
    SEGUNDA(0), TERCA(1), QUARTA(2), QUINTA(3), SEXTA(4), SABADO(5), DOMINGO(6);
    private int value;

    private EDias(int value) {
        this.value = value;
    }

    private EDias(Parcel source) {
        this.value = source.readInt();
    }

    public int getDia() {
        return this.value;
    }

    public static EDias fromInteger(int x) {
        switch(x) {
            case 0:
                return SEGUNDA;
            case 1:
                return TERCA;
            case 2:
                return QUARTA;
            case 3:
                return QUINTA;
            case 4:
                return SEXTA;
            case 5:
                return SABADO;
            case 6:
                return DOMINGO;
        }
        return null;
    }

    public static EDias fromDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                return SEGUNDA;
            case Calendar.TUESDAY:
                return TERCA;
            case Calendar.WEDNESDAY:
                return QUARTA;
            case Calendar.THURSDAY:
                return QUINTA;
            case Calendar.FRIDAY:
                return SEXTA;
            case Calendar.SATURDAY:
                return SABADO;
            case Calendar.SUNDAY:
                return DOMINGO;
        }

        return SEGUNDA;
    }

    public static EDias fromString(String x, Context c) {
        if (x.equals(c.getString(R.string.day_monday)))
            return SEGUNDA;
        else
            if (x.equals(c.getString(R.string.day_tuesday)))
                return TERCA;
            else
                if (x.equals(c.getString(R.string.day_wednesday)))
                    return QUARTA;
                else
                    if (x.equals(c.getString(R.string.day_thursday)))
                        return QUINTA;
                    else
                        if (x.equals(c.getString(R.string.day_friday)))
                            return SEXTA;
                        else
                            if (x.equals(c.getString(R.string.day_saturday)))
                                return SABADO;
                            else
                                if (x.equals(c.getString(R.string.day_sunday)))
                                    return DOMINGO;
                                else
                                    return SEGUNDA;
    }

    public String toString(Context c) {

        switch (this.value) {
            case 0:
                return c.getString(R.string.day_monday);
            case 1:
                return c.getString(R.string.day_tuesday);
            case 2:
                return c.getString(R.string.day_wednesday);
            case 3:
                return c.getString(R.string.day_thursday);
            case 4:
                return c.getString(R.string.day_friday);
            case 5:
                return c.getString(R.string.day_saturday);
            case 6:
                return c.getString(R.string.day_sunday);
        }

        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.value);
    }
}
