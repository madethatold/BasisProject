package com.example.basisproject.Bookpart_2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.basisproject.MainActivity;
import com.example.basisproject.R;
import com.example.basisproject.util.NotificationUtil;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;

    /*创造一个DownloadListener匿名类实例，在匿名类中实现具体的五个方法*/
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            /*使用getNotification构造一个显示下载进度的通知
             * 使用NotificationManager的notify方法去触发这个通知*/
            getNotificationManager().notify(1, getNotification("Downloading...", progress));

        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true); //关闭前台服务
            getNotificationManager().notify(1, getNotification("Download success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败将前台通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_LONG).show();
        }

    };

    /*为了服务和活动可以通信，创建一个DownloadBinder*/
    private DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    /*DownloadBinder中提供了开始，暂停 取消三种方法*/
    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                /*设置为前台服务*/
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this,
                        "Downloading,,,", Toast.LENGTH_LONG).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            }
            if (downloadUrl != null) {
                //取消下载要删除文件，关闭通知
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//下载文件名
                String directory = Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).getPath();  //下载地址
                File file = new File(directory + fileName);//下载文件的目录
                if(file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(1);  //关闭通知
                stopForeground(true);  //取消前台通知
                Toast.makeText(DownloadService.this,"Canceled",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /*返回通知管理器的方法*/
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /*构造一个显示下载进度的通知*/
    private Notification getNotification(String title, int progress) {
        /*点击跳转intent*/
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0) {
            //当progress大于或等于0才显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }
}
