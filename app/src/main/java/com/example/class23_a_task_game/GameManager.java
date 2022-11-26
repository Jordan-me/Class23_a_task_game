package com.example.class23_a_task_game;

public class GameManager {
    public static final int MIN_SPEED = 500;
    public static final int MAX_SPEED = 350;
    public static final String MSG_LOST = "Oh Oh! you crashed";
    private final int AMOUNT_OBSTACLE_PER_LEVEL = 10;
    private int currentSpeed;
    private int score;
    private int level;
    private int LIFE;
    private GameView gameView;
    private int lose;

    public GameManager(int life) {
        this.LIFE = life;
        this.currentSpeed = MIN_SPEED;
        this.lose = -1;
        this.score = 0;
        this.level = 1;
        this.gameView = new GameView(LIFE);
    }

    public GameManager setLIFE(int LIFE) {
        this.LIFE = LIFE;
        return this;
    }

    public int getScore() {
        return score;
    }

    public GameManager setScore(int score) {
        this.score = score;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public GameManager setLevel(int level) {
        this.level = level;
        return this;
    }

    public GameView getGameView() {
        return gameView;
    }

    public GameManager setGameView(GameView gameView) {
        this.gameView = gameView;
        return this;
    }

    public GameManager setLose(int lose) {
        this.lose = lose;
        return this;
    }
    public Boolean manageLevel() {
        int currentLevel = this.level;
        this.level = this.score / this.AMOUNT_OBSTACLE_PER_LEVEL +1;
        if (currentLevel != this.level){
            return true;
        }
        return false;
    }

    public void faster() {
        int minus = (this.level - 1)*10;
        if(this.currentSpeed - minus >= this.MAX_SPEED){
            this.currentSpeed = this.currentSpeed - minus;
        }
    }

    public int getLose() {
        return lose;
    }

    public boolean isGameOver() {
        return this.lose >= LIFE - 1;
    }

    public void refreshUI(){
        this.gameView.setLevelTXT("" + this.level);
        this.gameView.setScoreTXT("" + this.score);
        for (int i = 0; i <= this.lose ; i++) {
            this.gameView.setImageInvisible(i);
        }
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public GameManager setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
        return this;
    }
}
