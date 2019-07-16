package com.hswei.dc4.Utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

public class ImuUtil implements SensorEventListener {

    private SensorManager sensorManager;

    private float acc_x,acc_y,acc_z,gyro_x,gyro_y,gyro_z,mag_x,mag_y,mag_z;

    private static final ImuUtil ourInstance=new ImuUtil();

    public static ImuUtil getInstance() {
        return ourInstance;
    }

    private ImuUtil() {
    }

    public void registerSensor(Context context){
        sensorManager =(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor:sensors){
            sensorManager.registerListener(ourInstance,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                acc_x = sensorEvent.values[0];
                acc_y = sensorEvent.values[1];
                acc_z = sensorEvent.values[2];
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyro_x = sensorEvent.values[0];
                gyro_y = sensorEvent.values[1];
                gyro_z = sensorEvent.values[2];
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mag_x = sensorEvent.values[0];
                mag_y = sensorEvent.values[1];
                mag_z = sensorEvent.values[2];
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getAcc_x() {
        return acc_x;
    }

    public float getAcc_y() {
        return acc_y;
    }

    public float getAcc_z() {
        return acc_z;
    }

    public float getGyro_x() {
        return gyro_x;
    }

    public float getGyro_y() {
        return gyro_y;
    }

    public float getGyro_z() {
        return gyro_z;
    }

    public float getMag_x() {
        return mag_x;
    }

    public float getMag_y() {
        return mag_y;
    }

    public float getMag_z() {
        return mag_z;
    }
}
