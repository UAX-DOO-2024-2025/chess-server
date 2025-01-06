package com.uax.chess.controller;

import com.uax.chess.model.ChessTimer;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/chess-timer")
public class ChessTimerController {

    private ChessTimer timer1;
    private ChessTimer timer2;
    private boolean timer1Active = true;

    // Inicializar temporizadores con tiempo personalizado
    @PostMapping("/init")
    public ResponseEntity<String> initializeTimers(
            @RequestParam int minutesPlayer1,
            @RequestParam int secondsPlayer1,
            @RequestParam int minutesPlayer2,
            @RequestParam int secondsPlayer2) {

        timer1 = new ChessTimer(0, minutesPlayer1, secondsPlayer1);
        timer2 = new ChessTimer(0, minutesPlayer2, secondsPlayer2);

        return ResponseEntity.ok("Timers initialized with Player 1: " +
                minutesPlayer1 + ":" + secondsPlayer1 + " and Player 2: " +
                minutesPlayer2 + ":" + secondsPlayer2);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTimer(@RequestParam boolean isPlayer1) {
        if (isPlayer1) {
            timer1.start();
            timer2.stop();
        } else {
            timer2.start();
            timer1.stop();
        }
        timer1Active = isPlayer1;
        return ResponseEntity.ok("Timer started for player " + (isPlayer1 ? "1" : "2"));
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopTimer() {
        timer1.stop();
        timer2.stop();
        return ResponseEntity.ok("Both timers stopped.");
    }

    @PostMapping("/switch")
    public ResponseEntity<String> switchTimers() {
        if (timer1Active) {
            timer1.stop();
            timer2.start();
        } else {
            timer2.stop();
            timer1.start();
        }
        timer1Active = !timer1Active;
        return ResponseEntity.ok("Timers switched.");
    }

    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        String time1 = timer1.getTime();
        String time2 = timer2.getTime();
        return ResponseEntity.ok("Player 1: " + time1 + ", Player 2: " + time2);
    }
}