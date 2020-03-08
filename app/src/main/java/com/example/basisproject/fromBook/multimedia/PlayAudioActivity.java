package com.example.basisproject.fromBook.multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import java.io.File;
import java.io.IOException;

public class PlayAudioActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private Button mbtnPlay,mbtnPause,mbtnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        mbtnPlay=findViewById(R.id.btn_play);
        mbtnPause=findViewById(R.id.btn_pause);
        mbtnStop=findViewById(R.id.btn_stop);
        mbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });
        mbtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(mediaPlayer.isPlaying()){
                 mediaPlayer.pause();
             }
            }
        });
        mbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
            }
        });
        if(ContextCompat.checkSelfPermission(PlayAudioActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayAudioActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }else {
            initMediaPlayer();
        }




    }
    private void initMediaPlayer(){
        try {
            File file=new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    ToastUtil.showMsg(PlayAudioActivity.this,"拒绝权限将无法使用应用程序");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
