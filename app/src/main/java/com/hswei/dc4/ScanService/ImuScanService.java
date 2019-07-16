package com.hswei.dc4.ScanService;

import android.app.IntentService;
import android.content.Intent;

import com.hswei.dc4.MainActivity;
import com.hswei.dc4.Scan.Scan;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ImuScanService extends IntentService {


    public ImuScanService() {
        super("ImuScanService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Scan.getInstance().init(MainActivity.mainActivity);
        Scan.getInstance().startImuScan();
    }

}
