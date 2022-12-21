package com.example.class23_a_task_game;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MSPV.init(this);
    }
}
