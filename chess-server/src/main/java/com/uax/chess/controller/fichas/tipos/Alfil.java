package com.uax.chess.controller.fichas.tipos;

import com.uax.chess.controller.fichas.Ficha;
import com.uax.chess.controller.fichas.TiposColor;

public class Alfil extends Ficha {
    public Alfil(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if(Math.abs(filaDestino - filaOrigen) == Math.abs(columnDestino - columnOrigen)){
            return true;
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♗";
        }else {
            return "♝";
        }
    }

    @Override
    public int prioridad() {
        return 3;
    }
}
