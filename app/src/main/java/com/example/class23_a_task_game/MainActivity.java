package com.example.class23_a_task_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Mode GAME_MODE = Mode.BUTTONS;
    private SensorsManager sensorManager;
    private SensorsManager.CallBack_sensor callBack_sensor;
    private AppCompatImageView main_IMG_background;
    private ExtendedFloatingActionButton main_BTN_right;
    private ExtendedFloatingActionButton main_BTN_left;
    private PlayerManager playerManager;
    private GameManager gameManager;
    private ObstacleBoardManager obstacleBoardManager;
    private TimerTask timerTask;
//    private Timer timer;
    private boolean flagSpeed = false;
    private int DELAY ;
    private LocationManager.CallBack_Location callBack_location;
    private String lat;
    private String lng;
    private ManagerDB managerDB;
    private boolean helper;

    public MainActivity setCallBack_location() {
        callBack_location = new LocationManager.CallBack_Location() {
            @Override
            public void setCurrentLocation(String latitude, String longitude) {
                lat = latitude;
                lng = longitude;
            }
        };
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_IMG_background = findViewById(R.id.main_IMG_background);
//        set background image
        Glide
                .with(this)
                .load(R.drawable.img_galaxy)
                .into(main_IMG_background);
//    Set system UI as immersive screen
        hideSystemUI(this);
        setCallBack_location();

        findViews();
        setGameMode();
        initViews();
        setDelay(this.gameManager.getCurrentSpeed());
    }

    private void setGameMode() {
        Bundle extras = getIntent().getBundleExtra("Bundle");
        if (extras != null) {
            GAME_MODE = Mode.valueOf(extras.getString("GAME_MODE"));
        }
    }

    private void setDelay(int delay) {
        this.DELAY = delay;
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

    @SuppressLint("WrongViewCast")
    private void findViews() {
//        Obstacle View
        this.obstacleBoardManager = new ObstacleBoardManager();
        //        ROW 0
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c0),0);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c1),0);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c2),0);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c3),0);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c4),0);
        //        ROW 1
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c0),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c1),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c2),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c3),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c4),1);
        //        ROW 2
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c0),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c1),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c2),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c3),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c4),2);
        //        ROW 3
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c0),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c1),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c2),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c3),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c4),3);
        //        ROW 4
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c0),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c1),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c2),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c3),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c4),4);
        //        ROW 5
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r5_c0),5);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r5_c1),5);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r5_c2),5);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r5_c3),5);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r5_c4),5);
        //        ROW 6
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r6_c0),6);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r6_c1),6);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r6_c2),6);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r6_c3),6);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r6_c4),6);

//        Game View - stage & score & hearts
        int life = 3;
        this.gameManager = new GameManager(life);
//        Game View- Hearts
        this.gameManager.getGameView().addImageView(findViewById(R.id.ic_IMG_heart1));
        this.gameManager.getGameView().addImageView(findViewById(R.id.ic_IMG_heart2));
        this.gameManager.getGameView().addImageView(findViewById(R.id.ic_IMG_heart3));
//        this.gameManager.getGameView().addImageView(findViewById(R.id.ic_IMG_heart4));
//        Game view - stage
        this.gameManager.setLevel(1);
        this.gameManager.getGameView().setMain_TXT_level(findViewById(R.id.main_TXT_level));
//        Game view - score
        this.gameManager.setScore(0);
        this.gameManager.getGameView().setMain_TXT_score(findViewById(R.id.main_TXT_score));

//        Player View
        this.playerManager = new PlayerManager();
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_left));
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_left_1));
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_middle));
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_right));
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_right_1));
//        Buttons Right  &  Left
        this.main_BTN_left = findViewById(R.id.main_BTN_left);
        this.main_BTN_right = findViewById(R.id.main_BTN_right);

    }

    private void initViews() {
        this.helper = true;
        LocationManager locationManager =  new LocationManager(this);
        locationManager.getCurrentLocation(callBack_location);
        if(lng==""||lat==""){
            lng = "0";
            lat = "0";
        }

        this.obstacleBoardManager.getBoardView().hideAll();
        this.playerManager.getPlayerView().hideAll();
        this.playerManager.showPlayer();
        this.obstacleBoardManager.initActivateColumns();
//        Initialize Movement {Listeners for arrow's buttons} if mode is button mode
        if(GAME_MODE == Mode.BUTTONS){
            main_BTN_left.setVisibility(View.VISIBLE);
            main_BTN_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerManager.hidePlayer().moveLeft().showPlayer();
                }
            });
            main_BTN_right.setVisibility(View.VISIBLE);
            main_BTN_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerManager.hidePlayer().moveRight().showPlayer();
                }
            });
        }else if(GAME_MODE == Mode.SENSORS){
            initSensor();
        }

    }

    private void initSensor() {
        callBack_sensor = new SensorsManager.CallBack_sensor() {
            @Override
            public void moveRight() {
                playerManager.hidePlayer().moveRight().showPlayer();
            }

            @Override
            public void moveLeft() {
                playerManager.hidePlayer().moveLeft().showPlayer();
            }

            @Override
            public void slower() {
                if(DELAY < gameManager.MIN_SPEED)
                    setDelay(DELAY + 1);
            }

            @Override
            public void faster() {
                if(DELAY > gameManager.MAX_SPEED)
                    setDelay(DELAY - 1);
            }
        };
        sensorManager = new SensorsManager(this, callBack_sensor);
        sensorManager.start();
    }

    private void updateUI() {
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if(!gameManager.isGameOver() && obstacleBoardManager.isObstacleHit(playerManager.getLocationIndex())){
                        if(obstacleBoardManager.isObstacleHitOnCoin(playerManager.getLocationIndex())){
                            sound(Mode.COLLECT);
                            gameManager.setScore(gameManager.getScore() + obstacleBoardManager.getCOIN_POINT());
                        }else{
                            vibrate();
                            sound(Mode.CRASH);
                            toast(gameManager.MSG_LOST);
                            gameManager.setLose(gameManager.getLose() +1);
                        }
                    }else{
//                      check how mach obstacle passed and add score accordingly
                        gameManager.setScore(gameManager.getScore() + obstacleBoardManager.getObstaclePassed(playerManager.getLocationIndex()));
                    }
//                  check if game-over
                    if(gameManager.isGameOver() && helper){
                        helper = false;
                        stopTimer();
                        endGame();
                        finish();
                        return;
                    }
//                  move showing rocks next row
                    obstacleBoardManager.moveObstacle();
//                  randomize new rock
                    obstacleBoardManager.createNewObstacle();
//                  refresh game status
                    gameManager.refreshUI();
//                  increase level and speed
                    flagSpeed = gameManager.manageLevel();

                });
            }

        }, 0, DELAY);

    }

    private void endGame() {
        String js = MSPV.getMySPV().getString("MY_DB", "");
        if (!js.equals("")) {
            managerDB = new Gson().fromJson(js, ManagerDB.class);
        }else{
            managerDB =new ManagerDB();
        }
        if (managerDB.addScore(gameManager.getScore(), lng, lat)) {
            Intent myIntent = new Intent(this, LeaderboardActivity.class);
            String json = new Gson().toJson(managerDB);
            MSPV.getMySPV().putString("MY_DB", json);
            startActivity(myIntent);
        }
    }

    private void sound(Mode sound) {
        if(sound == Mode.CRASH){
            // play crash sound
            MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_crash);
            mp.start();
        }else if(sound == Mode.COLLECT){
            // play collect sound
            (new MediaActionSound()).play(MediaActionSound.STOP_VIDEO_RECORDING);
        }
    }
    private void toast(String txt) {
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();

    }

    private void stopTimer() {
        this.timerTask.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GAME_MODE == Mode.SENSORS){
            sensorManager.start();
        }
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(GAME_MODE == Mode.SENSORS){
            sensorManager.stop();
        }
        stopTimer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(GAME_MODE == Mode.SENSORS){
            sensorManager.stop();
        }
        stopTimer();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
    }



}
