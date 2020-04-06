package com.example.basisproject.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.basisproject.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

public class LitePalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        Button btnCreateDataBase=findViewById(R.id.btn_create_database);
        btnCreateDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        Button insert_name =findViewById(R.id.btn_insert_name);

        Button update_name =  findViewById(R.id.btn_update_button_name);

        Button clear_insert =  findViewById(R.id.btn_clear_insert);
        Button clear_update_name = findViewById(R.id.btn_clear_update_button_name);

        Button query=findViewById(R.id.btn_query_litepal);
        Button clear_query=findViewById(R.id.btn_clear_query_litepal);



        insert_name.setOnClickListener(this);

        update_name.setOnClickListener(this);

        clear_insert.setOnClickListener(this);
        clear_update_name.setOnClickListener(this);

        query.setOnClickListener(this);
        clear_query.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        EditText insert_name = findViewById(R.id.et_insert_name);
        String insertname = insert_name.getText().toString();

        EditText name_before = findViewById(R.id.et_update_before_name);
        String namebefore = name_before.getText().toString();
        EditText name_after = findViewById(R.id.et_update_after_name);
        String nameafter = name_after.getText().toString();

        TextView result = findViewById(R.id.tv_result);


        Book book=new Book();

        switch (v.getId()){
            case R.id.btn_insert_name:
                book.setName(insertname);
                book.save();
                break;
            case R.id.btn_update_button_name:
                book.setName(nameafter);
                book.updateAll("name=?",namebefore);
                break;
            case R.id.btn_clear_insert:
                insert_name.setText("");
                break;
            case R.id.btn_clear_update_button_name:
                name_before.setText("");
                name_after.setText("");
                break;

            case R.id.btn_query_litepal:
                List<Book> books=LitePal.findAll(Book.class);
                for(Book book1:books){
                    result.setText(book1.getName()+"");
                }
                break;
            case R.id.btn_clear_query_litepal:
                result.setText("");
                break;


            default:
                break;
        }
    }

}
