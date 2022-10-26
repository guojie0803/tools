package com.guojie.tools.backgroundService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.guojie.tools.R;
import com.guojie.tools.guide.GuideActivity;


public class KeepBackgroundService extends Service {
    private NotificationManager notificationManager;
    private static String channelId = "myChannelId";
    private static String channelName = "micphonechannel";
    private int ONGOING_NOTIFICATION_ID = 1001;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_MIN);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            if (command != null) {
                if (command.equals("start_foreground")) {
                    myStartForeground();
                } else if (command.equals("stop_foreground")) {
                    myStopForeground();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void myStartForeground() {
        Intent notificationIntent = new Intent(this, GuideActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);


        Notification notification =
                new Notification.Builder(this, channelId)
                        .setContentTitle("CPTT")
                        .setContentText("Background Running")
                        .setSmallIcon(R.drawable.point_on)
                        .setContentIntent(pendingIntent)
                        .setTicker("ticker...")
                        .build();

        // Notification ID cannot be 0.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(ONGOING_NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE);
        }
    }

    private void myStopForeground() {
        stopForeground(true);
//        stopSelf();
    }
}
