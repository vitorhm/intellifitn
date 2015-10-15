package com.prjvt.intellifitn.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.prjvt.intellifitn.MainActivity;
import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.service.AlarmService;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by vitor on 15/10/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, AlarmService.class);
        context.startService(service1);
    }

}
