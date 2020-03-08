package com.example.basisproject.fromBook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.basisproject.util.ToastUtil;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.showMsg(context,"Boot Complete");
    }
}
