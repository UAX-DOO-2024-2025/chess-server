package fichas.tipos;

import fichas.Ficha;
import fichas.TiposColor;

public class Torre extends Ficha {
    public Torre(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino) {
        if(columnOrigen == columnDestino || filaOrigen == filaDestino){
            return true;
        }
        return false;
    }

    @Override
    public String obtenerRepresentacion() {
        if (getColor()== TiposColor.BLANCO){
            return "♖";
        }else {
            return "♜";
        }
    }

    @Override
    public int prioridad() {
        return 5;
    }
}
