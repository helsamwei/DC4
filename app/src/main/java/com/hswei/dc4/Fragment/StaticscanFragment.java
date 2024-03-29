package com.hswei.dc4.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hswei.dc4.Adapter.RefPointAdapter;
import com.hswei.dc4.Scan.Scan;
import com.hswei.dc4.ScanService.ImuScanService;
import com.hswei.dc4.ScanService.WsnScanService;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.R;
import com.hswei.dc4.Utils.ScanResultUtil;
import com.hswei.dc4.Utils.ScreenControllerUtil;

import java.util.ArrayList;
import java.util.List;

public class StaticscanFragment extends Fragment {

    private EditText Dur,Dev,Head,inputX,inputY,inputZ;
    private RecyclerView mRecView;
    public static Chronometer Clock;
    private ImageView Start,Setting;
    public static ProgressDialog dialog;
    private int SCAN_STATIC = 1;
    private LinearLayoutManager mLinearLayoutManager;
    private List<String> mData = new ArrayList<>();
    RefPointAdapter adapter;
    String data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sta_scan_fragment,container,false);
        Dur = view.findViewById(R.id.dur);
        Dev = view.findViewById(R.id.dev);
        Head = view.findViewById(R.id.head);
        inputX = view.findViewById(R.id.inputX);
        inputY = view.findViewById(R.id.inputY);
        inputZ = view.findViewById(R.id.inputZ);
        Clock = view.findViewById(R.id.clock);
        Start = view.findViewById(R.id.btn_start);
        mRecView = view.findViewById(R.id.recView);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        initAdapter();

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInputCompleted()){
                    Clock.setBase(SystemClock.elapsedRealtime());
                    Clock.start();
                    waitDialog();

                    ScanResultUtil.scanMode = SCAN_STATIC;
                    ScanResultUtil.dur = Float.parseFloat(Dur.getText().toString());
                    ScanResultUtil.dev = Integer.parseInt(Dev.getText().toString());
                    ScanResultUtil.head = Head.getText().toString().charAt(0);
                    ScanResultUtil.x = Float.parseFloat(inputX.getText().toString());
                    ScanResultUtil.y = Float.parseFloat(inputY.getText().toString());
                    ScanResultUtil.z = Float.parseFloat(inputZ.getText().toString());
                    data = "RP:("+inputX.getText().toString()+","+inputY.getText().toString()+
                            ","+inputZ.getText().toString()+")";
                    adapter.addData(data);
                    Scan.getInstance().init(MainActivity.mainActivity);
                    Scan.getInstance().startWsnScan();
                    Scan.getInstance().startImuScan();

                }else{
                    Toast.makeText(MainActivity.mainActivity,"Please finish the parameters setting！",Toast.LENGTH_LONG).show();
                }
            }
        });
        Setting = view.findViewById(R.id.setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenControllerUtil.getInstance().loadScreen(ScreenControllerUtil.Screen.DYNAMIC_SCAN);
            }
        });

        return view;
    }



    private void initAdapter() {
        mRecView.setLayoutManager(mLinearLayoutManager);
        adapter = new RefPointAdapter(mData);

        mRecView.setAdapter(adapter);
    }

    private boolean isInputCompleted(){
        boolean res;
        if (TextUtils.isEmpty(Dur.getText()) || TextUtils.isEmpty(Dev.getText())
                || TextUtils.isEmpty(Head.getText()) || TextUtils.isEmpty(inputX.getText())
                || TextUtils.isEmpty(inputY.getText()) || TextUtils.isEmpty(inputZ.getText()))
            res=true;
        else
            res=false;
        return res;
    }

    public void waitDialog(){
        dialog = new ProgressDialog(MainActivity.mainActivity);
        dialog.setTitle("Data is being collected,Please waiting finished!");
        dialog.setMessage("Sampling...");
        dialog.setCancelable(false);
        dialog.show();
    }

}
