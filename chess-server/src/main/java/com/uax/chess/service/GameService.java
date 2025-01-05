package com.chess.service;

import com.chess.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final List<Game> games = new ArrayList<>(); // Almacena todas las partidas

    public Game createGame(Game game) {
        game.initializeGame(); // Inicializa el juego
        games.add(game); // Agrega la partida a la lista
        return game; // Devuelve la partida creada
    }

    public List<Game> getAllGames() {
        return games; // Devuelve todas las partidas
    }

    public Game getGameById(Long id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null); // Encuentra una partida por su ID
    }
}
