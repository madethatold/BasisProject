package com.example.basisproject.fromBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.basisproject.R;
import com.example.basisproject.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private RecyclerView rvContacts;

    List<String> contactsnameList=new ArrayList<>();
    List<String> contactsnumList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        rvContacts=findViewById(R.id.rv_contacts);//找到RecyclerView控件
        rvContacts.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
        rvContacts.addItemDecoration(new MyDecoration());//添加分割线
        rvContacts.setAdapter(new ContactsAdapter(ContactsActivity.this));
        if (ContextCompat.checkSelfPermission(ContactsActivity.this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContactsActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContacts();
        }
    }

    private void readContacts()
    {
        Cursor cursor=null;
        try {
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String displayname=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String displaynum=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsnameList.add(displayname);
                    contactsnumList.add(displaynum);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null) cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    ToastUtil.showMsg(ContactsActivity.this,"u denied the permission");
                }
                break;
        }
    }


    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }

    public String getname(int p){
        return contactsnameList.get(p).toString();
    }

    public String getnum(int p){
        return contactsnumList.get(p).toString();
    }
}
