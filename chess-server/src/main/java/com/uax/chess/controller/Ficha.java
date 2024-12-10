package com.uax.chess.controller;

import com.uax.chess.controller.Color;

public abstract class Ficha implements Color, Comparable<Ficha> {
    private TiposColor color;

    public Ficha(TiposColor color) {
        this.color = color;
    }

    @Override
    public void setColor(TiposColor color) {
        this.color = color;
    }

    @Override
    public TiposColor getColor() {
        return color;
    }

    public abstract boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero);

    public abstract char obtenerRepresentacion();

    @Override
    public String toString() {
        return "Ficha: " + obtenerRepresentacion() + " Color: " + color;
    }

    @Override
    public int compareTo(Ficha o) {
        if (this.color != o.color) {
            return this.color == TiposColor.BLANCO ? -1 : 1;
        }
        return Integer.compare(getOrdenPrioridad(), o.getOrdenPrioridad());
    }

    protected abstract int getOrdenPrioridad();

    public void mover() {
    }
}
