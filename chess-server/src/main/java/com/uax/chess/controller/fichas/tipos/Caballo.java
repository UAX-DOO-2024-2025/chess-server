package com.uax.chess.controller.fichas.tipos;

import com.uax.chess.controller.fichas.Ficha;
import com.uax.chess.controller.fichas.TiposColor;

public class Caballo extends Ficha {
    public Caballo(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if((filaOrigen + 2) == filaDestino && (columnOrigen + 1) == columnDestino || (columnOrigen - 1) == columnDestino) {
            return true;
        } else if ((columnOrigen + 2) == columnDestino && (filaOrigen + 1) == filaDestino || (filaOrigen - 1) == filaDestino) {
            return true;
        } else if ((filaOrigen - 2) == filaDestino && (columnOrigen + 1) == columnDestino || (columnOrigen - 1) == columnDestino) {
            return true;
        } else if ((columnOrigen - 2) == columnDestino && (filaOrigen + 1) == filaDestino || (filaOrigen - 1) == filaDestino) {
            return true;
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♘";
        }else {
            return "♞";
        }
    }

    @Override
    public int prioridad() {
        return 4;
    }
}
