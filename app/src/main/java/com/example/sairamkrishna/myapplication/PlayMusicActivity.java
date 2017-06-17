package com.example.sairamkrishna.myapplication;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import android.content.ComponentName;
import android.widget.TextView;

public class PlayMusicActivity extends AppCompatActivity {
    Switch mySwitch;
    SharedPreferences sharedpreferences;
    Button button;
    Button update;
    public static TextView tv;
    public static TextView pkg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_detail);
        sharedpreferences = getApplicationContext().getSharedPreferences("playerdata", getApplicationContext().MODE_PRIVATE);

        mySwitch = (Switch) findViewById(R.id.mySwitch);
        tv=(TextView) findViewById(R.id.current_app);
        pkg_tv=(TextView) findViewById(R.id.pkgname);
        String pkg1=sharedpreferences.getString("pkgnme", "com.google.android.youtube");
        String name1=sharedpreferences.getString("appnme", "Youtube");
        pkg_tv.setText(pkg1);
        tv.setText(name1);

        int playrpref = sharedpreferences.getInt("playerdata", -1);
        if(playrpref==1 )
        {
            mySwitch.setChecked(true);
        }
        else if(playrpref==0)
        {
            mySwitch.setChecked(false);
        }
        else
        {
            mySwitch.setChecked(false);
        }
        button = (Button) findViewById(R.id.chooseButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(getApplicationContext(), Installed_item_activity.class);
                startActivity(i);

            }

        });

        update = (Button) findViewById(R.id.updateBtn);

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("pkgnme", pkg_tv.getText().toString().trim()+"");
                editor.putString("appnme", tv.getText().toString().trim()+"");
                editor.putInt("playerdata", 1);
                editor.commit();
                startService(new Intent(getApplicationContext(), HeadphoneService.class));
                //Intent i = new Intent(getApplicationContext(), Installed_item_activity.class);
               // startActivity(i);

            }

        });
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                startService(new Intent(getApplicationContext(), HeadphoneService.class));
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(isChecked){

                    editor.putInt("playerdata", 1);
                    editor.commit();
                    mySwitch.setChecked(true);
                    startService(new Intent(getApplicationContext(), HeadphoneService.class));
                }else{


                    editor.putInt("playerdata", 0);
                    editor.apply();
                    PackageManager pm = getApplicationContext().getPackageManager();
                    ComponentName component = new ComponentName(getApplicationContext(), HeadphoneService.class);
                    pm.setComponentEnabledSetting(component , PackageManager.COMPONENT_ENABLED_STATE_DISABLED , PackageManager.DONT_KILL_APP);

                }

            }
        });


    }

}
