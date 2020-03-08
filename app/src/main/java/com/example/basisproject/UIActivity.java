package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.recyclerview.RecyclerViewActivity;

public class UIActivity extends AppCompatActivity {
    private Button mBtnRv,mBtnWebView,mBtnToast,mBtnDialog,mBtnProgress,mBtnCustomDialog,mBtnPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        mBtnRv=findViewById(R.id.btn_recyclerview);
        mBtnWebView=findViewById(R.id.btn_webview);
        mBtnToast=findViewById(R.id.btn_toast);
        mBtnDialog=findViewById(R.id.btn_dialog);
        mBtnProgress=findViewById(R.id.btn_progress);
        mBtnCustomDialog=findViewById(R.id.btn_customdialog);
        mBtnPopupWindow=findViewById(R.id.btn_popupwindow);

        OnClick onclick=new OnClick();

        mBtnRv.setOnClickListener(onclick);
        mBtnWebView.setOnClickListener(onclick);
        mBtnToast.setOnClickListener(onclick);
        mBtnDialog.setOnClickListener(onclick);
        mBtnProgress.setOnClickListener(onclick);
        mBtnCustomDialog.setOnClickListener(onclick);
        mBtnPopupWindow.setOnClickListener(onclick);
    }
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_recyclerview:
                    intent = new Intent(UIActivity.this, RecyclerViewActivity.class);
                    break;
                case R.id.btn_webview:
                    intent = new Intent(UIActivity.this,WebViewActivity.class);
                    break;
                case R.id.btn_toast:
                    intent = new Intent(UIActivity.this,ToastActivity.class);
                    break;
                case R.id.btn_dialog:
                    intent = new Intent(UIActivity.this, DialogActivity.class);
                    break;
                case R.id.btn_progress:
                    intent = new Intent(UIActivity.this,ProgressActivity.class);
                    break;
                case R.id.btn_customdialog:
                    intent = new Intent(UIActivity.this,CustomDialogActivity.class);
                    break;
                case R.id.btn_popupwindow:
                    intent = new Intent(UIActivity.this,PopupWindowActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
