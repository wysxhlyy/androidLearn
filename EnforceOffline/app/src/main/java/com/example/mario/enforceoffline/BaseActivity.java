package com.example.mario.enforceoffline;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by victor on 16/6/10.
 * activity的父类.
 */
public class BaseActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}


