package com.example.zhide.xiongmaoweixiao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;

/**
 * Created by zhide on 2017/4/9.
 */

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bmob.initialize(BaseActivity.this,"9a7b3c23c1697689b4dfbdda30660131");
        BmobSMS.initialize(BaseActivity.this,"9a7b3c23c1697689b4dfbdda30660131");
        ActivityCollector.addActivity(this);
    }
    protected  void onDestory(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver =new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }
    protected  void onPause(){
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }
}
class ForceOfflineReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("warning");
        builder.setMessage("You are forced to be offline.Please try to login again");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                ActivityCollector.finishAll();
                Intent intent=new Intent(context,LoginActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
