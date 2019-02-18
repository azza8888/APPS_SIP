package com.ezatech.apps_sip.firebase;

import android.content.Intent;
import android.util.Log;

import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

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
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String finalmessage = data.getString("message");
            String telp = data.getString("telp");
//            String jam = data.getString("jam");
            String lat = data.getString("lat");
            String lng = data.getString("lng");
            String imageUrl = data.getString("image");
//            log.d("cekparsing", title+finalmessage+telp+lat+lng);

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), DetailLapActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("message",finalmessage);
            intent.putExtra("telp",telp);
            intent.putExtra("lat",lat);
            intent.putExtra("lng",lng);
            Log.d("cekputextra", title+finalmessage+telp+lat+lng);



            //if there is no image
            if(imageUrl.equals("null")){
                //displaying small notification
                mNotificationManager.showSmallNotification(title, finalmessage, telp, lat, lng, intent);
            }else{
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, finalmessage, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
}
