package com.example.class23_a_task_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {
    private MaterialButton menu_BTN_modeB;
    private MaterialButton menu_BTN_modeS;
    private MaterialButton menu_BTN_modeT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        hideSystemUI(this);
        activatePermissions();
        findViews();
        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI(this);
    }

    private void activatePermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1001  );
            }else{
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1001  );
            }
        }
    }

    private void hideSystemUI(Activity activity) {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Dim the Status and Navigation Bars
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);

        // Without - cut out display
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            activity.getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    private void initViews() {
        menu_BTN_modeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActivityActivate(Mode.BUTTONS);
            }
        });
        menu_BTN_modeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActivityActivate(Mode.SENSORS);
            }
        });

        menu_BTN_modeT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderboardActivityActivate();
            }
        });

    }
    private void leaderboardActivityActivate() {
        Intent myIntent = new Intent(this,LeaderboardActivity.class);
        startActivity(myIntent);
    }

    private void gameActivityActivate(Mode mode) {
        Intent myIntent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("GAME_MODE", mode.name());
        myIntent.putExtra("Bundle", bundle);
        startActivity(myIntent);
    }

    private void findViews() {
        menu_BTN_modeB = findViewById(R.id.menu_BTN_modeB);
        menu_BTN_modeS = findViewById(R.id.menu_BTN_modeS);
        menu_BTN_modeT = findViewById(R.id.menu_BTN_modeT);
    }

}