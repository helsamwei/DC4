package com.hswei.dc4.Utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;

import com.hswei.dc4.BLE.BleList;
import com.hswei.dc4.BLE.iBeaconClass;

import java.util.List;

import static android.content.Context.BLUETOOTH_SERVICE;

public class BleUtil {

    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private BleList scanList = new BleList();
    private ScanSettings set;
    BluetoothLeScanner bleScanner;

    private static final BleUtil ourInstance=new BleUtil();

    public static BleUtil getInstance() {
        return ourInstance;
    }

    public void attachContext(Context context){
        mContext = context;
    }

    private BleUtil() {
    }

    public void startScan() {
        final BluetoothManager mBluetoothManager =(BluetoothManager) mContext.getApplicationContext()
                .getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        set = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();
        bleScanner = mBluetoothAdapter.getBluetoothLeScanner();
        bleScanner.startScan(null,set,scanCallback);
    }

    public void stopScan(){
        bleScanner.stopScan(scanCallback);
    }
    /**
     * Ble scan callback to receive the scan result
     */
    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            BluetoothDevice bleDevice = result.getDevice();
            int rssi = result.getRssi();
            byte[] scanData = result.getScanRecord().getBytes();
            final iBeaconClass.iBeacon ibeacon = iBeaconClass.fromScanData(bleDevice,rssi,scanData);
            scanList.addDevice(ibeacon);
        }
    };

    /**
     *
     * @return the scan iBeacon ArrayList
     */
    public BleList getScanResult(){
        return scanList;
    }

}
