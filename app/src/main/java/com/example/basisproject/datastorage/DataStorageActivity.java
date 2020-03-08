package com.example.basisproject.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class DataStorageActivity extends AppCompatActivity {

    private Button mBtnSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        mBtnSharedPreferences=findViewById(R.id.btn_sharedpreferences);
        mBtnSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataStorageActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });
    }
}
