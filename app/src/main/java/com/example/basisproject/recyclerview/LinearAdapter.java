package com.example.basisproject.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basisproject.R;

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    //构造方法
    public LinearAdapter(Context context){
        this.mContext=context;
    }

    @NonNull
    @Override
    //负责承载每个子项的布局
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
        }else{
            return new LinearViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item_2,parent,false));
        }

    }

    //负责将每个子项holder绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(getItemViewType(position)==0){
            ((LinearViewHolder)holder).textView.setText("Hello World!");//偶数位置设置文本
        }else{
            ((LinearViewHolder2)holder).textView.setText("Hello!");//奇数位置文本
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click...."+position,Toast.LENGTH_SHORT).show();
            }
        });//item的点击事件

    }

    //奇偶数位置区别
    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return 0;
        }else {
            return 1;
        }
    }

    //返回控件数目
    @Override
    public int getItemCount() {
        return 30;
    }

    //自定义ViewHolder1
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public LinearViewHolder(View itemView){
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);//文本

        }
    }

    //自定义ViewHolder2
    class LinearViewHolder2 extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public LinearViewHolder2(View itemView){
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);//文本
            imageView=itemView.findViewById(R.id.iv_image);//图

        }
    }
}
