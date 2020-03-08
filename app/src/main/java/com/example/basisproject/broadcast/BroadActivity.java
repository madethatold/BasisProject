package com.example.basisproject.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.basisproject.R;

public class BroadActivity extends AppCompatActivity {

    private Button mbtn1;
    private TextView mtvTest;
    private MyBroadcast myBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);

        mbtn1=findViewById(R.id.btn1);
        mtvTest=findViewById(R.id.tv_test);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BroadActivity.this,BroadActivity2.class);
                startActivity(intent);
            }
        });

        myBroadcast=new MyBroadcast();//实例化
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.update");//监听此广播
        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);//获取实例
        localBroadcastManager.registerReceiver(myBroadcast,intentFilter);//注册


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(myBroadcast);
    }


    private class MyBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "com.example.update":
                    mtvTest.setText("finally");
                    break;
            }
        }
    }


}
