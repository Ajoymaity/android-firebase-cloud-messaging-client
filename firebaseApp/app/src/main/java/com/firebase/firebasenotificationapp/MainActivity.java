package com.firebase.firebasenotificationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerTopics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("*/*");
//        String[] extraMimeTypes = {"file/*", "video/*"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, extraMimeTypes);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//       // startActivityForResult(intent, RQS_OPEN);



        spinnerTopics = findViewById(R.id.spinnerTopics);
        findViewById(R.id.buttonSubscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = spinnerTopics.getSelectedItem().toString();
                FirebaseMessaging.getInstance().subscribeToTopic(topic);

                Toast.makeText(getApplicationContext(), "Subscribed", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }
}
