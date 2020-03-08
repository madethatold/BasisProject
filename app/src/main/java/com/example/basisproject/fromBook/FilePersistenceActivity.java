package com.example.basisproject.fromBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilePersistenceActivity extends AppCompatActivity {

    private EditText ettest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_persistence);
        ettest1 = findViewById(R.id.et_test1);
        String inputText=load();
        ettest1.setText(inputText);
        ettest1.setSelection(inputText.length());
        ToastUtil.showMsg(FilePersistenceActivity.this,"success");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = ettest1.getText().toString();
        save(inputText);

    }

    //存数据
    public void save(String inputText) {
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            fos = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //加载数据
    public String load() {
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            fis = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fis));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
