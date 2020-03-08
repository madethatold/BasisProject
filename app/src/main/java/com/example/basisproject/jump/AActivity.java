package com.example.basisproject.jump;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.basisproject.R;

public class AActivity extends AppCompatActivity {
    private Button mbtnJump;
//    private Button mBtnJump2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

//        Log.d("AActivity","onCreate");
//        Log.d("AActivity","taskid"+getTaskId()+" _hash"+hashCode());
//        logtaskName();

        mbtnJump=findViewById(R.id.btn_jump);
//        mBtnJump2=findViewById(R.id.btn_jump_2);

        mbtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式跳转1
                Intent intent=new Intent(AActivity.this,BActivity.class);
                Bundle bundle=new Bundle();//以bundle为载体
                bundle.putString("name","colorful");//数据放入bundle
                bundle.putInt("num",24);//数据放入bundle
                intent.putExtras(bundle);//传入bundle
//                startActivity(intent);
                startActivityForResult(intent,0);


                //显式跳转2
//                Intent intent=new Intent();
//                intent.setClass(AActivity.this,BActivity.class);
//                startActivity(intent);

                //显式跳转3
//                Intent intent=new Intent();
//                intent.setClassName(AActivity.this,"com.example.basisproject.jump.BActivity");
//                startActivity(intent);

                //显式跳转4
//                Intent intent=new Intent();
//                intent.setComponent(new ComponentName(AActivity.this,"com.example.basisproject.jump.BActivity"));
//                startActivity(intent);

                //隐式调用
//                Intent intent=new Intent();
//                intent.setAction("com.example.basisproject.BActivity");
//                startActivity(intent);



            }
        });

//        mBtnJump2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AActivity.this,AActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    //接收BActivity 返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AActivity.this,data.getExtras().getString("title"),Toast.LENGTH_LONG).show();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.d("AActivity","onNewIntent");
//        Log.d("AActivity","taskid"+getTaskId()+" _hash"+hashCode());
//        logtaskName();
//    }

//    private void logtaskName(){
//        try {
//            ActivityInfo info=getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
//            Log.d("AActivity",info.taskAffinity);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
