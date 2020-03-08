package com.example.basisproject.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.basisproject.R;

public class ContainerActivity extends AppCompatActivity implements AFragment .IOnMessageClick{
    private AFragment aFragment;
//    private BFragment bFragment;
//    private Button mBtnChange;
    private TextView mtvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        //实例化AFragment
        aFragment=new AFragment().newInstance("2019_ncov");//用newInstance传参数__Activity传数据
        //把AFragment添加到Activity中
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,aFragment).commitAllowingStateLoss();//将布局文件加载到Activity布局管理器


//        mBtnChange=findViewById(R.id.btn_change);
//        mBtnChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(bFragment==null){
//                    bFragment=new BFragment();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).commitAllowingStateLoss();//替换原本的Fragment
//            }
//        });


        mtvTitle=findViewById(R.id.tv_title);


    }

    //提供公共方法 便于fragment调用此方法设置activity中的文字
    public void setData(String text){
        mtvTitle.setText(text);
    }

    @Override
    public void onClick(String text) {
        mtvTitle.setText(text);
    }
}
