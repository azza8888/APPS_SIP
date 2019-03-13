package com.ezatech.apps_sip.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;
import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private SharedPreferences sharedpreferences;
    private String access_token;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    //this method will display the notification
    //We are passing the JSONObject that is received from
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        //
        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        access_token = sharedpreferences.getString("acces_token", "");
        //
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("");

            //parsing json data
            String no_surat = data.getString("title");
            String finalmessage = data.getString("message");
//            log.d("cekparsing", title+finalmessage+telp+lat+lng);

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), ListLaporanActivity.class);
            intent.putExtra("title",no_surat);
            intent.putExtra("message",finalmessage);
            //
            intent.putExtra("acces_token", access_token);
            startActivity(intent);
            Log.d(TAG, "AAAAAAAAAAAAAAAAAA: "+no_surat);


        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
}
