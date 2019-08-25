package com.hswei.dc4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Toolbar;


import com.hswei.dc4.Utils.ConstantsUtil;
import com.hswei.dc4.Utils.ScreenControllerUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;
    private List<String> permissionList = new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        createPath();
        checkPermission();

        ScreenControllerUtil.getInstance().openScreen(ScreenControllerUtil.Screen.STATIC_SCAN);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void createPath() {

        File file_sta = new File(ConstantsUtil.DATA_PATH_STATIC);
        File file_dyn = new File(ConstantsUtil.DATA_PATH_DYNAMIC);
        if(!file_sta.exists() || !file_dyn.exists()){
            file_sta.mkdirs();
            file_dyn.mkdirs();
        }

    }

    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CHANGE_WIFI_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CHANGE_WIFI_STATE);
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_ADMIN)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.BLUETOOTH_ADMIN);
        }

        if(permissionList.isEmpty()){
            Toast.makeText(this,"All the Permission is Allowed",Toast.LENGTH_LONG).show();
        }else{
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,1001);
        }
    }

    private void setToolConfig(){
        mToolbar = findViewById(R.id.toolbar);

    }
}
