package fichas.tipos;

import fichas.Ficha;
import fichas.TiposColor;

import static juego.Tablero.turno;

public class Peon extends Ficha {

    public Peon(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if((columnOrigen == columnDestino || columnOrigen == (columnDestino + 1) ||
                columnOrigen == (columnDestino - 1)) && columnDestino < 8){
            if (turno == 1){
                if(filaOrigen == (filaDestino + 1)) {
                    return true;
                } else if(filaOrigen == 6 && filaOrigen == filaDestino + 2){
                    if(columnOrigen == (columnDestino + 1) || columnOrigen == (columnDestino - 1)){
                        return false;
                    }
                    return true;
                }

            }else if (turno == 2){
                if(filaOrigen == (filaDestino - 1)) {
                    return true;
                } else if(filaOrigen == 1 && filaOrigen == filaDestino - 2){
                    if(columnOrigen == (columnDestino + 1) || columnOrigen == (columnDestino - 1)){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♙";
        }else {
            return "♟";
        }
    }

    @Override
    public int prioridad() {
        return 6;
    }

}
