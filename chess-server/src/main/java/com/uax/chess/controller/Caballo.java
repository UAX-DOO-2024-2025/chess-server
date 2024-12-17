package com.uax.chess.controller;

import com.uax.chess.controller.Ficha;

public class Caballo extends Ficha {

    public Caballo(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        int diferenciaFila = Math.abs(filaDestino - filaOrigen);
        int diferenciaColumna = Math.abs(columnaDestino - columnaOrigen);

        return (diferenciaFila == 2 && diferenciaColumna == 1) || (diferenciaFila == 1 && diferenciaColumna == 2);
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? '♘' : '♞';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 4; // Menor prioridad que Alfil y Torre
    }
}
