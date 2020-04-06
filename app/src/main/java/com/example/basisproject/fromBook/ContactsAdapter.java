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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contacts> contactsList;


    //构造方法
    public ContactsAdapter(List<Contacts> list){
        contactsList=list;
    }

    //负责承载每个子项的布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    //负责将每个子项holder绑定数据
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Contacts contacts=contactsList.get(position);
        holder.name.setText(contacts.name);
        holder.name.setText(contacts.num);

    }

    //返回控件数目
    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    //自定义ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,number;

        public ViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            number=itemView.findViewById(R.id.tv_number);
        }
    }



}
