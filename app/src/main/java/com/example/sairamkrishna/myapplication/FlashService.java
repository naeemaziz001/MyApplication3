package com.example.sairamkrishna.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.IBinder;
import android.widget.Toast;

public class FlashService extends Service {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    static boolean  Servflash=false;
    public FlashService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener(){
            @Override
            public void onShake() {


                if(Servflash==false)
                {
                    Servflash=true;
                    handleActionTurnOnFlashLight(getApplicationContext());
                }
                else
                {
                    Servflash=false;
                    handleActionTurnOffFlashLight(getApplicationContext());
                }
            }
        });


        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    private static void handleActionTurnOffFlashLight(Context context){
        try{
            CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode(manager.getCameraIdList()[0], false);
        }
        catch (CameraAccessException cae){
            //Log.e(TAG, cae.getMessage());
            cae.printStackTrace();
        }
    }
    private static void handleActionTurnOnFlashLight(Context context){
        try{

            CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            String[] list = manager.getCameraIdList();
            manager.setTorchMode(list[0], true);

        }
        catch (CameraAccessException cae){
          //  Toast.makeText(context, "Exception flashLightOn()",
           //         Toast.LENGTH_SHORT).show();
            // Log.e(TAG, cae.getMessage());
            cae.printStackTrace();
        }
    }
    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onDestroy();
    }
}
