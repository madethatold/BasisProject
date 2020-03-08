package com.example.basisproject.fromBook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basisproject.R;

import java.util.ArrayList;
import java.util.List;

public class TalkActivity extends AppCompatActivity {
    private RecyclerView msgrecyclerView;
    private EditText inputEdit;
    private Button btnsend;
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        initMsgs();

        msgrecyclerView=findViewById(R.id.rv_talk);
        inputEdit=findViewById(R.id.et_content);
        btnsend=findViewById(R.id.btn_send);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        msgrecyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter=new MsgAdapter(msgList);
        msgrecyclerView.setAdapter(msgAdapter);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=inputEdit.getText().toString();
                Msg msg=new Msg(content, Msg.TYPE_SEND);
                msgList.add(msg);
                msgAdapter.notifyItemInserted(msgList.size()-1);
                msgrecyclerView.scrollToPosition(msgList.size()-1);
                inputEdit.setText("");

            }
        });

    }
    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello . Who is that?",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }


}
