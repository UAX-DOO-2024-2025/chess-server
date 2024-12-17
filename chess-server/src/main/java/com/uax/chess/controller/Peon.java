package com.uax.chess.controller;

import com.uax.chess.controller.Ficha;

public class Peon extends Ficha {

    private boolean capturaPaso = false;

    public Peon(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        int direccion = getColor() == TiposColor.BLANCO ? -1 : 1;
        capturaPaso = false;


        // Movimiento estándar
        if (columnaOrigen == columnaDestino && filaDestino - filaOrigen == direccion &&
                tablero.getCelda(filaDestino, columnaDestino) == null) {
            return true;
        }

        // Movimiento inicial de dos casillas
        if (columnaOrigen == columnaDestino && filaDestino - filaOrigen == 2 * direccion &&
                filaOrigen == (getColor() == TiposColor.BLANCO ? 6 : 1) &&
                tablero.getCelda(filaDestino, columnaDestino) == null &&
                tablero.getCelda(filaOrigen + direccion, columnaDestino) == null) {
            capturaPaso = true;
            return true;
        }

        // Captura diagonal
        if (Math.abs(columnaDestino - columnaOrigen) == 1 && filaDestino - filaOrigen == direccion &&
                tablero.getCelda(filaDestino, columnaDestino) != null &&
                tablero.getCelda(filaDestino, columnaDestino).getColor() != getColor()) {
            return true;
        }

        return false;
    }

    public boolean isCapturaPaso() {
        return capturaPaso;
    }

    public void resetearCapturaPaso() {
        capturaPaso = false;
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? 'P' : 'p';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 6; // Mínima prioridad
    }
}