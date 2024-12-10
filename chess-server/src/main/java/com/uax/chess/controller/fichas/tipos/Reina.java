package com.uax.chess.controller.fichas.tipos;

import com.uax.chess.controller.fichas.Ficha;
import com.uax.chess.controller.fichas.TiposColor;

public class Reina extends Ficha {
    public Reina(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if(((filaOrigen + 1) == filaDestino || (filaOrigen - 1) == filaDestino) && (columnOrigen == columnDestino ||
                (columnOrigen +1) == columnDestino || (columnOrigen -1) == columnDestino)) {
            return true;
        } else if (filaOrigen == filaDestino && ((columnOrigen + 1) == columnDestino || (columnOrigen - 1) == columnDestino)) {
            return true;
        } else if(Math.abs(filaDestino - filaOrigen) == Math.abs(columnDestino - columnOrigen)){
            return true;
        } else if(columnOrigen == columnDestino || filaOrigen == filaDestino){
            return true;
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♕";
        }else {
            return "♛";
        }
    }

    @Override
    public int prioridad() {
        return 2;
    }
}