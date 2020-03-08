package com.example.basisproject.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basisproject.R;

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.LinearViewHolder> {
    private Context mContext;

    //构造方法
    public HorAdapter(Context context){
        this.mContext=context;
    }

    @NonNull
    @Override
    //负责承载每个子项的布局
    public HorAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_hor_item,parent,false));
    }

    @Override
    //负责将每个子项holder绑定数据
    public void onBindViewHolder(@NonNull HorAdapter.LinearViewHolder holder, final int position) {

        holder.textView.setText("Hello!");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click...."+position,Toast.LENGTH_SHORT).show();
            }
        });//item点击事件
    }

    @Override
    //返回控件数目
    public int getItemCount() {
        return 30;
    }

    //自定义ViewHolder
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public LinearViewHolder(View itemView){
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);

        }
    }
}
