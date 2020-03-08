package com.example.basisproject.fromBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;
import com.example.basisproject.widgt.CustomDialog;

public class RuntimePermissionActivity extends AppCompatActivity {

    private Button mbtnMakeCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);
        mbtnMakeCall=findViewById(R.id.btn_makecall);
        mbtnMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(RuntimePermissionActivity.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RuntimePermissionActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }
            }
        });
    }
    private void call(){
        try {
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:13363536772"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    ToastUtil.showMsg(RuntimePermissionActivity.this,"u denied the permission");
                }
                break;
        }
    }
}
