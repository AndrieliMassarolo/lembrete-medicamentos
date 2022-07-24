package me.ads.lembretemedicamentos.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import me.ads.lembretemedicamentos.MainActivity;
import me.ads.lembretemedicamentos.R;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String CHANNEL_ID = "notification_id";
        CharSequence name = context.getResources().getString(R.string.app_name);
        NotificationCompat.Builder mBuilder;
        Intent notificationIntent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String title = intent.getStringExtra(NotificationHelper.TITLE);
        String content = intent.getStringExtra(NotificationHelper.CONTENT);

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLights(Color.RED, 300, 300)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle(title);
        } else {
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(me.ads.lembretemedicamentos.R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentTitle(title);
        }

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setContentText(content);
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(1, mBuilder.build());
    }

}