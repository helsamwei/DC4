package com.hswei.dc4.Scan;

import android.net.wifi.ScanResult;

import com.hswei.dc4.BLE.BleList;
import com.hswei.dc4.BLE.iBeaconClass;
import com.hswei.dc4.Fragment.DynamicscanFragment;
import com.hswei.dc4.Fragment.StaticscanFragment;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.Utils.BleUtil;
import com.hswei.dc4.Utils.SavaDataAsyn;
import com.hswei.dc4.Utils.ScanResultUtil;
import com.hswei.dc4.Utils.WifiUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WsnScanTask extends TimerTask {

    private int countNum = 0;
    private long currentTime;
    private ScanResult mScanResult;
    private iBeaconClass.iBeacon ibeacon;
    private List<ScanResult> wifiScanList;
    private BleList bleScanList;
    private Timer mTimer;

    public WsnScanTask(Timer timer){
        mTimer = timer;
    }

    @Override
    public void run() {
        countNum++;
        currentTime = System.currentTimeMillis();

        /**
         * start wifi scan
         */
        WifiUtil.startScan();
        wifiScanList = WifiUtil.getScanResultList();
        for(int i=0;i<wifiScanList.size();i++){
            mScanResult = wifiScanList.get(i);
            ScanResultUtil.getInstance().appendixWifiRes(mScanResult,currentTime);
        }
        wifiScanList.clear();
        /**
         * start ble scan
         */
        BleUtil.getInstance().startScan();
        bleScanList = BleUtil.getInstance().getScanResult();
        for(int i=0;i<bleScanList.getCount();i++){
            ibeacon = bleScanList.getDevice(i);
            ScanResultUtil.getInstance().appendixBleRes(ibeacon,currentTime);
        }

        switch (ScanResultUtil.scanMode){
            case 1:
                if(countNum>=ScanResultUtil.dur*2*60){
                    mTimer.cancel();
                    BleUtil.getInstance().stopScan();
                    bleScanList.clear();
                    StaticscanFragment.Clock.stop();
                    StaticscanFragment.dialog.dismiss();
                    new SavaDataAsyn(MainActivity.mainActivity).execute();
                }
                break;
            case 2:
                if(DynamicscanFragment.isFinished){
                    mTimer.cancel();
                    BleUtil.getInstance().stopScan();
                    bleScanList.clear();
                    DynamicscanFragment.Clock.stop();
                    new SavaDataAsyn(MainActivity.mainActivity).execute();
                }
                break;
        }
    }


}
