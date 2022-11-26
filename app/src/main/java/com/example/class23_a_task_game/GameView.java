package com.example.class23_a_task_game;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;

public class GameView {
    private AppCompatTextView main_TXT_score;
    private AppCompatTextView main_TXT_level;
    private ArrayList<View> main_IMG_hearts;


    public GameView(int life) {

        this.main_IMG_hearts = new ArrayList<View>(life);
    }


    public void addImageView(View viewById) {
        this.main_IMG_hearts.add(viewById);
    }

    public AppCompatTextView getMain_TXT_score() {
        return main_TXT_score;
    }

    public GameView setMain_TXT_score(AppCompatTextView main_TXT_score) {
        this.main_TXT_score = main_TXT_score;
        return this;
    }
    public GameView setScoreTXT(String txt) {
        this.main_TXT_score.setText(txt);
        return this;
    }
    public AppCompatTextView getMain_TXT_level() {
        return main_TXT_level;
    }

    public GameView setMain_TXT_level(AppCompatTextView main_TXT_level) {
        this.main_TXT_level = main_TXT_level;
        return this;
    }
    public GameView setLevelTXT(String txt) {
        this.main_TXT_level.setText(txt);
        return this;
    }
    public ArrayList<View> getMain_IMG_hearts() {
        return main_IMG_hearts;
    }


    public void setImageInvisible(int i) {
        this.main_IMG_hearts.get(i).setVisibility(View.INVISIBLE);
    }
}
