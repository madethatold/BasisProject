package com.example.basisproject.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class DataStorageActivity extends AppCompatActivity {

    private Button mBtnSharedPreferences,mBtnfileSave,mBtnLitePal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        mBtnSharedPreferences=findViewById(R.id.btn_sharedpreferences);
        mBtnfileSave=findViewById(R.id.btn_filesave);
        mBtnLitePal=findViewById(R.id.btn_litepal);
        mBtnSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataStorageActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });
        mBtnfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataStorageActivity.this, FileSaveActivity.class);
                startActivity(intent);
            }
        });
        mBtnLitePal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataStorageActivity.this, LitePalActivity.class);
                startActivity(intent);
            }
        });

    }
}
