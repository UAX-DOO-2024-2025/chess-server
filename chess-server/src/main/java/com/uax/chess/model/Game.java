package com.chess.model;

import com.chess.logic.ChessGame;

public class Game {
    private Long id;
    private String playerWhite;
    private String playerBlack;
    private String status; // active, finished, waiting
    private ChessGame chessGame; // Lógica del juego de ajedrez

    public Game() {
        this.chessGame = new ChessGame(); // Inicializa el tablero
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(String playerWhite) {
        this.playerWhite = playerWhite;
    }

    public String getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(String playerBlack) {
        this.playerBlack = playerBlack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public void initializeGame() {
        this.status = "active"; // Cambia el estado a activo
        this.chessGame.initializeBoard(); // Inicializa el tablero de ajedrez
    }
}
