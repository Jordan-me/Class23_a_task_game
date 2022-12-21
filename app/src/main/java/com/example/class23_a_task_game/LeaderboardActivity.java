package com.example.class23_a_task_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {
    private ImageView leaderboard_IMG_return;
    private ImageView leaderboard_IMG_reGame;
    private FragmentList fragmentList;
    private FragmentMap fragmentMap;
    public ManagerDB managerDB;

    private ArrayList<RecordScore> records;
    private AdapterRecordScore adapterRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        fragmentList = new FragmentList();
        fragmentList.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboard_FRAME_list, fragmentList).commit();

        fragmentMap = new FragmentMap();
        fragmentMap.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboard_FRAME_map, fragmentMap).commit();

        hideSystemUI(this);
        findViews();
        onStart();

        initViews();
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

    private void findViews() {
        leaderboard_IMG_return = findViewById(R.id.leaderboard_IMG_return);
        leaderboard_IMG_reGame = findViewById(R.id.leaderboard_IMG_reGame);
        managerDB = new ManagerDB();
    }

    private void initViews() {

        String js = MSPV.getMySPV().getString("MY_DB", "");

        managerDB = new Gson().fromJson(js, ManagerDB.class);
        if (managerDB !=null){
            records = managerDB.getBestScores();
        }
        adapterRecord = new AdapterRecordScore(this, records);

        fragmentList.getMain_LST_records().setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        fragmentList.getMain_LST_records().setHasFixedSize(true);
        fragmentList.getMain_LST_records().setItemAnimator(new DefaultItemAnimator());
        fragmentList.getMain_LST_records().setAdapter(adapterRecord);

        leaderboard_IMG_return.setOnClickListener(v -> finish());
        leaderboard_IMG_reGame.setOnClickListener(v -> onClick());

        adapterRecord.setRecordItemClickListener(new AdapterRecordScore.RecordItemClickListener() {
            @Override
            public void recordItemClicked(RecordScore item, String lat, String lng) {
                Log.d("pttt", "msg from adapterRecord");
                fragmentMap.zoom(Double.parseDouble(lat),Double.parseDouble(lng));

            }
        });
    }

    private void onClick(){
        Intent myIntent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("GAME_MODE", Mode.BUTTONS.name());
        myIntent.putExtra("Bundle", bundle);
        startActivity(myIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentList.getActivity();
        fragmentMap.getActivity();

    }
}