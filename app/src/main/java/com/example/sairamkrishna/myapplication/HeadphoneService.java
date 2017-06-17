package com.example.sairamkrishna.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.IntentFilter;

public class HeadphoneService extends Service {
    private  IBinder vplug_binder;
    private  BroadcastReceiver vplug_receiver;
    public void onCreate() {

        IntentFilter inf = new IntentFilter();
        inf.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(this.vplug_receiver, inf);

    }
    public HeadphoneService() {
        this.vplug_binder = new LocalBinder();
        this.vplug_receiver = new broadcst();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return this.vplug_binder;
    }

    class broadcst extends BroadcastReceiver {
        broadcst() {
        }

        public void onReceive(Context ctx, Intent intent) {

            if (intent.getIntExtra("state", -1) == 1) {

                //Toast.makeText(ctx, "connected", Toast.LENGTH_SHORT).show();

                PackageManager pm = ctx.getPackageManager();
                Intent appStartIntent = pm.getLaunchIntentForPackage("com.google.android.youtube");
                if (null != appStartIntent)
                {
                    ctx.startActivity(appStartIntent);
                }

            }
        }
    }
    public class LocalBinder extends Binder {
        public HeadphoneService getService() {
            return HeadphoneService.this;
        }
    }
    public void onDestroy() {

        unregisterReceiver(this.vplug_receiver);
    }
}
