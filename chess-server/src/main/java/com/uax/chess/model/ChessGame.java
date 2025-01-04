package com.uax.chess.model;

import com.uax.chess.controller.Ficha;
import com.uax.chess.controller.Tablero;

public class ChessGame {

    private Tablero tablero;
    private Integer id = 0;

    public ChessGame() {
        this.tablero = new Tablero();
        this.id = 0;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
