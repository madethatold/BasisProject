package com.example.basisproject.widgt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.basisproject.R;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mTvTitle,mTvMessage,mTvCancel,mTvConfirm;
    private String title,message,cancel,confirm;

    //声明接口
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;

    //基本构造方法
    public CustomDialog(@NonNull Context context) {
        super(context);
    }
    public CustomDialog(@NonNull Context context, int themeId) {
        super(context,themeId);
    }

    //设置控件
    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }
    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }
    public CustomDialog setCancel(String cancel,IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener= listener;
        return this;
    }
    public CustomDialog setConfirm(String confirm,IOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener=listener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);

        mTvTitle=findViewById(R.id.tv_title);
        mTvMessage=findViewById(R.id.tv_message);
        mTvCancel=findViewById(R.id.tv_cancel);
        mTvConfirm=findViewById(R.id.tv_confirm);

        //设置文本文字
        if(!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            mTvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(confirm)){
            mTvConfirm.setText(confirm);
        }

        //cancel confirm 的点击事件
        mTvCancel.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
    }

    //实现接口带来的方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if(cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.tv_confirm:
                if(confirmListener!=null){
                    confirmListener.onConfirm(this);
                }
                break;

        }
    }

    //接口
    public interface IOnCancelListener{
        void onCancel(CustomDialog dialog);
    }

    //接口
    public interface IOnConfirmListener{
        void onConfirm(CustomDialog dialog);
    }
}
