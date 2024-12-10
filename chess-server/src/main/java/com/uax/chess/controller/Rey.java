import com.uax.chess.controller.Ficha;

public class Rey extends Ficha {

    private boolean enroquePermitido = true;

    public Rey(TiposColor color) {
        super(color);
    }

    @Override
    public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero) {
        // Validar enroque
        if (Math.abs(columnaDestino - columnaOrigen) == 2 && filaOrigen == filaDestino && enroquePermitido) {
            return validarEnroque(filaOrigen, columnaOrigen, columnaDestino, tablero);
        }

        // Movimiento normal del rey: una casilla en cualquier dirección
        return Math.abs(filaDestino - filaOrigen) <= 1 && Math.abs(columnaDestino - columnaOrigen) <= 1;
    }

    private boolean validarEnroque(int fila, int columnaOrigen, int columnaDestino, Tablero tablero) {
        int incremento = columnaDestino > columnaOrigen ? 1 : -1;

        // Validar que no haya piezas entre el rey y la torre
        for (int c = columnaOrigen + incremento; c != columnaDestino; c += incremento) {
            if (tablero.getCelda(fila, c) != null) {
                return false;
            }
        }

        // Validar que el rey no pase por jaque (simulación necesaria)
        for (int c = columnaOrigen; c != columnaDestino + incremento; c += incremento) {
            tablero.setCelda(fila, c, this); // Simula que el rey se mueve a esta posición
            if (tablero.estaEnJaque(getColor())) {
                tablero.setCelda(fila, c, null); // Revertir simulación
                return false;
            }
            tablero.setCelda(fila, c, null); // Limpia después de la simulación
        }

        return true;
    }

    @Override
    public char obtenerRepresentacion() {
        return getColor() == TiposColor.BLANCO ? 'K' : 'k';
    }

    @Override
    protected int getOrdenPrioridad() {
        return 1;
    }

    public void mover() {
        enroquePermitido = false;
    }
}
