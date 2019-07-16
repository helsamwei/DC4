package com.hswei.dc4.Utils;

import android.os.Environment;

public class ConstantsUtil {

    public static String DATA_PATH_STATIC =Environment.getExternalStorageDirectory().getPath()
            + "/" + "DC_Sta" + "/";

    public static String DATA_PATH_DYNAMIC = Environment.getExternalStorageDirectory().getPath()
            + "/" + "DC_Dyn" + "/";

    private static final ConstantsUtil ourInstance=new ConstantsUtil();

    public static ConstantsUtil getInstance() {
        return ourInstance;
    }

    private ConstantsUtil() {
    }
}
