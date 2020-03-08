package com.example.basisproject.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.example.basisproject.R;

public class HorRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRvHor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_recycler_view);
        mRvHor=findViewById(R.id.rv_hor);//找到RecyclerView控件

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(HorRecyclerViewActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);//额外设置view方向
        mRvHor.setLayoutManager(linearLayoutManager);
        mRvHor.addItemDecoration(new MyDecoration());//添加分割线
        mRvHor.setAdapter(new HorAdapter(HorRecyclerViewActivity.this));


    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight2),0);
        }
    }
}
