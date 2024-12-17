package com.uax.chess.controller;

import com.uax.chess.controller.Ficha;

public class Torre extends Ficha {

    private boolean enroquePermitido = true;

    public Torre(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        // Movimiento en línea recta (horizontal o vertical)
        if (filaOrigen != filaDestino && columnaOrigen != columnaDestino) {
            return false;
        }

        // Verificar si hay obstrucción en el camino
        if (tablero.hayObstruccion(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
            return false;
        }

        // Verificar si la casilla de destino está ocupada por una pieza del mismo color
        Ficha destino = tablero.getCelda(filaDestino, columnaDestino);
        if (destino != null && destino.getColor() == this.getColor()) {
            return false;
        }

        return true;
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? '♖' : '♜';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 3; // Prioridad de torre
    }

    public boolean estaEnroquePermitido() {
        return enroquePermitido;
    }

    public void mover() {
        enroquePermitido = false;
    }
}
