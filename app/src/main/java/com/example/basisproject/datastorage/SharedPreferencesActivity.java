package com.example.basisproject.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basisproject.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText metName;
    private Button mbtnSave,mbtnShow;
    private TextView mtvContent;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        metName=findViewById(R.id.et_name);
        mbtnSave=findViewById(R.id.btn_save);
        mbtnShow=findViewById(R.id.btn_show);
        mtvContent=findViewById(R.id.tv_show);

        //存
        mSharedPreferences=getSharedPreferences("anything",MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();

        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("name",metName.getText().toString());
                mEditor.apply();
            }
        });
        mbtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtvContent.setText(mSharedPreferences.getString("name",""));
            }
        });
    }
}
