package com.example.basisproject.fromBook.login_offline;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

public class LoginOffLineActivity extends BaseActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCancel;
    private CheckBox cbRememberP;

    private SharedPreferences rSharedPreferences;
    private SharedPreferences.Editor rEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_off_line);

        btnCancel=findViewById(R.id.btn_cancel);
        btnLogin=findViewById(R.id.btn_login);
        etPassword=findViewById(R.id.et_password);
        etUsername=findViewById(R.id.et_username);
        cbRememberP=findViewById(R.id.cb_rememberp);

        rSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=rSharedPreferences.getBoolean("isremember",false);
        if (isRemember) {
            String username=rSharedPreferences.getString("username","");
            String password=rSharedPreferences.getString("password","");
            etUsername.setText(username);
            etPassword.setText(password);
            cbRememberP.setChecked(true);

        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                if(username.equals("lif")&&password.equals("123456")){
                    //检验复选框是否被选中
                    rEditor=rSharedPreferences.edit();
                    if(cbRememberP.isChecked()){
                        rEditor.putBoolean("isremember",true);
                        rEditor.putString("username",username);
                        rEditor.putString("password",password);
                    }else{
                        rEditor.clear();
                    }
                    rEditor.apply();

                    Intent intent=new Intent(LoginOffLineActivity.this,MymainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    ToastUtil.showMsg(LoginOffLineActivity.this,"username or password is invalid");
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
