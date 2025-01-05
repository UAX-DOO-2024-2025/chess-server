package com.chess.controller;

import com.chess.model.Game;
import com.chess.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PostMapping("/{id}/move")
    public String makeMove(@PathVariable Long id, @RequestBody Map<String, String> move) {
        String from = move.get("from");
        String to = move.get("to");
        return gameService.makeMove(id, from, to);
    }

    @GetMapping("/{id}/board")
    public String[][] getBoard(@PathVariable Long id) {
        return gameService.getBoard(id);
    }
}
