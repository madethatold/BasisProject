package com.example.basisproject.Bookpart_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.basisproject.R;

import java.util.ArrayList;
import java.util.List;

public class LBSActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private TextView tvPosition;
    private MapView mapView;

    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*设置一个初始化方式，传入一个全局的context参数*/
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_lbs);

        tvPosition=findViewById(R.id.tv_position);

        mLocationClient = new LocationClient(getApplicationContext());//创建实例，传入全局context参数
        /*注册一个定位监听器，当获取到位置信息后回调这定位监听器*/
        mLocationClient.registerLocationListener(new MyLocationListener());
        /*当获取到位置信息时，就会回调这个定位监听器*/

         mapView=findViewById(R.id.mv_mapview);
         baiduMap=mapView.getMap();
         baiduMap.setMyLocationEnabled(true);

        /*因为申请的权限中有三个权限需要对用户进行询问，在这里进行一次性授权
         * 1.新建一个List集合，依次判断3个权限有没有授权，如果没有加入到list中
         * 2.将list转化为数组，调用ActivityCompat.requestPermissions（）方法一次性申请*/
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LBSActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LBSActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LBSActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        /*如果申请权限列表不为空，则申请权限列表中的权限，否则直接进行返回位置信息方法requestLocation()*/
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LBSActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    /*定位的具体方法，具体的定位结果会回调到监听器中。*/
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000); //设置更新的间隔--5s
        option.setIsNeedAddress(true);//需要获取详细的地址信息
        mLocationClient.setLocOption(option);
    }

    /*在活动被销毁的时候要关闭定位，不然会在后台一直定位，耗费电量。*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    /*申请权限的回调函数*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        switch (requestCode) {
            case 1:
                if (grantResult.length > 0) {
                    /*采用一个循环将每个申请的权限进行判断*/
                    for (int result:grantResult){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用",
                                    Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(LBSActivity.this,"发送未知错误",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    /*组装一个字符串，加入获取经度和纬度，加入定位方式，在textview中显示*/
                    StringBuilder currentPosition = new StringBuilder();
                    /*获取经度和纬度*/
                    currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
                    currentPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
                    currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
                    currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
                    currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
                    currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
                    currentPosition.append("定位方式：");
                    if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                        currentPosition.append("网络");
                    }
                    tvPosition.setText(currentPosition);
                }
            });


            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    private void navigateTo(BDLocation location){

        if(isFirstLocate){
            LatLng latLng=new LatLng(location.getLatitude(), location.getLatitude());//存放位置经纬度
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);//缩放级别
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData locationData=builder.build();
        baiduMap.setMyLocationData(locationData);
    }

}
