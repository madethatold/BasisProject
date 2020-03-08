package com.example.basisproject.fromBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import javax.security.auth.Destroyable;

public class BroadcastTestActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;//接收广播

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        intentFilter=new IntentFilter();//创建实例
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//监听此广播
        networkChangeReceiver=new NetworkChangeReceiver();//创建实例
        registerReceiver(networkChangeReceiver,intentFilter);//注册
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    //定义一个类，并重写方法
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                ToastUtil.showMsg(BroadcastTestActivity.this,"available");
            }else {
                ToastUtil.showMsg(BroadcastTestActivity.this,"unavailable");
            }

        }
    }
}

