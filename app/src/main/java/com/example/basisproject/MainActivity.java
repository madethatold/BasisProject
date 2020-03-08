package com.example.basisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.Bookpart_2.BookPart2Activity;
import com.example.basisproject.Fragment.ContainerActivity;
import com.example.basisproject.broadcast.BroadActivity;
import com.example.basisproject.datastorage.DataStorageActivity;
import com.example.basisproject.jump.AActivity;
import com.example.basisproject.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private Button mBtnUI,mBtnLifeCycle,mBtnJump,mBtnFragment,mBtnEvent,mBtnData,mBtnBroad,mBtnFromBook,mBtnBookPart2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnUI=findViewById(R.id.btn_ui);
        mBtnLifeCycle=findViewById(R.id.btn_lifecycle);
        mBtnJump=findViewById(R.id.btn_jump);
        mBtnFragment=findViewById(R.id.btn_fragment);
        mBtnEvent=findViewById(R.id.btn_event);
        mBtnData=findViewById(R.id.btn_data);
        mBtnBroad=findViewById(R.id.btn_broad);
        mBtnFromBook=findViewById(R.id.btn_frombook);
        mBtnBookPart2=findViewById(R.id.btn_bookpart2);


        OnClick onclick=new OnClick();

        mBtnUI.setOnClickListener(onclick);
        mBtnLifeCycle.setOnClickListener(onclick);
        mBtnJump.setOnClickListener(onclick);
        mBtnFragment.setOnClickListener(onclick);
        mBtnEvent.setOnClickListener(onclick);
        mBtnData.setOnClickListener(onclick);
        mBtnBroad.setOnClickListener(onclick);
        mBtnFromBook.setOnClickListener(onclick);
        mBtnBookPart2.setOnClickListener(onclick);

    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this,UIActivity.class);
                    break;
                case R.id.btn_lifecycle:
                    intent = new Intent(MainActivity.this,LifeCycleActivity.class);
                    break;
                case R.id.btn_jump:
                    intent = new Intent(MainActivity.this, AActivity.class);
                    break;
                case R.id.btn_fragment:
                    intent = new Intent(MainActivity.this, ContainerActivity.class);
                    break;
                case R.id.btn_event:
                    intent = new Intent(MainActivity.this, EventActivity.class);
                    break;
                case R.id.btn_data:
                    intent = new Intent(MainActivity.this, DataStorageActivity.class);
                    break;
                case R.id.btn_broad:
                    intent = new Intent(MainActivity.this, BroadActivity.class);
                    break;
                case R.id.btn_frombook:
                    intent = new Intent(MainActivity.this, FromBookActivity.class);
                    break;
                case R.id.btn_bookpart2:
                    intent = new Intent(MainActivity.this, BookPart2Activity.class);
                    break;


            }
            startActivity(intent);
        }
    }

    //重写该方法  给当前活动创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;//允许显示
    }

    //重写该方法  给每个菜单项加入逻辑处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                ToastUtil.showMsg(MainActivity.this,"add");
                break;
            case R.id.item_remove:
                ToastUtil.showMsg(MainActivity.this,"remove");
                break;
        }
        return true;
    }
}
