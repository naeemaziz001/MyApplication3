package com.example.sairamkrishna.myapplication;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class CaughtStealing extends Activity {

    Switch mySwitch;
    SharedPreferences sharedpreferences;
    ComponentName cn;
    DevicePolicyManager mgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caughtsteal);
         cn=new ComponentName(this, LoginAttemptListener.class);
         mgr=(DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);
        sharedpreferences = getApplicationContext().getSharedPreferences("CaughtSteal", getApplicationContext().MODE_PRIVATE);

        mySwitch = (Switch) findViewById(R.id.mySwitch);
        int flashpref = sharedpreferences.getInt("CaughtSteal", -1);
        if(flashpref==1 )
        {
            mySwitch.setChecked(true);
        }
        else if(flashpref==0)
        {
            mySwitch.setChecked(false);
        }
        else
        {
            mySwitch.setChecked(false);
        }


        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                startService(new Intent(getApplicationContext(), HeadphoneService.class));
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(isChecked){

                    editor.putInt("CaughtSteal", 1);
                    editor.commit();
                    mySwitch.setChecked(true);
                    if (mgr.isAdminActive(cn)) {

                    }
                    else
                    {
                        Intent intent=
                                new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
                        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                "Research");
                        startActivity(intent);
                    }

                }else{

                    editor.putInt("CaughtSteal", 0);
                    editor.apply();

                }

            }
        });

      /*  if (mgr.isAdminActive(cn)) {

        }
        else
        {
            Intent intent=
                    new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "Research");
            startActivity(intent);
        }

        finish();*/
    }

    }



