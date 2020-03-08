package com.example.basisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.basisproject.util.ToastUtil;

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar mpb1;
    private Button mbtnStart,mbtnProgresssDialog1,mbtnProgresssDialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mpb1=findViewById(R.id.pb1);
        mbtnStart=findViewById(R.id.btn_start);
        mbtnProgresssDialog1=findViewById(R.id.btn_progress_dialog1);
        mbtnProgresssDialog2=findViewById(R.id.btn_progress_dialog2);

        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });

        mbtnProgresssDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(ProgressActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("loading");
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        ToastUtil.showMsg(ProgressActivity.this,"cancel...");
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });
        mbtnProgresssDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(ProgressActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("loading...");
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(ProgressActivity.this,":p");
                    }
                });
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });

    }
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(mpb1.getProgress()<100){
                handler.postDelayed(runnable,500);
            }else {
                ToastUtil.showMsg(ProgressActivity.this,"success");
            }
        }
    };
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            mpb1.setProgress(mpb1.getProgress()+5);
            handler.sendEmptyMessage(0);

        }
    };
}
