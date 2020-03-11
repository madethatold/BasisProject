package com.example.basisproject.Bookpart_2.MaterialDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent=getIntent();
        String fruitname=intent.getStringExtra(FRUIT_NAME);
        int fruitimageid=intent.getIntExtra(FRUIT_IMAGE_ID,0);

        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);

        TextView fruittext=findViewById(R.id.fruit_content_text);
        ImageView fruitimage=findViewById(R.id.iv_fruit);

        //设置toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //状态栏返回键
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout.setTitle(fruitname);
        Glide.with(this).load(fruitimageid).into(fruitimage);
        String fruitContent=generateFruitContent(fruitname);
        fruittext.setText(fruitContent);

        FloatingActionButton fab=findViewById(R.id.fab_like);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar方式
                Snackbar.make(v,"likes",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(FruitActivity.this,"unlilkes");
                    }
                }).show();
            }
        });

    }
    private String generateFruitContent(String fruitName){
        StringBuilder fruitContent=new StringBuilder();
        for (int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
