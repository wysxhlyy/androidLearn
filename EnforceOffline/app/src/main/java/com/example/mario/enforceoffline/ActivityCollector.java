package com.example.mario.enforceoffline;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/6/10.
 * 存储activity的容器.
 */
public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
