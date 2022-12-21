package com.example.class23_a_task_game;

public class RecordScore {
    private int score;
    private String lng;
    private String lat;

    public RecordScore() {
    }

    public RecordScore(int score, String lng, String lat) {
        this.score = score;
        this.lng = lng;
        this.lat = lat;
    }

    public int getScore() {
        return score;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }
}
