package com.chess.service;

import com.chess.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final List<Game> games = new ArrayList<>();

    public Game createGame(Game game) {
        game.initializeGame();
        games.add(game);
        return game;
    }

    public String makeMove(Long gameId, String from, String to) {
        Game game = getGameById(gameId);
        if (game != null) {
            return game.makeMove(from, to);
        }
        return "Partida no encontrada o movimiento inválido.";
    }

    public String[][] getBoard(Long gameId) {
        Game game = getGameById(gameId);
        return game != null ? game.getBoard() : null;
    }

    private Game getGameById(Long id) {
        return games.stream().filter(game -> game.getId().equals(id)).findFirst().orElse(null);
    }
}