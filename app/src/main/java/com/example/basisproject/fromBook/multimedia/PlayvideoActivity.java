package com.example.basisproject.fromBook.multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import java.io.File;
import java.io.IOException;

public class PlayvideoActivity extends AppCompatActivity {
    private Button mbtnPlay,mbtnPause,mbtnStop;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        mbtnPlay=findViewById(R.id.btn_playvideo);
        mbtnPause=findViewById(R.id.btn_pausevideo);
        mbtnStop=findViewById(R.id.btn_stopvideo);
        mbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!videoView.isPlaying()){
                    videoView.start();
                }
            }
        });
        mbtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.pause();
                }
            }
        });
        mbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.resume();
                }
            }
        });
        if(ContextCompat.checkSelfPermission(PlayvideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayvideoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }else {
            initVideoPath();
        }
    }

    private void initVideoPath(){
            File file=new File(Environment.getExternalStorageDirectory(),"movie.mp4");
            videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else {
                    ToastUtil.showMsg(PlayvideoActivity.this,"拒绝权限将无法使用应用程序");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null){
            videoView.suspend();
        }
    }
}
