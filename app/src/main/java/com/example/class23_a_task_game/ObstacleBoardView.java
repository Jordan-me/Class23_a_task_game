package com.example.class23_a_task_game;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ObstacleBoardView {
    private ArrayList<ArrayList<ImageView>> board;

    public ObstacleBoardView() {
        this.board = new ArrayList<>();
    }

    public ArrayList<ArrayList<ImageView>> getBoard() {
        return board;
    }

    public ObstacleBoardView setBoard(ArrayList<ArrayList<ImageView>> board) {
        this.board = board;
        return this;
    }
    public ObstacleBoardView addRow() {
        this.board.add(new ArrayList<ImageView>());
        return this;
    }
    public ObstacleBoardView addObstacleImage(ImageView obstacleImage, int rowIndex) {
        this.board.get(rowIndex).add(obstacleImage);
        return this;
    }

    public ObstacleBoardView hideAll() {
        for (ArrayList<ImageView> arr:this.board){
            for (ImageView img:arr){
                img.setVisibility(View.INVISIBLE);
            }
        }
        return this;
    }

    public boolean isImageViewVisible(int columnIndex, int rowIndex) {
        return this.board.get(rowIndex).get(columnIndex).getVisibility() == View.VISIBLE;
    }

    public void setImageInvisible(int column, int row) {
        this.board.get(row).get(column).setVisibility(View.INVISIBLE);
    }

    public void setImageVisible(int column, int row) {
        this.board.get(row).get(column).setVisibility(View.VISIBLE);
    }

}
