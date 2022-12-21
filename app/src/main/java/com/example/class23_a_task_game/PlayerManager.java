package com.example.class23_a_task_game;

public class PlayerManager {
    private int locationIndex;
    private final int START_LOCATION = 0;
    private final int END_LOCATION = 4;
    private PlayerView playerView;

    public PlayerManager() {
        playerView = new PlayerView();
        this.locationIndex = 1;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public PlayerManager setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
        return this;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public PlayerManager moveRight(){
        if (this.locationIndex < END_LOCATION)
            this.locationIndex++;
        return this;
    }
    public PlayerManager moveLeft(){
        if (this.locationIndex > START_LOCATION)
            this.locationIndex--;
        return this;
    }
    public PlayerManager showPlayer(){
        this.playerView.showImage(this.locationIndex);
        return this;
    }
    public PlayerManager hidePlayer(){
        this.playerView.hideImage(this.locationIndex);
        return this;
    }
}
