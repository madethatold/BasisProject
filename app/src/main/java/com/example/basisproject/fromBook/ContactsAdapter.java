package com.example.basisproject.fromBook;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    ContactsActivity contactsActivity=new ContactsActivity();



    //构造方法
    public ContactsAdapter(Context context){
        this.mContext=context;
    }

    //负责承载每个子项的布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_contacts_item,parent,false));
    }

    //负责将每个子项holder绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((LinearViewHolder)holder).name.setText(contactsActivity.getname(position));
        ((LinearViewHolder)holder).number.setText(contactsActivity.getnum(position));
    }

    //返回控件数目
    @Override
    public int getItemCount() {
        return 30;
    }

    //自定义ViewHolder
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView name,number;

        public LinearViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            number=itemView.findViewById(R.id.tv_number);
        }
    }



}
