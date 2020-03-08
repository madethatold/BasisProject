package com.example.basisproject.fromBook.multimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;
import com.example.basisproject.fromBook.login_offline.MymainActivity;
import com.example.basisproject.util.NotificationUtil;

public class NotificationActivity extends AppCompatActivity {

    private Button btnSendNo,btnUseUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnSendNo=findViewById(R.id.btn_sendnotification);
        btnUseUtils=findViewById(R.id.btn_useutils);

        btnSendNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//使用的是getSystemService方法，传入一个服务，返回一个manager

                String channelId = "app1";
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelId,"app1",NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(NotificationActivity.this,MymainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this,0,intent,0);

                Notification notification = new NotificationCompat.Builder(NotificationActivity.this,channelId)
                        .setContentTitle("通知标题")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("通知内容:this is a notification which is about how use the tile bar,ok"))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.icon_love)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);
            }
        });

        btnUseUtils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this,MymainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
                NotificationUtil notificationUtils =new NotificationUtil(NotificationActivity.this);
                notificationUtils.sendNotification("UTILS","NICE !!TACO TUESDAY",pi);

            }
        });
    }


}
