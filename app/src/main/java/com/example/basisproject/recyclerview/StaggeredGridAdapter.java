package com.example.basisproject.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basisproject.R;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.LinearViewHolder> {
    private Context mContext;

    //构造方法
    public StaggeredGridAdapter(Context context){
        this.mContext=context;
    }

    @NonNull
    @Override
    //负责承载每个子项的布局
    public StaggeredGridAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_staggeredgrid_item,parent,false));
    }

    @Override
    //负责将每个子项holder绑定数据
    public void onBindViewHolder(@NonNull StaggeredGridAdapter.LinearViewHolder holder, final int position) {

        if(position%2!=0){
            holder.imageView.setImageResource(R.drawable.pic2);
        }else {
            holder.imageView.setImageResource(R.drawable.pic1);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click...."+position,Toast.LENGTH_SHORT).show();
            }
        });//item的点击事件
    }

    @Override
    //返回控件数目
    public int getItemCount() {
        return 30;
    }

    //自定义ViewHolder
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public LinearViewHolder(View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.iv);

        }
    }
}
