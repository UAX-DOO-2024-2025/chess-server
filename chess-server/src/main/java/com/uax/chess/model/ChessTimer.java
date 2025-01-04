package com.uax.chess.model;

class ChessTimer {
    private int hours, minutes, seconds;
    private boolean active;

    public ChessTimer(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.active = false;
    }

    public void start() {
        active = true;
    }

    public void stop() {
        active = false;
    }

    public void tick() {
        if (active && (hours > 0 || minutes > 0 || seconds > 0)) {
            if (seconds == 0) {
                seconds = 59;
                if (minutes == 0) {
                    minutes = 59;
                    hours--;
                } else {
                    minutes--;
                }
            } else {
                seconds--;
            }
        }
    }

    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}