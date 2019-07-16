package com.hswei.dc4.Scan;

import android.content.Context;

import com.hswei.dc4.Utils.BleUtil;
import com.hswei.dc4.Utils.ImuUtil;
import com.hswei.dc4.Utils.ScanResultUtil;
import com.hswei.dc4.Utils.WifiUtil;

import java.util.Timer;

public class Scan {

    private Timer wsnTimer,sensorTimer;
    private WsnScanTask wsnTimerTask;
    private ImuScanTask sensorTimerTask;
    private int wsnFrq = 2;
    private int sensorFrq = 50;
    private Context mContext;

    private static final Scan ourInstance = new Scan();

    public static Scan getInstance() {
        return ourInstance;
    }

    private Scan() {
    }

    public void init(Context context){
        BleUtil.getInstance().attachContext(context);
        WifiUtil.attachContext(context);
        mContext = context;
        ScanResultUtil.getInstance().setLength0();
    }

    public void startWsnScan(){
        //start WSN scan
        int wsnTimeindex = 1000/wsnFrq;
        Timer wsnTimer = new Timer();
        WsnScanTask wsnTimerTask = new WsnScanTask(wsnTimer);
        wsnTimer.scheduleAtFixedRate(wsnTimerTask,0,wsnTimeindex);
    }

    public void startImuScan(){
        int sensorTimerindex = 1000/sensorFrq;
        ImuUtil.getInstance().registerSensor(mContext);
        Timer sensorTimer = new Timer();
        ImuScanTask sensorTimerTask = new ImuScanTask(sensorTimer);
        sensorTimer.scheduleAtFixedRate(sensorTimerTask,0,sensorTimerindex);
    }
}
