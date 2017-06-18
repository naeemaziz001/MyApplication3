package com.example.sairamkrishna.myapplication;


import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;


public class LoginAttemptListener extends DeviceAdminReceiver {
    static int attemptCount=0;
    @Override
    public void onEnabled(Context ctxt, Intent intent) {
        ComponentName cn=new ComponentName(ctxt, LoginAttemptListener.class);
        DevicePolicyManager mgr=
                (DevicePolicyManager)ctxt.getSystemService(Context.DEVICE_POLICY_SERVICE);

        mgr.setPasswordQuality(cn,
                DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC);

        onPasswordChanged(ctxt, intent);
    }

    @Override
    public void onPasswordChanged(Context ctxt, Intent intent) {

    }

    @Override
    public void onPasswordFailed(Context ctxt, Intent intent) {
        attemptCount=attemptCount+1;
        if(attemptCount==2)
        {
            attemptCount=0;

        //takePhoto(ctxt);
           Toast.makeText(ctxt, "Don't Touch My phone. You Have been Caught!", Toast.LENGTH_LONG)
                   .show();

        }
    }

    @Override
    public void onPasswordSucceeded(Context ctxt, Intent intent) {
        /*Toast.makeText(ctxt, "success", Toast.LENGTH_LONG)
                .show();*/
    }

}