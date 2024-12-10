package fichas.tipos;

import fichas.Ficha;
import fichas.TiposColor;

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
