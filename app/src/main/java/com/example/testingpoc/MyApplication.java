package com.example.testingpoc;

import android.app.Application;
import android.content.Intent;

import com.example.notifications.service.AlwaysOnService;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, AlwaysOnService.class));
    }
}
