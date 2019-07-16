package com.hswei.dc4.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    private static final FileUtil ourInstance=new FileUtil();

    public static FileUtil getInstance() {
        return ourInstance;
    }

    private FileUtil() {
    }

    public boolean saveData(String fileName,String content){
        boolean res = false;
        File file = new File(fileName);
        FileOutputStream os;
        try {
            os = new FileOutputStream(file,true);
            os.write(content.getBytes());
            os.close();
            res = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
