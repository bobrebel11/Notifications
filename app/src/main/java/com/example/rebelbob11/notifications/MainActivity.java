package com.example.rebelbob11.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NOT_ID = 0;
    private static final int CHANNEL_ID = 1;
    Button showBtn;
    Button hidetn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBtn = findViewById(R.id.show);
        hidetn = findViewById(R.id.hide);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Showing notification", Toast.LENGTH_SHORT).show();

                showNotification();

            }
        });

        hidetn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideNotification();

            }
        });
    }

    
    private void showNotification() {
        Intent resultIntent = new Intent(this,MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);




        builder.setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_cake_black_24dp)
                .setContentTitle("Birthday")
                .setContentText("Its your friend's birthday")
                .setTicker("New Notification")
        .setContentIntent(pendingIntent);


        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN){
            builder.setPriority(Notification.PRIORITY_HIGH);
        }



        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(NOT_ID, builder.build());
    }

    private void hideNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.cancel(NOT_ID);
    }

}
