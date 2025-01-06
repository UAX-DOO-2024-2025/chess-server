package com.uax.chess.controller;

import com.uax.chess.controller.Ficha;

public class Alfil extends Ficha {

    public Alfil(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        int diferenciaFila = Math.abs(filaDestino - filaOrigen);
        int diferenciaColumna = Math.abs(columnaDestino - columnaOrigen);
        return diferenciaFila == diferenciaColumna;
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? '♗' : ♝';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 5; // Menor prioridad que Torre y Reina
    }
}
