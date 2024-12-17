package com.uax.chess.model;

import com.uax.chess.controller.Tablero;

public class ChessGame {

    private Tablero tablero;

    public ChessGame() {
        this.tablero = new Tablero();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

}
