package com.example.basisproject.fromBook.login_offline;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private OffLineReceiver offLineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.FORCE_OFFLINE");
        Log.d("receive__", "onResume: ");
        offLineReceiver=new OffLineReceiver();
        registerReceiver(offLineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(offLineReceiver!=null){
            unregisterReceiver(offLineReceiver);
            offLineReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    class OffLineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            Log.d("rererererere", "onReceive: ");
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning").setMessage("u're forced to be offline.Please try to login again").setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finiall();
                    Intent intent=new Intent(context,LoginOffLineActivity.class);
                    context.startActivity(intent);
                }
            }).show();
        }
    }
}
