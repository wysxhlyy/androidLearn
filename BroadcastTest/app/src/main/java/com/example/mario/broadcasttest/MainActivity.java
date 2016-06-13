package com.example.mario.broadcasttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * 这里既实现了全局广播的动态和静态的接收,也实现了本地广播的发送和接受,但由于无法同时应用本地广播和全局广播,所以目前的代码是只接受本地广播.
 * 稍作改动,便能实现接受全局广播的功能.
 */

public class MainActivity extends Activity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver; //动态注册全局广播

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 全局广播
         */
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//当系统网络发现变化时,会发出这条广播.所以监听器想监听什么广播,这里就添加什么action.

        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);

        Button send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.example.broadcasttest.MY_BROADCAST");//和myBroadcastReceiver所接收的广播相等.
                sendOrderedBroadcast(intent, null); //有序传递,第二个参数与权限有关,暂为null.
            }
        });


        /**
         * 本地广播
         */
        localBroadcastManager=LocalBroadcastManager.getInstance(this); //获取实例

        Button local=(Button)findViewById(R.id.local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.example.broadcasttest.LOCAL_BROADCAST"); //发送一条本地广播.
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    /**
     * 动态注册的广播接收器一定要取消注册.
     */
    /*protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }*/

    /**
     * 本地广播接收器的取消注册.
     */
    protected void onDestroy(){
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    /**
     * 本地广播接收器.
     */
    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received local Broadcast",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 创建一个广播接受器:继承BroadcastReceiver并重写父类的onReceive().
     */

    class NetworkChangeReceiver extends BroadcastReceiver{
        public void onReceive(Context context,Intent intent){
            ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);//用getSystemService方法获取ConnectivityManager实例.

            NetworkInfo networkInfo=cm.getActiveNetworkInfo();//获取networkInfo实例. 用isavailable()查询网络状态. 需要在manifests中注册权限.
            if(networkInfo!=null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
