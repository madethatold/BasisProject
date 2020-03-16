package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnbindService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /*在活动成功绑定时候执行*/
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            /*在活动和服务没有成功绑定时候执行*/
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnBindService=findViewById(R.id.btn_bind_service);
        btnUnbindService=findViewById(R.id.btn_unbind_service);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); //启动服务
                break;
            case R.id.btn_stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);  //停止服务
                break;
            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);  //解绑服务
                break;
        }
    }
}
