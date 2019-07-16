package com.hswei.dc4.Utils;

import android.net.wifi.ScanResult;

import com.hswei.dc4.BLE.iBeaconClass;

public class ScanResultUtil {

    public static int dev,scanMode;
    public static char head;
    public static float x,y,z,dur;

    /**
     * scan result
     */
    private StringBuilder wifiRes = new StringBuilder();
    private StringBuilder bleRes = new StringBuilder();
    private StringBuilder imuRes = new StringBuilder();

    private static final ScanResultUtil ourInstance=new ScanResultUtil();

    public static ScanResultUtil getInstance() {
        return ourInstance;
    }

    private ScanResultUtil() {
    }

    public void setLength0(){
        wifiRes.setLength(0);
        bleRes.setLength(0);
        imuRes.setLength(0);
    }

    public void appendixWifiRes(ScanResult mScanResult,long currentTime){
        wifiRes.append(currentTime+"\t").append(dev+"\t").append(head+"\t").append(x+"\t")
                .append(y+"\t").append(z+"\t").append(mScanResult.BSSID+"\t").append(mScanResult.SSID)
                .append(mScanResult.level+"\t").append("\r\n");
    }

    public void appendixBleRes(iBeaconClass.iBeacon mScanResult, long currentTime){
        bleRes.append(currentTime+"\t").append(dev+"\t").append(head+"\t").append(x+"\t")
                .append(y+"\t").append(z+"\t").append(mScanResult.minor+"\t").append(mScanResult.major)
                .append(mScanResult.rssi+"\t").append("\r\n");
    }

    public void appendixImuRes(ImuUtil imuUtil, long currentTime){
        imuRes.append(currentTime+"\t").append(dev+"\t").append(head+"\t").append(x+"\t")
                .append(y+"\t").append(z+"\t").append(imuUtil.getAcc_x()+"\t").append(imuUtil.getAcc_y()+"\t")
                .append(imuUtil.getAcc_z()+"\t").append(imuUtil.getGyro_x()+"\t").append(imuUtil.getGyro_y()+"\t")
                .append(imuUtil.getGyro_z()+"\t").append(imuUtil.getMag_x()+"\t").append(imuUtil.getMag_y()+"\t")
                .append(imuUtil.getMag_z()+"\t").append("\r\n");
    }

    public String getWifiRes() {
        return wifiRes.toString();
    }

    public String getBleRes() {
        return bleRes.toString();
    }

    public String getImuRes() {
        return imuRes.toString();
    }
}
