package fichas;

public abstract class Ficha implements Color, Comparable<Ficha>{
    private TiposColor color;


    public Ficha(TiposColor color) {
        this.color = color;
    }

    public abstract boolean validarMovimiento(int filaOrigen, int filaDestino, int columnOrigen, int columnDestino);
    public abstract String obtenerRepresentacion();

    @Override
    public void setColor(TiposColor color) {
        this.color = color;
    }

    @Override
    public TiposColor getColor() {
        return color;
    }

    public abstract int prioridad();
    @Override
    public int compareTo(Ficha otro) {
        if (this.color != otro.color) {
            return this.color == TiposColor.BLANCO ? -1 : 1;
        }

        return prioridad() - otro.prioridad();
    }

    @Override
    public String toString() {
        return obtenerRepresentacion();
    }
}
