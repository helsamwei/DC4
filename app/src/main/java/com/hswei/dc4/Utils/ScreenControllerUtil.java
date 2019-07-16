package com.hswei.dc4.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hswei.dc4.Fragment.DynamicscanFragment;
import com.hswei.dc4.Fragment.StaticscanFragment;
import com.hswei.dc4.MainActivity;
import com.hswei.dc4.R;

public class ScreenControllerUtil {
    private FragmentManager mFragmentManager;

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

        Fragment fragment = getFragment(screen);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    private Fragment getFragment(Screen screen) {
        switch(screen){
            case STATIC_SCAN:
                return new StaticscanFragment();
            case DYNAMIC_SCAN:
                return new DynamicscanFragment();
            default:
                break;
        }
        return null;
    }
}
