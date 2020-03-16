package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.basisproject.R;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {
    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            //服务被绑定时的操作 获取了DownloadBinder的实例，有了实例就可以在活动中调用服务提供的方法。
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //服务解除绑定时的操作
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button startDownload = findViewById(R.id.btn_start_download);
        Button pauseDownload = findViewById(R.id.btn_pause_download);
        Button cancelDowmload =findViewById(R.id.btn_cancel_download);
        startDownload.setOnClickListener(this);
        pauseDownload.setOnClickListener(this);
        cancelDowmload.setOnClickListener(this);

        /*启动服务并绑定服务
         * 启动服务可以保证DownloadService一直在后台运行*/
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
        /*sd卡读写权限申请判断*/
        if(ContextCompat.checkSelfPermission(DownloadActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownloadActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onClick(View view) {
        if(downloadBinder==null){
            return;
        }
        switch (view.getId()) {
            case R.id.btn_start_download:
                String url = "http://raw.githubusercentent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                downloadBinder.startDownload(url);
                break;
            case R.id.btn_pause_download:
                downloadBinder.pauseDownload();
                break;
            case R.id.btn_cancel_download:
                downloadBinder.cancelDownload();
                break;
            default:
                break;
        }
    }

    //requestPermissions的回调函数，用来判断权限
    @Override
    public void onRequestPermissionsResult(int requesCode,String[] permissions,int[] grantResults){
        switch (requesCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"拒绝授权无法使用程序",Toast.LENGTH_LONG)
                            .show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    /*活动销毁的时候要解除服务的绑定*/
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(connection);
    }
}
