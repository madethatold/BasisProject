package com.example.basisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basisproject.util.ToastUtil;

public class ToastActivity extends AppCompatActivity {
    private Button mbtnToast1,mbtnToast2,mbtnToast3,mbtnToast4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        mbtnToast1=findViewById(R.id.btn_toast_1);
        mbtnToast2=findViewById(R.id.btn_toast_2);
        mbtnToast3=findViewById(R.id.btn_toast_3);
        mbtnToast4=findViewById(R.id.btn_toast_4);

        Onclick onclick=new Onclick();

        mbtnToast1.setOnClickListener(onclick);
        mbtnToast2.setOnClickListener(onclick);
        mbtnToast3.setOnClickListener(onclick);
        mbtnToast4.setOnClickListener(onclick);

    }
    class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_toast_1:
                    Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_LONG).show();
                    break;
                case R.id.btn_toast_2:
                    Toast toastCenter=Toast.makeText(getApplicationContext(),"居中Toast",Toast.LENGTH_LONG);
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    toastCenter.show();
                    break;
                case R.id.btn_toast_3:
                    Toast toastCustom=new Toast(getApplicationContext());
                    LayoutInflater inflater=LayoutInflater.from(ToastActivity.this);
                    View view=inflater.inflate(R.layout.layout_toast,null);
                    ImageView imageView=view.findViewById(R.id.iv_toast);
                    TextView textView=view.findViewById(R.id.tv_toast);
                    imageView.setImageResource(R.drawable.icon_love);
                    textView.setText("myToast");
                    toastCustom.setView(view);
                    toastCustom.setDuration(Toast.LENGTH_LONG);
                    toastCustom.show();
                    break;
                case R.id.btn_toast_4:
                    ToastUtil.showMsg(getApplicationContext(),"包装过的Toast");
                    break;
            }
        }
    }
}
