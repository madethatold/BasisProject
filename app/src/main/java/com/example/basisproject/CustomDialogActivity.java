package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.widgt.CustomDialog;

public class CustomDialogActivity extends AppCompatActivity {

    private Button mbtncustomdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        mbtncustomdialog=findViewById(R.id.btn_custom_dialog);
        mbtncustomdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog=new CustomDialog(CustomDialogActivity.this);
                customDialog.setTitle("notice")
                .setMessage("cancel truly?")
                .setCancel("cancel", new CustomDialog.IOnCancelListener() {
                    @Override
                    public void onCancel(CustomDialog dialog) {

                    }
                })
                .setConfirm("confirm", new CustomDialog.IOnConfirmListener() {
                    @Override
                    public void onConfirm(CustomDialog dialog) {

                    }
                }).show();

            }
        });
    }
}
