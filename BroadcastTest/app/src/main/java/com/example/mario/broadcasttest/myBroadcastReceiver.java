package com.example.mario.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by victor on 16/6/8.
 */
public class myBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Received in MyBroadCastReceiver",Toast.LENGTH_SHORT).show();
        abortBroadcast(); //在有序传递中,当此接收器接收到广播,停止广播传递到其他接收器.
    }
}
