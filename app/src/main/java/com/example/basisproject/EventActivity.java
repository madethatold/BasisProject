package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.util.ToastUtil;
import com.example.basisproject.widgt.MyButton;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{
    private MyButton mbtnMy;
    private Button mbtnEvent,mbtnHandler;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mbtnEvent=findViewById(R.id.btn_event);
        mbtnMy=findViewById(R.id.btn_my);
        mbtnHandler=findViewById(R.id.btn_handler);

        //当同时设置了多个监听器，系统会响应最后一个监听器，其他无效（布局文件中的show为最先设置的监听器）

        //内部类实现点击事件
//        mbtnEvent.setOnClickListener(new OnClick());
        //匿名内部类实现点击事件
        mbtnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg(EventActivity.this,"click...");
            }
        });
        //通过事件源所在的类实现
//        mbtnEvent.setOnClickListener(EventActivity.this);
        //通过外部类实现
//        mbtnEvent.setOnClickListener(new MyClickListener(EventActivity.this));



        mbtnMy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Listener","_onTouch_");
                        break;
                }
                return false;
            }
        });
        mbtnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Listener","_onClick_");
            }
        });

        mbtnHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventActivity.this, HandlerActivity.class);
                startActivity(intent);
            }
        });
    }

    //事件源所在的类实现View.OnClickListener带来的方法
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_event:
                ToastUtil.showMsg(EventActivity.this, "click...");
                break;
        }
    }

    //内部类
    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_event:
                    ToastUtil.showMsg(EventActivity.this, "click...");
                    break;
            }
        }
    }

    //布局文件Button加onCLick属性
//        public void show(View v) {
//            switch (v.getId()) {
//                case R.id.btn_event:
//                    ToastUtil.showMsg(EventActivity.this, "click...");
//                    break;
//            }
//        }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity","_onTouchEvent_");
                break;
        }
        return false;
    }
}
