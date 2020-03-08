package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.basisproject.util.ToastUtil;

public class PopupWindowActivity extends AppCompatActivity {
    private Button mBtnPop;
    private PopupWindow mPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mBtnPop=findViewById(R.id.btn_pop);

        mBtnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=getLayoutInflater().inflate(R.layout.layout_pop,null);//控件布置

                TextView textView=view.findViewById(R.id.tv_good);
                //点击文字效果
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPop.dismiss();
                        ToastUtil.showMsg(PopupWindowActivity.this,"good is clicked");
                    }
                });
                mPop=new PopupWindow(view,mBtnPop.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);//设置弹出的菜单宽度高度
                mPop.setFocusable(true);//重复点击按钮不多次弹出菜单栏
                mPop.setOutsideTouchable(true);//点击外部退出菜单
                mPop.showAsDropDown(mBtnPop);//弹出菜单栏
            }
        });
    }
}
