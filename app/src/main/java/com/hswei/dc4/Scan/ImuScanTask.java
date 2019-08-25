package com.hswei.dc4.Scan;

import com.hswei.dc4.Fragment.DynamicscanFragment;
import com.hswei.dc4.Fragment.StaticscanFragment;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.Utils.BleUtil;
import com.hswei.dc4.Utils.ImuUtil;
import com.hswei.dc4.Utils.SavaDataAsyn;
import com.hswei.dc4.Utils.ScanResultUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ImuScanTask extends TimerTask {

    private int countNum = 0;
    private long currentTime;
    private Timer mTimer;

    public ImuScanTask(Timer timer){
        mTimer = timer;
    }

    public ImuScanTask() {
    }

    @Override
    public void run() {
        countNum++;
        currentTime = System.currentTimeMillis();

        ScanResultUtil.getInstance().appendixImuRes(ImuUtil.getInstance(),currentTime);
        switch (ScanResultUtil.scanMode){
            case 1:
                if(countNum>=ScanResultUtil.dur*50*60){
                    mTimer.cancel();
//                    Scan.getInstance().stopScan();
                }
                break;
            case 2:
                if(DynamicscanFragment.isFinished){
                    mTimer.cancel();
//                    Scan.getInstance().stopScan();
                }
                break;
        }
    }
}
