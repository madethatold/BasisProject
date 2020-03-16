package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.basisproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkTestActivity extends AppCompatActivity {
    TextView tvresponse;
    private Button btnSendR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_test);
        btnSendR=findViewById(R.id.btn_sendrequest);
        tvresponse=findViewById(R.id.tv_response);

        btnSendR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });
    }

    private void sendRequestWithOkHttp() {
        /*开启线程来发起网络请求*/
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient client = new OkHttpClient();//实例化
                    Request request = new Request.Builder()//创建Request实例，用以发起Http请求
                            .url("https://www.hao123.com")//目标的网络地址
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String responsedata) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvresponse.setText(responsedata);
            }
        });
    }


}


