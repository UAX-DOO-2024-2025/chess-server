package juego;

public class ValidacionPosicion extends RuntimeException {
    public ValidacionPosicion(String message) {
        super(message);
    }

    public static void validar(String posicion) {
        if (!posicion.matches("[a-hA-H][1-8]")) {
            throw new ValidacionPosicion("La posición " + posicion + " no es válida");
        }
    }
}
