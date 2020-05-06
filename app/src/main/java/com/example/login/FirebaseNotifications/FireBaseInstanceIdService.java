package com.example.login.FirebaseNotifications;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;

public class FireBaseInstanceIdService extends FireBaseMessagingService {

    public static final String TAG="Notificacion";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh: "+token);
    }
}
