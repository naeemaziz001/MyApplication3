package com.example.sairamkrishna.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class FlashActivity extends AppCompatActivity {
    Switch mySwitch;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        sharedpreferences = getApplicationContext().getSharedPreferences("flashon", getApplicationContext().MODE_PRIVATE);

        mySwitch = (Switch) findViewById(R.id.mySwitch);
        int flashpref = sharedpreferences.getInt("flashon", -1);
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


        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                startService(new Intent(getApplicationContext(), HeadphoneService.class));
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(isChecked){

                    editor.putInt("flashon", 1);
                    editor.commit();
                    mySwitch.setChecked(true);
                    startService(new Intent(getApplicationContext(), FlashService.class));
                }else{

                    editor.putInt("flashon", 0);
                    editor.apply();

                }

            }
        });


    }

}
