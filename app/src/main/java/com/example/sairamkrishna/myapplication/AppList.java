package com.example.sairamkrishna.myapplication;

/**
 * Created by naeemaziz on 6/17/17.
 */
import android.graphics.drawable.Drawable;

public class AppList {

    private String name;
    private String pkgname;
    Drawable icon;

    public AppList(String name,String pkg, Drawable icon) {
        this.name = name;
        this.icon = icon;
        this.pkgname = pkg;
    }

    public String getName() {
        return name;
    }
    public String getpkgName() {
        return pkgname;
    }

    public Drawable getIcon() {
        return icon;
    }
}

