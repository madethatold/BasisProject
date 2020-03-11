package com.example.basisproject.Bookpart_2.MaterialDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.basisproject.MainActivity;
import com.example.basisproject.R;
import com.example.basisproject.WebViewActivity;
import com.example.basisproject.util.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialTestActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fruit[] fruits={new Fruit("straw",R.drawable.straw),new Fruit("orange",R.drawable.orange)
            ,new Fruit("banana",R.drawable.banana),new Fruit("cherry",R.drawable.cherry)};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_test);

        drawerLayout=findViewById(R.id.drawer_layout);

        //设置标题栏toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_material);
        setSupportActionBar(toolbar);

        //设置标题栏默认返回键，默认返回上一级
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        }

        //设置滑出菜单栏操作
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_setting);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_international:
                        Intent intent=new Intent(MaterialTestActivity.this, WebViewActivity.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //设置浮动按钮
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar方式
                Snackbar.make(v,"ok",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showMsg(MaterialTestActivity.this,"no");
                    }
                }).show();
            }
        });

        initfruits();


        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);//设置刷新进度条的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshfruits();
            }
        });

    }

    //重写该方法  给当前活动创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;//允许显示
    }

    //重写该方法  给每个菜单项加入逻辑处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                ToastUtil.showMsg(MaterialTestActivity.this,"refresh");
                break;
            case R.id.heart:
                ToastUtil.showMsg(MaterialTestActivity.this,"remove");
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                ToastUtil.showMsg(MaterialTestActivity.this,"open");

        }
        return true;
    }

    //水果List初始化
    public void initfruits(){
        fruitList.clear();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void refreshfruits(){
        //新的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initfruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
