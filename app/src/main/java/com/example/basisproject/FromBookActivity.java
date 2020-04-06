package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.fromBook.BroadcastTestActivity;
import com.example.basisproject.fromBook.ContactsActivity;
import com.example.basisproject.fromBook.FilePersistenceActivity;
import com.example.basisproject.fromBook.MyDataBaseActivity;
import com.example.basisproject.fromBook.MyDatabaseHelper;
import com.example.basisproject.fromBook.RuntimePermissionActivity;
import com.example.basisproject.fromBook.TalkActivity;
import com.example.basisproject.fromBook.TitleBarActivity;
import com.example.basisproject.fromBook.login_offline.LoginOffLineActivity;
import com.example.basisproject.fromBook.multimedia.NotificationActivity;
import com.example.basisproject.fromBook.multimedia.PlayAudioActivity;
import com.example.basisproject.fromBook.multimedia.PlayvideoActivity;

public class FromBookActivity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;

    private Button mbtnCall,mbtntitlebar,mbtnTalk,mbtBCTest,mbtnLoginOff,mbtnFileP;
    private Button mbtnsqlite,mbtnRuntimeP,mbtnContacts,mbtnSendN,mbtnPlayAudio,mbtnPlayVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_book);

        myDatabaseHelper=new MyDatabaseHelper(this,"BookStore.dp",null,1);

        mbtnCall=findViewById(R.id.btn_call);
        mbtntitlebar=findViewById(R.id.btn_titlebar);
        mbtnTalk=findViewById(R.id.btn_talk);
        mbtBCTest=findViewById(R.id.btn_bctest);
        mbtnLoginOff=findViewById(R.id.btn_loginoff);
        mbtnFileP=findViewById(R.id.btn_filep);
        mbtnsqlite=findViewById(R.id.btn_sqlite);
        mbtnRuntimeP=findViewById(R.id.btn_runtimep);
        mbtnContacts=findViewById(R.id.btn_contacts);
        mbtnSendN=findViewById(R.id.btn_sendNotification);
        mbtnPlayAudio=findViewById(R.id.btn_playaudio);
        mbtnPlayVideo=findViewById(R.id.btn_playvideo);

        OnClick onClick=new OnClick();

        mbtnCall.setOnClickListener(onClick);
        mbtntitlebar.setOnClickListener(onClick);
        mbtnTalk.setOnClickListener(onClick);
        mbtBCTest.setOnClickListener(onClick);
        mbtnLoginOff.setOnClickListener(onClick);
        mbtnFileP.setOnClickListener(onClick);
        mbtnsqlite.setOnClickListener(onClick);
        mbtnRuntimeP.setOnClickListener(onClick);
        mbtnContacts.setOnClickListener(onClick);
        mbtnSendN.setOnClickListener(onClick);
        mbtnPlayAudio.setOnClickListener(onClick);
        mbtnPlayVideo.setOnClickListener(onClick);
    }
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.btn_call:
                    intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:10086"));
                    break;
                case R.id.btn_titlebar:
                    intent=new Intent(FromBookActivity.this, TitleBarActivity.class);
                    break;
                case R.id.btn_talk:
                    intent=new Intent(FromBookActivity.this, TalkActivity.class);
                    break;
                case R.id.btn_bctest:
                    intent=new Intent(FromBookActivity.this, BroadcastTestActivity.class);
                    break;
                case R.id.btn_loginoff:
                    intent=new Intent(FromBookActivity.this, LoginOffLineActivity.class);
                    break;
                case R.id.btn_filep:
                    intent=new Intent(FromBookActivity.this, FilePersistenceActivity.class);
                    break;
                case R.id.btn_sqlite:
                    intent=new Intent(FromBookActivity.this, MyDataBaseActivity.class);
                    break;
                case R.id.btn_runtimep:
                    intent=new Intent(FromBookActivity.this, RuntimePermissionActivity.class);
                    break;
                case R.id.btn_contacts:
                    intent=new Intent(FromBookActivity.this, ContactsActivity.class);
                    break;
                case R.id.btn_sendNotification:
                    intent=new Intent(FromBookActivity.this, NotificationActivity.class);
                    break;
                case R.id.btn_playaudio:
                    intent=new Intent(FromBookActivity.this, PlayAudioActivity.class);
                    break;
                case R.id.btn_playvideo:
                    intent=new Intent(FromBookActivity.this, PlayvideoActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
