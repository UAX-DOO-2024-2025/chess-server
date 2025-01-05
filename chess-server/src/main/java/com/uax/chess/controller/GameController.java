package com.chess.controller;

import com.chess.model.Game;
import com.chess.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game); // Crea una nueva partida
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames(); // Devuelve todas las partidas
    }
}
