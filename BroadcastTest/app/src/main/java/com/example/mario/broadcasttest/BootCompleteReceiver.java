package com.example.mario.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by victor on 16/6/8.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    public void onReceive(Context context,Intent intent){
        Toast.makeText(context,"Boot complete", Toast.LENGTH_SHORT).show();
    }
}
