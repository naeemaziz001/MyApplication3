package com.example.sairamkrishna.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PowerConnectionActivity extends AppCompatActivity {
    Switch PowerConnectionSwitch;
    NotificationManager notiManager;
    IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_noti);

        PowerConnectionSwitch = (Switch) findViewById(R.id.PowerConnectionSwitch);


        //set the switch to ON
        PowerConnectionSwitch.setChecked(true);
        //attach a listener to check for changes in state
        PowerConnectionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
//                startService(new Intent(getApplicationContext(), AlertSoundService.class));
                if(isChecked){
                    startService(new Intent(getApplicationContext(), AlertSoundService.class));
                }else{
                    //switchStatus.setText("Switch is currently OFF");
                }

            }
        });







    }

}
