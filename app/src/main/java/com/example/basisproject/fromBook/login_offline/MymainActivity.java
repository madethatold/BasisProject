package com.example.basisproject.fromBook.login_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class MymainActivity extends BaseActivity {
    private Button btnoffline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymain);
        btnoffline=findViewById(R.id.btn_offline);
        btnoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
