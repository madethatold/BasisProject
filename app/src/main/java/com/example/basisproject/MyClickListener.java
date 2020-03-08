package com.example.basisproject;

import android.app.Activity;
import android.view.View;

import com.example.basisproject.util.ToastUtil;

//外部类实现监听事件
public class MyClickListener implements View.OnClickListener {
    private Activity mActivity;

    public MyClickListener(Activity activity){
        this.mActivity=activity;
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showMsg(mActivity,"click...");
    }
}
