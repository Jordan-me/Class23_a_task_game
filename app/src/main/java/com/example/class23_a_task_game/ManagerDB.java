package com.example.class23_a_task_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ManagerDB {
    private ArrayList<RecordScore> bestScore;
    private int score;
    private String lng;
    private String lat;

    public ManagerDB (){
        bestScore = new ArrayList(10);
    }

    public ManagerDB(ArrayList<RecordScore> bestScore, int score, String lot, String lat) {
        this.score = score;
        this.lng = lot;
        this.lat = lat;
    }

    public boolean addScore(int score, String lot, String lat){
        if (bestScore.size()<10) {
            bestScore.add(new RecordScore(score,lot,lat));
            sort();
            return true;
        }
        sort();
        RecordScore lastScore = bestScore.get(9);
        if (score >lastScore.getScore()){
            bestScore.remove(9);
            RecordScore record = new RecordScore(score,lot,lat);
            bestScore.add(record);
            bestScore = sort();
            return true;
        }
        return false;
    }

    public ArrayList<RecordScore> getBestScores() {
        return bestScore;
    }

    public ArrayList<RecordScore> sort() {
        Collections.sort(bestScore, new Comparator<RecordScore>() {
            @Override
            public int compare(RecordScore o1, RecordScore o2) {
                return o1.getScore() > o2.getScore() ? -1 : (o1.getScore() < o2.getScore()) ? 1 : 0;
            }
        });
        return bestScore;
    }
}
