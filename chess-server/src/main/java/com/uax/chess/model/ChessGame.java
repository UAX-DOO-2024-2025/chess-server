package com.uax.chess.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.uax.chess.controller.Ficha;
import com.uax.chess.controller.Tablero;

@JsonPropertyOrder({ "id", "tablero" })
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
