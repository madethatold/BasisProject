package com.example.basisproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basisproject.util.ToastUtil;

public class DialogActivity extends AppCompatActivity {
    private Button mbtndialog1,mbtndialog2,mbtndialog3,mbtndialog4,mbtndialog5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mbtndialog1=findViewById(R.id.btn_dialog1);
        mbtndialog2=findViewById(R.id.btn_dialog2);
        mbtndialog3=findViewById(R.id.btn_dialog3);
        mbtndialog4=findViewById(R.id.btn_dialog4);
        mbtndialog5=findViewById(R.id.btn_dialog5);

        OnCLick onCLick=new OnCLick();

        mbtndialog1.setOnClickListener(onCLick);
        mbtndialog2.setOnClickListener(onCLick);
        mbtndialog3.setOnClickListener(onCLick);
        mbtndialog4.setOnClickListener(onCLick);
        mbtndialog5.setOnClickListener(onCLick);
    }

    class OnCLick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_dialog1:
                    AlertDialog.Builder builder=new AlertDialog.Builder(DialogActivity.this);//实例化
                    builder.setTitle("please answer").setMessage("what about the Android?").setIcon(R.drawable.icon_love).setPositiveButton("nice", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"positive");
                        }
                    }).setNeutralButton("neutral", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"neutral");
                        }
                    }).setNegativeButton("bad", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"bad");
                        }
                    }).show();
                    break;
                case R.id.btn_dialog2:
                    final String[] arrays2=new String[]{"boy","girl"};
                    AlertDialog.Builder builder2=new AlertDialog.Builder(DialogActivity.this);
                    builder2.setTitle("choose gender").setIcon(R.drawable.icon_love).setItems(arrays2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,arrays2[which]);
                        }
                    }).show();
                    break;
                case R.id.btn_dialog3:
                    final String[] arrays3=new String[]{"boy","girl"};
                    AlertDialog.Builder builder3=new AlertDialog.Builder(DialogActivity.this);
                    builder3.setTitle("choose gender").setIcon(R.drawable.ic_launcher_background).setSingleChoiceItems(arrays3, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,arrays3[which]);
                            dialog.dismiss();
                        }
                    }).setCancelable(false).show();
                    break;
                case R.id.btn_dialog4:
                    final String[] arrays4=new String[]{"sing","swim","run","dance"};
                    final boolean[] isselected=new boolean[]{false,false,false,false};
                    AlertDialog.Builder builder4=new AlertDialog.Builder(DialogActivity.this);
                    builder4.setTitle("choose hobby").setMultiChoiceItems(arrays4, isselected, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            ToastUtil.showMsg(DialogActivity.this, arrays4[which] + ":" + isChecked);
                        }
                    }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"^-^" );
                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,":)" );
                        }
                    }).show();
                    break;
                case R.id.btn_dialog5:
                    AlertDialog.Builder builder5=new AlertDialog.Builder(DialogActivity.this);
                    View view= LayoutInflater.from(DialogActivity.this).inflate(R.layout.layout_dialog,null);
                    EditText username=view.findViewById(R.id.et_username);
                    EditText passwprd=view.findViewById(R.id.et_password);
                    Button btnLogin=view.findViewById(R.id.btn_login);
                    btnLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showMsg(DialogActivity.this,"success");

                        }
                    });
                    builder5.setTitle("please login firstly").setView(view).show();
                    break;


            }
        }
    }
}
