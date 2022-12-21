package com.example.class23_a_task_game;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ObstacleBoardManager {
    private ObstacleBoardView boardView;
    private final int COLUMNS_COUNT = 5;
    private final int COIN_POINT = 3;
    private ArrayList<ArrayList<Boolean>> activateObstacle;
    private ArrayList<ArrayList<ObstacleType>> activateObstacleTypes;

    public ObstacleBoardManager() {
        this.boardView = new ObstacleBoardView();
        this.activateObstacle = new ArrayList<>(COLUMNS_COUNT);
        this.activateObstacleTypes = new ArrayList<>(COLUMNS_COUNT);
    }

    public ObstacleBoardView getBoardView() {
        return boardView;
    }

    public ObstacleBoardManager setBoardView(ObstacleBoardView boardView) {
        this.boardView = boardView;
        return this;
    }

    public ArrayList<ArrayList<Boolean>> getActivateObstacle() {
        return activateObstacle;
    }

    public ObstacleBoardManager setActivateObstacle(ArrayList<ArrayList<Boolean>> activateObstacle) {
        this.activateObstacle = activateObstacle;
        return this;
    }
    public ObstacleBoardManager initActivateColumns() {
        for (int i = 0; i < this.boardView.getBoard().size() ; i++) {
            this.activateObstacle.add(new ArrayList<>());
            this.activateObstacleTypes.add(new ArrayList<>());
            for (int j = 0; j < this.boardView.getBoard().get(i).size(); j++) {
                this.activateObstacle.get(i).add(false);
                this.activateObstacleTypes.get(i).add(ObstacleType.ROCK);
            }
        }
        return this;
    }
    public ObstacleBoardManager setActivateColumnsAllFalse() {
        for (int i = 0; i < this.activateObstacle.size() ; i++) {
            for (int j = 0; j < this.activateObstacle.get(i).size(); j++) {
                this.activateObstacle.get(i).set(j,Boolean.FALSE);
            }
        }
        return this;
    }

    public boolean isObstacleHit(int columnIndex) {
        return this.boardView.isImageViewVisible(columnIndex, this.boardView.getBoard().size() - 1);

    }

    public void moveObstacle() {
        Log.d("moveObstacle", "enter");
        ArrayList<Integer>indexTrue = new ArrayList<>();
        ArrayList<Integer>indexFalse = new ArrayList<>();
        for (int i = 0; i <  this.activateObstacle.size(); i++) {
            for (int j = 0; j <  this.activateObstacle.get(i).size(); j++){
//                Log.d("moveObstacle", "i = " + i +", j = " + j + ", pass =" + pass);
                //  if there is obstacle
                if (this.activateObstacle.get(i).get(j)) {
                    indexFalse.add(i*10+j);
                    if ((i + 1) < this.activateObstacle.size()) {
                        indexTrue.add((i+1)*10+j);
                        this.activateObstacleTypes.get(i+1).set(j,this.activateObstacleTypes.get(i).get(j));
//                        this.activateObstacle.get(i+1).set(j, this.activateObstacle.get(i).get(j));
                    }
                }

            }
        }
        updateInvisibleBoard(indexFalse);
        updateVisibleBoard(indexTrue);
    }

    private void updateVisibleBoard(ArrayList<Integer> indexTrue) {
        for (Integer index: indexTrue  ) {
            int col = index%10;
            int row = index/10;
            this.activateObstacle.get(row).set(col, Boolean.TRUE);
            this.boardView.changeObstacleView(col,row,this.activateObstacleTypes.get(row).get(col));
            this.boardView.setImageVisible(col, row);
        }
    }

    private void updateInvisibleBoard(ArrayList<Integer> indexFalse) {
        for (Integer index: indexFalse  ) {
            int col = index%10;
            int row = index/10;
            this.activateObstacle.get(row).set(col, Boolean.FALSE);
            this.boardView.setImageInvisible(col, row);
        }
    }

    public ObstacleBoardManager addColumn() {
        this.activateObstacle.add(new ArrayList<>());
        return this;
    }

    public void createNewObstacle() {
//      randomize number 0 to COLUMNS_COUNT
        int randomColumn = new Random().nextInt(COLUMNS_COUNT);
        ObstacleType obstacleType = ObstacleType.values()[new Random().nextInt(ObstacleType.values().length)];
//      show this obstacle on row 0 and set activate obstacle true
        this.activateObstacle.get(0).set(randomColumn,Boolean.TRUE);
        this.activateObstacleTypes.get(0).set(randomColumn,obstacleType);
        this.boardView.changeObstacleView(randomColumn,0,obstacleType);
        this.boardView.setImageVisible(randomColumn,0);
    }
    public void createNewObstacle1() {
//      randomize number 0 to COLUMNS_COUNT
        int randomColumn = new Random().nextInt(COLUMNS_COUNT);
//      show this obstacle on row 0 and set activate obstacle true
        this.activateObstacle.get(0).set(randomColumn,Boolean.TRUE);
        this.boardView.setImageVisible(randomColumn,0);
    }

    public int getObstaclePassed(int exceptIndex) {
        int count = 0;
        for (int i = 0; i < COLUMNS_COUNT; i++) {
            if(this.activateObstacle.get(this.activateObstacle.size()-1).get(i) && i != exceptIndex){
                if(this.activateObstacleTypes.get(this.activateObstacle.size()-1).get(i) != ObstacleType.COIN)
                    count++;
            }
        }
        return count;
    }

    public boolean isObstacleHitOnCoin(int i) {
        return this.activateObstacleTypes.get(this.activateObstacle.size()-1).get(i) == ObstacleType.COIN;
    }

    public int getCOIN_POINT() {
        return COIN_POINT;
    }
}
