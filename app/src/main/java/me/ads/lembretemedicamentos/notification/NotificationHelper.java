package me.ads.lembretemedicamentos.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class NotificationHelper {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    public static void startAlarmBroadcastReceiver(Context context, String title, String content, int hour, int minute) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(CONTENT, content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
