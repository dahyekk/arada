package com.example.arada.ui.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.arada.R;

public class AlarmReceive extends BroadcastReceiver {

    private int YOURAPP_NOTIFICATION_ID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "!!!!", Toast.LENGTH_SHORT).show();

        showNotification(context, R.drawable.ic_create_black_24dp,
                "알람!!", "지금 이러고 있을 시간 없다.");
    }

    private void showNotification(Context context, int statusBarIconID,
                                  String statusBarTextID, String detailedTextID) {
        Intent contentIntent = new Intent(context, AlarmFragment.class);
        PendingIntent theappIntent =
                PendingIntent.getActivity(context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        CharSequence from = "알람";
        CharSequence message = "무슨짓을 해야  알람이 꺼질까요?";

        Notification notif = new Notification(statusBarIconID, null, System.currentTimeMillis());
        notif.sound = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6");//ringURI;
        notif.flags = Notification.FLAG_INSISTENT;
        // notif.setLatestEventInfo(context, from, message, theappIntent);
        notif.ledARGB = Color.GREEN;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(1234, notif);

    }
}