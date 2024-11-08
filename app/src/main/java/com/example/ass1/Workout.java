package com.example.ass1;

public class Workout {

    private String type;
    private int duration;

    public Workout(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return type + " - " + duration + " mins";
    }
}
