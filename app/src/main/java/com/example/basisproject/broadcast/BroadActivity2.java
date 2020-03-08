package com.example.basisproject.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class BroadActivity2 extends AppCompatActivity {

    private Button mbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad2);
        mbtn2=findViewById(R.id.btn_click);
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.update");
                LocalBroadcastManager.getInstance(BroadActivity2.this).sendBroadcast(intent);//发送本地广播

            }
        });
    }
}
