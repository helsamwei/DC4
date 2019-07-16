package com.hswei.dc4.Utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;

public class WifiUtil {

    private static Context mContext;
    private static WifiManager mWifiManager;
    private static List<ScanResult> scanResultList;

    private static final WifiUtil ourInstance=new WifiUtil();

    public static WifiUtil getInstance() {
        return ourInstance;
    }

    private WifiUtil() {
    }

    public static void attachContext(Context context){
        mContext = context;
        mWifiManager =(WifiManager) mContext.getApplicationContext().getSystemService(WIFI_SERVICE);
    }

    public static void startScan(){
        mWifiManager.startScan();
        scanResultList = mWifiManager.getScanResults();
    }

    /**
     *
     * @return Wi-Fi scan result
     */
    public static List<ScanResult> getScanResultList() {
        return scanResultList;
    }
}
