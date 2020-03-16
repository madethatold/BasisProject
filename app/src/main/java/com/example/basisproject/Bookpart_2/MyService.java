package com.example.basisproject.Bookpart_2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.basisproject.R;
import com.example.basisproject.util.NotificationUtil;

public class MyService extends Service {


    //服务于活动之间联系
    private DownloadBinder mBinder = new DownloadBinder();

    static class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d("MyService","startDownload executed");
        }
        public int getProgress(){
            Log.d("MyService","getProgress executed");
            return 0;
        }
    }


    /*在服务创建时被调用*/
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "服务已创建");

        //前台服务
        Intent intent=new Intent(this,ServiceTestActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        NotificationUtil notificationUtil=new NotificationUtil(MyService.this);
        notificationUtil.sendNotification("tile","content",pi);

    }

    /*在服务每次启动时候调用*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /*在服务被销毁时候调用*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "服务已销毁 ");
    }


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
