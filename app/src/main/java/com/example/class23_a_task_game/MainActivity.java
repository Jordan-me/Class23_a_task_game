package com.example.class23_a_task_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
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
        findViews();
        initViews();
        setDelay(this.gameManager.getCurrentSpeed());
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
        //        ROW 1
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c0),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c1),1);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c2),1);
        //        ROW 2
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c0),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c1),2);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c2),2);
        //        ROW 3
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c0),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c1),3);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c2),3);
        //        ROW 4
        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c0),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c1),4);
        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c2),4);
//        //        COLUMN 0
//        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c0),0);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c0),0);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c0),0);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c0),0);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c0),0);
//        //        COLUMN 1
//        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c1),1);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c1),1);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c1),1);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c1),1);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c1),1);
//        //        COLUMN 2
//        this.obstacleBoardManager.getBoardView().addRow().addObstacleImage(findViewById(R.id.main_IMG_rock_r0_c2),2);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r1_c2),2);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r2_c2),2);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r3_c2),2);
//        this.obstacleBoardManager.getBoardView().addObstacleImage(findViewById(R.id.main_IMG_rock_r4_c2),2);

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
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_middle));
        this.playerManager.getPlayerView().addPlayerImage(findViewById(R.id.main_IMG_rocket_right));
//        Buttons Right  &  Left
        this.main_BTN_left = findViewById(R.id.main_BTN_left);
        this.main_BTN_right = findViewById(R.id.main_BTN_right);

    }
    private void initViews() {
        this.obstacleBoardManager.getBoardView().hideAll();
        this.playerManager.getPlayerView().hideAll();
        this.playerManager.showPlayer();
        this.obstacleBoardManager.initActivateColumns();
//        Initialize Movement {Listeners for arrow's buttons}
        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerManager.hidePlayer().moveLeft().showPlayer();
            }
        });
        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerManager.hidePlayer().moveRight().showPlayer();
            }
        });
//        speed manage
//        this.gameManager.getGameView().getMain_TXT_level().addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(gameManager.getLevel() != 1 && flagSpeed){
//                    flagSpeed = false;
//                    gameManager.faster();
//                    timerTask.cancel();
////                    timer.getClass().
//                    setDelay(gameManager.getCurrentSpeed());
//                    startTimer();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
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
                    if(obstacleBoardManager.isObstacleHit(playerManager.getLocationIndex())){
                        vibrate();
                        toast(gameManager.MSG_LOST);
                        gameManager.setLose(gameManager.getLose() +1);
                    }else{
//                      check how mach obstacle passed and add score accordingly
                        gameManager.setScore(gameManager.getScore() + obstacleBoardManager.getObstaclePassed(playerManager.getLocationIndex()));
                    }
//                  check if game-over
                    if(gameManager.isGameOver()){
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
    protected void onStop() {
        super.onStop();
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
