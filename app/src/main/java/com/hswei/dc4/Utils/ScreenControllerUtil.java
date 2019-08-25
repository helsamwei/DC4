package com.hswei.dc4.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hswei.dc4.Fragment.DynamicscanFragment;
import com.hswei.dc4.Fragment.StaticscanFragment;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScreenControllerUtil {
    private FragmentManager mFragmentManager;
    private static StaticscanFragment mStaticscanFragment;
    private static DynamicscanFragment mDynamicscanFragment;
    private static HashMap<String,Fragment> FragmentList = new HashMap<>();

    private static final ScreenControllerUtil ourInstance=new ScreenControllerUtil();

    public static ScreenControllerUtil getInstance() {
        return ourInstance;
    }

    private ScreenControllerUtil() {
    }

    public static enum Screen{
        STATIC_SCAN,
        DYNAMIC_SCAN
    }

    public void openScreen(Screen screen){
        mFragmentManager = MainActivity.mainActivity.getSupportFragmentManager();
        mStaticscanFragment = new StaticscanFragment();
        mDynamicscanFragment = new DynamicscanFragment();
        FragmentList.put("Sta",mStaticscanFragment);
        FragmentList.put("Dyn",mDynamicscanFragment);
        loadScreen(screen);
    }

    public void loadScreen(Screen screen) {
        Fragment fragment = getFragment(screen);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    private Fragment getFragment(Screen screen) {
        switch(screen){
            case STATIC_SCAN:
                if(FragmentList.get("Sta")==null){
                    FragmentList.put("Sta",new StaticscanFragment());
                }
                return FragmentList.get("Sta");
            case DYNAMIC_SCAN:
                if(FragmentList.get("Dyn") == null){
                    FragmentList.put("Dyn",new DynamicscanFragment());
                }
                return FragmentList.get("Dyn");
            default:
                break;
        }
        return null;
    }
}
