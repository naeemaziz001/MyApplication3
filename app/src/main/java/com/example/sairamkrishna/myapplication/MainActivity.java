package com.example.sairamkrishna.myapplication;

import android.hardware.Camera;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Plug & Go Media",
                "Shake To Light",
                "Ringing Connection",
                "Caught Stealing",
                "Emergency Location",
                "Call Recorder"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
         listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {
                    Intent i = new Intent(getApplicationContext(), PlayMusicActivity.class);
                    startActivity(i);
                }
                else if(position==1)
                {
                    Intent i = new Intent(getApplicationContext(), FlashActivity.class);
                    startActivity(i);
                }
                else if (position == 2)
                {
                    Intent i = new Intent(getApplicationContext(), PowerConnectionActivity.class);
                    startActivity(i);
                }
                else if(position==3)
                {
                    Intent i = new Intent(getApplicationContext(), CaughtStealing.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not Implemented Yet",
                            Toast.LENGTH_SHORT).show();
                }


            }



        });
    }

    }



