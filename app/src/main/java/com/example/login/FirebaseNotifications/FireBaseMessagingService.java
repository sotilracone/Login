package com.example.login.FirebaseNotifications;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseMessagingService extends FirebaseMessagingService {


    public static final String TAG="Notificacion";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String From=remoteMessage.getFrom();

        Log.d("", "onMessageReceived: "+From);

        if (remoteMessage.getNotification()!=null){
            Log.d(TAG, "Notificacion:"+ remoteMessage.getNotification().getBody());
        }
    }
}
