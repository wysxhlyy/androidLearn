package com.example.mario.enforceoffline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

/**
 * Created by victor on 16/6/10.
 */
public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(context);
        dialogBuilder.setTitle("warning");
        dialogBuilder.setMessage("You are forced to be offline .Please Try to login again");
        dialogBuilder.setCancelable(false);//将对话框设为不可取消
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() { //给对话框注册确定按钮
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCollector.finishAll(); //销毁所有活动
                Intent intent=new Intent(context,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);  //重新启动login activity.
            }
        });
        AlertDialog alertDialog=dialogBuilder.create();

        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
