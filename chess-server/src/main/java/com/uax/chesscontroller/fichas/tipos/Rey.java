package fichas.tipos;

import fichas.Ficha;
import fichas.TiposColor;

public class Rey extends Ficha {
    public Rey(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if(((filaOrigen + 1) == filaDestino || (filaOrigen - 1) == filaDestino) && (columnOrigen == columnDestino ||
                (columnOrigen +1) == columnDestino || (columnOrigen -1) == columnDestino)) {
            return true;
        } else if (filaOrigen == filaDestino && ((columnOrigen + 1) == columnDestino || (columnOrigen - 1) == columnDestino)) {
            return true;
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♔";
        }else {
            return "♚";
        }
    }

    @Override
    public int prioridad() {
        return 1;
    }
}
