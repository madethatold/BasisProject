package com.example.basisproject.fromBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basisproject.R;

public class MyDataBaseActivity extends AppCompatActivity implements View.OnClickListener{

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_base);
        initView();
    }
    public void initView(){
        Button insert =findViewById(R.id.btn_insert_button);
        Button insert_cleardata = findViewById(R.id.btn_clear_insert_button);

        Button update =  findViewById(R.id.btn_update_button);
        Button update_cleardata = findViewById(R.id.btn_clear_update_button);

        Button delete =  findViewById(R.id.btn_delete_button);
        Button delete_cleardata = findViewById(R.id.btn_clear_delete_button);

        Button query =  findViewById(R.id.btn_query);
        Button clearquery = findViewById(R.id.btn_clear_query);

        insert.setOnClickListener(this);
        insert_cleardata.setOnClickListener(this);

        update.setOnClickListener(this);
        update_cleardata.setOnClickListener(this);

        delete.setOnClickListener(this);
        delete_cleardata.setOnClickListener(this);

        query.setOnClickListener(this);
        clearquery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText insert_text = findViewById(R.id.et_insert_text);
        String insert_data = insert_text.getText().toString();

        EditText delete_text = findViewById(R.id.et_delete_text);
        String delete_data = delete_text.getText().toString();

        EditText update_before_text = findViewById(R.id.et_update_before_text);
        String update_before_data = update_before_text.getText().toString();
        EditText update_after_text = findViewById(R.id.et_update_after_text);
        String update_after_data = update_after_text.getText().toString();

        TextView textView = findViewById(R.id.tv_query_text);

        //新建了一个test_db的数据库
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this,"test_db",null,1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        switch (v.getId()){
            case R.id.btn_insert_button:
                ContentValues values = new ContentValues();
                values.put("name",insert_data);
                db.insert("user",null,values);
                break;
            case R.id.btn_clear_insert_button:
                insert_text.setText("");
                break;
            case R.id.btn_delete_button:
                db.delete("user","name=?",new String[]{delete_data});
                break;
            case R.id.btn_clear_delete_button:
                delete_text.setText("");
                break;
            //更新数据按钮
            case R.id.btn_update_button:
                ContentValues values2 = new ContentValues();
                values2.put("name", update_after_data);
                db.update("user", values2, "name = ?", new String[]{update_before_data});
                break;
            //更新数据按钮后面的清除按钮
            case R.id.btn_clear_update_button:
                update_before_text.setText("");
                update_after_text.setText("");
                break;

            //查询全部按钮
            case R.id.btn_query:
                //创建游标对象
                Cursor cursor = db.query("user", new String[]{"name"}, null, null, null, null, null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                String textview_data = "";
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    textview_data = textview_data + "\n" + name;
                }
                textView.setText(textview_data);
                break;
            //查询全部按钮下面的清除查询按钮
            case R.id.btn_clear_query:
                textView.setText("");
                break;

            default:
                break;
        }
    }
}
