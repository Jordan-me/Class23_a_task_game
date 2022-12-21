package com.example.class23_a_task_game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsManager {
    public interface CallBack_sensor {
        void moveRight();
        void moveLeft();
        void faster();
        void slower();
    }

    private SensorManager mSensorManager;
    private Sensor sensor;

    long timeStamp = 0;
    private CallBack_sensor callBack_sensor;

    public SensorsManager(Context context, CallBack_sensor _callBack_sensor) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.callBack_sensor = _callBack_sensor;
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            calculateMovement(x, y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void calculateMovement(float x, float y) {
//        move right
        if (x >= 2.0) {
            if (System.currentTimeMillis() - timeStamp > 100) {
                timeStamp = System.currentTimeMillis();
                if (callBack_sensor != null) {
                    callBack_sensor.moveLeft();
                }
            }
        }
//       move left
        if (x <= -2.0) {
            if (System.currentTimeMillis() - timeStamp > 100) {
                timeStamp = System.currentTimeMillis();
                if (callBack_sensor != null) {
                    callBack_sensor.moveRight();
                }
            }
        }
//      increase speed
        if (y < 6.0) {
            if (System.currentTimeMillis() - timeStamp > 100) {
                timeStamp = System.currentTimeMillis();
                if (callBack_sensor != null) {
                    callBack_sensor.faster();
                }
            }
        }
//      decrease speed
        if (y > 7.0 ) {
            if (System.currentTimeMillis() - timeStamp > 100) {
                timeStamp = System.currentTimeMillis();
                if (callBack_sensor != null) {
                    callBack_sensor.slower();
                }
            }
        }
    }
    /**
     * register to the sensors
     */
    public void start() {
        mSensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * unregister to the sensors
     */
    public void stop() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

}
