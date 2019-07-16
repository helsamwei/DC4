package com.hswei.dc4.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.hswei.dc4.Fragment.DynamicscanFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SavaDataAsyn extends AsyncTask<Void,Void,Void> {

    private boolean saveWifi=false,saveBle=false,saveImu=false;
    private Context mContext;

    public SavaDataAsyn(Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        FileUtil fileUtil = FileUtil.getInstance();
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(currentTime);
        String wifiRes = ScanResultUtil.getInstance().getWifiRes();
        String bleRes = ScanResultUtil.getInstance().getBleRes();
        String imuRes = ScanResultUtil.getInstance().getImuRes();

        switch (ScanResultUtil.scanMode){
            case 1:
               saveWifi = fileUtil.saveData(ConstantsUtil.DATA_PATH_STATIC + "WIFI-Device" + ScanResultUtil.dev
                            + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                            + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",wifiRes)&&(!wifiRes.isEmpty());

               saveBle = fileUtil.saveData(ConstantsUtil.DATA_PATH_STATIC + "BLE-Device" + ScanResultUtil.dev
                       + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                       + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",bleRes)&&(!bleRes.isEmpty());

               saveImu = fileUtil.saveData(ConstantsUtil.DATA_PATH_STATIC + "IMU-Device" + ScanResultUtil.dev
                       + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                       + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",imuRes)&&(!imuRes.isEmpty());
               break;
            case 2:
                saveWifi = fileUtil.saveData(ConstantsUtil.DATA_PATH_DYNAMIC + "WIFI-Device" + ScanResultUtil.dev
                        + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                        + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",wifiRes)&&(!wifiRes.isEmpty());

                saveBle = fileUtil.saveData(ConstantsUtil.DATA_PATH_DYNAMIC + "BLE-Device" + ScanResultUtil.dev
                        + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                        + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",bleRes)&&(!bleRes.isEmpty());

                saveImu = fileUtil.saveData(ConstantsUtil.DATA_PATH_DYNAMIC + "IMU-Device" + ScanResultUtil.dev
                        + "-" + "RP(" + ScanResultUtil.x + "," + ScanResultUtil.y + "," + ScanResultUtil.z + ")-head"
                        + "-" + ScanResultUtil.head + "-" + format.format(date) + ".txt",imuRes)&&(!imuRes.isEmpty());
                DynamicscanFragment.isFinished = false;
                break;

        }

        ScanResultUtil.getInstance().setLength0();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(saveWifi&&saveBle&&saveImu){
            Toast.makeText(mContext,"Successful",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(mContext,"Failed",Toast.LENGTH_LONG).show();
        }
    }
}
