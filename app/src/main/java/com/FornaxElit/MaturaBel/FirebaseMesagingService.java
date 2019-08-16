package com.FornaxElit.MaturaBel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.crashlytics.android.core.CrashlyticsCore.TAG;

public class FirebaseMesagingService extends FirebaseMessagingService {

    public static int NOTIFICATION_ID = 1;
    final public static String CHANNEL_ID = "weekUpdate";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");
        if(title == null){
            title = remoteMessage.getNotification().getTitle();
        }
        generateNotification(body, title);
        //remoteMessage.getNotification().getTitle()
    }


    @Override
    public void onNewToken(String s) {
        Log.d("DEVICE TOKEN", s);
    }

    public void generateNotification(String body, String title){

        //if(body.equals("weeks")){
            Date startDate = Calendar.getInstance().getTime();
            Date endDate = null;


            String dateStr = "24/05/2020";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                endDate = sdf.parse(dateStr);
            }catch (Exception e){
                Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }

            long diff = endDate.getTime() - startDate.getTime();

            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = (hours / 24) + 1;
            long weeks = days/7;

            body = "Остават: " + weeks + " седмици до матурата!";
       // }


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Week Update", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Week update");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.owl_pic_transperent_full_color_splash_screen))
                .setSmallIcon(R.drawable.small_owl_pic_24x24_full_color)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);

        if(NOTIFICATION_ID > 1073741824){
            NOTIFICATION_ID = 0;
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
