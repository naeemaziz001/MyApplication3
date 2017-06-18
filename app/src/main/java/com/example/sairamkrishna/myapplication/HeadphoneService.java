package com.example.sairamkrishna.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.IntentFilter;

public class HeadphoneService extends Service {
    private  IBinder vplug_binder;
    public static BroadcastReceiver vplug_receiver;
    SharedPreferences sharedpreferences;

    public void onCreate() {
        sharedpreferences = getApplicationContext().getSharedPreferences("playerdata", getApplicationContext().MODE_PRIVATE);

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
                String pkg1=sharedpreferences.getString("pkgnme", "com.google.android.youtube");
                PackageManager pm = ctx.getPackageManager();
                Intent appStartIntent = pm.getLaunchIntentForPackage(pkg1);
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
    public  void  unregisterrFunction() {

        unregisterReceiver(this.vplug_receiver);
    }
    public  void onDestroy() {

        unregisterReceiver(this.vplug_receiver);
    }

}
