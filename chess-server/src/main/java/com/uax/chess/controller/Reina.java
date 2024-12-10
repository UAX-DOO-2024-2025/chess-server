import com.uax.chess.controller.Ficha;

public class Reina extends Ficha {

    public Reina(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        // Movimiento válido para Reina: combinación de Torre y Alfil
        boolean esMovimientoValido = new Torre(getColor()).validarMovimiento(filaOrigen, columnaOrigen, filaDestino,
                columnaDestino, tablero) ||
                new Alfil(getColor()).validarMovimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino,
                        tablero);

        // Verificar si la casilla de destino está ocupada por una pieza del mismo color
        Ficha destino = tablero.getCelda(filaDestino, columnaDestino);
        if (destino != null && destino.getColor() == this.getColor()) {
            return false;
        }

        return esMovimientoValido;
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? 'Q' : 'q';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 2; // Alta prioridad después del Rey
    }
}
