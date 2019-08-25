package com.hswei.dc4.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hswei.dc4.Scan.Scan;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.R;
import com.hswei.dc4.Utils.ScanResultUtil;
import com.hswei.dc4.Utils.ScreenControllerUtil;

public class DynamicscanFragment extends Fragment {

    private EditText Dev,Head,inputX,inputY,inputZ;
    public static Chronometer Clock;
    private ImageView Start,Setting;
    public AlertDialog.Builder dialog;
    private int SCAN_DYNAMIC = 2;
    public static boolean isFinished = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dyn_scan_fragment,container,false);
        Dev = view.findViewById(R.id.dev_dyn);
        Head = view.findViewById(R.id.head_dyn);
        inputX = view.findViewById(R.id.inputX_dyn);
        inputY = view.findViewById(R.id.inputY_dyn);
        inputZ = view.findViewById(R.id.inputZ_dyn);
        Clock = view.findViewById(R.id.clock_dyn);
        Start = view.findViewById(R.id.btn_start_dyn);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInputCompleted()){
                    Clock.setBase(SystemClock.elapsedRealtime());
                    Clock.start();
                    waitDialog();

                    ScanResultUtil.scanMode = SCAN_DYNAMIC;
                    ScanResultUtil.dev = Integer.parseInt(Dev.getText().toString());
                    ScanResultUtil.head = Head.getText().toString().charAt(0);
                    ScanResultUtil.x = Float.parseFloat(inputX.getText().toString());
                    ScanResultUtil.y = Float.parseFloat(inputY.getText().toString());
                    ScanResultUtil.z = Float.parseFloat(inputZ.getText().toString());
                    Scan.getInstance().init(MainActivity.mainActivity);
                    Scan.getInstance().startWsnScan();
                    Scan.getInstance().startImuScan();
                }else{
                    Toast.makeText(MainActivity.mainActivity,"Please finish the parameters setting！！",Toast.LENGTH_LONG).show();
                }
            }
        });
        Setting = view.findViewById(R.id.setting_dyn);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenControllerUtil.getInstance().loadScreen(ScreenControllerUtil.Screen.STATIC_SCAN);
            }
        });


        return view;
    }

    private boolean isInputCompleted(){
        boolean res;
        if (TextUtils.isEmpty(Dev.getText()) || TextUtils.isEmpty(Head.getText())
                || TextUtils.isEmpty(inputX.getText()) || TextUtils.isEmpty(inputY.getText())
                || TextUtils.isEmpty(inputZ.getText()))
            res=true;
        else
            res=false;
        return res;
    }

    public void waitDialog(){
        dialog = new AlertDialog.Builder(MainActivity.mainActivity);
        dialog.setTitle("Sampling");
        dialog.setMessage("the App is collecting data,are you sure to finish it?");
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isFinished = true;
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
