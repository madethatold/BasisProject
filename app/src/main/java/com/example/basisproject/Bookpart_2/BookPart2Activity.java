package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basisproject.Bookpart_2.MaterialDesign.MaterialTestActivity;
import com.example.basisproject.MainActivity;
import com.example.basisproject.R;
import com.example.basisproject.fromBook.multimedia.CameraAlbumActivity;

public class BookPart2Activity extends AppCompatActivity {
    private Button mBtnCamera,mBtnNetWork,mBtnTheadTest,mBtnService,mBtnDownload,mBtnPosition,mBtnMaterialD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_part2);
        mBtnCamera=findViewById(R.id.btn_camera);
        mBtnNetWork=findViewById(R.id.btn_network);
        mBtnTheadTest=findViewById(R.id.btn_threadtest);
        mBtnService=findViewById(R.id.btn_service);
        mBtnDownload=findViewById(R.id.btn_download);
        mBtnPosition=findViewById(R.id.btn_position);
        mBtnMaterialD=findViewById(R.id.btn_materialD);

        OnClick onclick=new OnClick();

        mBtnCamera.setOnClickListener(onclick);
        mBtnNetWork.setOnClickListener(onclick);
        mBtnTheadTest.setOnClickListener(onclick);
        mBtnService.setOnClickListener(onclick);
        mBtnDownload.setOnClickListener(onclick);
        mBtnPosition.setOnClickListener(onclick);
        mBtnMaterialD.setOnClickListener(onclick);
    }
    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_camera:
                    intent = new Intent(BookPart2Activity.this, CameraAlbumActivity.class);
                    break;
                case R.id.btn_network:
                    intent = new Intent(BookPart2Activity.this, NetWorkTestActivity.class);
                    break;
                case R.id.btn_threadtest:
                    intent = new Intent(BookPart2Activity.this, ThreadActivity.class);
                    break;
                case R.id.btn_service:
                    intent = new Intent(BookPart2Activity.this, ServiceTestActivity.class);
                    break;
                case R.id.btn_download:
                    intent = new Intent(BookPart2Activity.this, DownloadActivity.class);
                    break;
                case R.id.btn_position:
                    intent = new Intent(BookPart2Activity.this, LBSActivity.class);
                    break;
                case R.id.btn_materialD:
                    intent = new Intent(BookPart2Activity.this, MaterialTestActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
