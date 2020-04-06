package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.basisproject.R;

public class ThreadActivity extends AppCompatActivity {

    private TextView tvText;
    private Button btnChangeText;
    public static final int UPDATE_TEXT = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("SetTextI18n")
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里可以进行ui操作
                    tvText.setText("change ui");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        tvText = findViewById(R.id.tv_text);
        btnChangeText =  findViewById(R.id.btn_changetext);

        OnClick onClick=new OnClick();

        btnChangeText.setOnClickListener(onClick);



    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_changetext:

                    //新的线程
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;//常量
                            handler.sendMessage(message);  //将Message对象发送出去
                        }
                    }).start();
                    break;
            }
        }
    }

}
