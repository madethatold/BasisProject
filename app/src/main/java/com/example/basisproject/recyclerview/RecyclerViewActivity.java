package com.example.basisproject.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.R;

public class RecyclerViewActivity extends AppCompatActivity {
    private Button mbtnLinear,mbtnHor,mbtnGrid,mbtnPu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mbtnLinear=findViewById(R.id.btn_linear);
        mbtnHor=findViewById(R.id.btn_hor);
        mbtnGrid=findViewById(R.id.btn_grid);
        mbtnPu=findViewById(R.id.btn_pu);

        mbtnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecyclerViewActivity.this,LinearRecyclerViewActivity.class);
                startActivity(intent);            }
        });
        mbtnHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecyclerViewActivity.this,HorRecyclerViewActivity.class);
                startActivity(intent);            }

        });
        mbtnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecyclerViewActivity.this,GridRecyclerViewActivity.class);
                startActivity(intent);            }
        });
        mbtnPu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecyclerViewActivity.this,PuRecyclerActivity.class);
                startActivity(intent);            }

        });


    }
}
