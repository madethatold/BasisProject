package com.example.basisproject.Bookpart_2.MaterialDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.basisproject.MainActivity;
import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

public class MaterialTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_test);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //重写该方法  给当前活动创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;//允许显示
    }

    //重写该方法  给每个菜单项加入逻辑处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                ToastUtil.showMsg(MaterialTestActivity.this,"refresh");
                break;
            case R.id.heart:
                ToastUtil.showMsg(MaterialTestActivity.this,"remove");
                break;
        }
        return true;
    }
}
