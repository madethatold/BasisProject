package com.example.basisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.basisproject.util.ToastUtil;

public class HandlerActivity extends AppCompatActivity {
    private Handler mhandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
//        mhandler=new Handler();
//        mhandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(HandlerActivity.this,PopupWindowActivity.class);
//                startActivity(intent);
//            }
//        }, 3000);//延时3s进入Activity

        mhandler=new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        ToastUtil.showMsg(HandlerActivity.this,"线程");
                        break;
                }
            }
        };
        //额外线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message msg=new Message();
                msg.what=1;
                mhandler.sendMessage(msg);
            }
        }.start();
    }
}
