package com.example.basisproject.Bookpart_2.MaterialDesign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basisproject.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter <FruitAdapter.ViewHolder>{

    private Context context;
    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            cardView= (CardView) view;
            imageView=view.findViewById(R.id.fruit_image);
            textView=view.findViewById(R.id.tv_name_fruit);
        }
    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList=fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent(context,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.textView.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
