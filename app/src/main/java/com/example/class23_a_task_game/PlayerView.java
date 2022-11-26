package com.example.class23_a_task_game;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
// CLASS'S RESPONSIBLE: show the player on specific location
public class PlayerView {
    private ArrayList<ImageView> playerLayout;

    public PlayerView() {
        this.playerLayout = new ArrayList<>();
    }

    public ArrayList<ImageView> getPlayerLayout() {
        return playerLayout;
    }

    public PlayerView setPlayerLayout(ArrayList<ImageView> playerLayout) {
        this.playerLayout = playerLayout;
        return this;
    }
    public PlayerView addPlayerImage(ImageView playerImage) {
        this.playerLayout.add(playerImage);
        return this;
    }
    public PlayerView hideAll() {
        for (ImageView img:this.playerLayout) {
            img.setVisibility(View.INVISIBLE);
        }
        return this;
    }
    public PlayerView showImage(int index) {
        this.playerLayout.get(index).setVisibility(View.VISIBLE);
        return this;
    }

    public PlayerView hideImage(int index) {
        this.playerLayout.get(index).setVisibility(View.INVISIBLE);
        return this;
    }
}
