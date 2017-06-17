package com.example.sairamkrishna.myapplication;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Installed_item_activity extends AppCompatActivity {
    List<AppList> installedApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installed_list_activity);
        ListView userInstalledApps = (ListView)findViewById(R.id.installed_app_list);

       installedApps = getInstalledApps();
        Install_Adapter installedAppAdapter = new Install_Adapter(Installed_item_activity.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);
        userInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Installed_item_activity.this, installedApps.get(position).getpkgName(), Toast.LENGTH_SHORT).show();
                PlayMusicActivity.tv.setText(installedApps.get(position).getName());
                PlayMusicActivity.pkg_tv.setText(installedApps.get(position).getpkgName());
                finish();
            }



        });

    }
    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                String pkg = p.packageName;
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(new AppList(appName,pkg, icon));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
