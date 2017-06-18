package com.example.sairamkrishna.myapplication;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.IBinder;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.IntentFilter;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import static com.example.sairamkrishna.myapplication.R.attr.icon;

public class AlertSoundService extends Service {
    private  IBinder vplug_binder;
    private  BroadcastReceiver vplug_receiver;
    public void onCreate() {

        IntentFilter inf = new IntentFilter();
        inf.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        inf.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        registerReceiver(this.vplug_receiver, inf);

    }
    public AlertSoundService() {
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
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), soundUri);
            r.play();

//            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//            boolean isFull = status == BatteryManager.BATTERY_STATUS_FULL;
//            // BatteryManager.BATTERY_STATUS_CHARGING ||
//
//
//            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
//            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
//            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
//
//            if (usbCharge || acCharge || isFull) {
//                NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
//                //Define sound URI

//                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
//                        .setSound(soundUri); //This sets the sound to play
//                //Display notification
//                notificationManager.notify(0, mBuilder.build());


        }

    }
    public class LocalBinder extends Binder {
        public AlertSoundService getService() {
            return AlertSoundService.this;
        }
    }
    public void onDestroy() {

        unregisterReceiver(this.vplug_receiver);
    }
}
