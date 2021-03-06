package me.franciscoigor.tasks.controllers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DataModel;
import me.franciscoigor.tasks.base.DatabaseHelper;
import me.franciscoigor.tasks.base.Storage;
import me.franciscoigor.tasks.models.TaskModel;

public class NotifierActivity extends AppCompatActivity {

    static PendingIntent appIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<DataModel> filtered= DatabaseHelper.getItems("tasks", String.format("%s <> '%s'", TaskModel.FIELD_FINISHED,"1"),new String[0]);
        System.out.println(filtered);
        NotifierActivity.notifyUser(this, "Pending tasks", String.format("%d pending tasks for today", filtered.size()));

        System.out.println("Starting intent now !!");


    }

    public static void startNotifier(Context context){
        Intent intent = new Intent(context, NotifierActivity.class);
        PendingIntent notifierActivity = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10000,
                10000, notifierActivity);
    }

    public static void notifyUser(Context context, String title, String message){
        if (appIntent == null){
            Intent intent = new Intent(context, MainActivity.class);
            appIntent = PendingIntent.getActivity(context, 0, intent, 0);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "MAIN")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(appIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Storage.addInt("NOTIFICATION_ID",1);
        int id = Storage.getInt("NOTIFICATION_ID",1);
        notificationManager.notify(id, mBuilder.build());
    }

}
