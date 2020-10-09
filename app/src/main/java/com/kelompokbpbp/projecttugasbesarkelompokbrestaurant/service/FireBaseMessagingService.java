package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.R;

public class FireBaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    private void showNotification(String title,String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"Channel 1")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.logo_restoku)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,builder.build());
    }
}


