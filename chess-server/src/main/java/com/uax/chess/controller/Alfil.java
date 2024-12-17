package com.uax.chess.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uax.chess.controller.Ficha;

import java.io.IOException;

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
        return getColor() == TiposColor.BLANCO ? 'A' : 'a';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 5; // Menor prioridad que Torre y Reina
    }

}
