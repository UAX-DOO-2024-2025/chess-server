import java.util.Scanner;

public class JuegoAjedrez {

    public static void main(String[] args) {
        Tablero tablero = Tablero.getInstance();
        Scanner scanner = new Scanner(System.in);

        TiposColor turnoActual = TiposColor.BLANCO;

        System.out.println("Bienvenido al Juego de Ajedrez");
        System.out.println("Formato de movimiento: filaOrigen,columnaOrigen filaDestino,columnaDestino (Ej: 6,4 4,4)");
        System.out.println("Introduce 'salir' para terminar el juego.\n");

        while (true) {
            imprimirTablero(tablero);

            System.out.print("Turno actual: ");
            System.out.println(turnoActual == TiposColor.BLANCO ? "Blancas" : "Negras");

            if (tablero.estaEnJaque(turnoActual)) {
                System.out.println("¡Cuidado! Tu Rey está en jaque.");
                if (tablero.esJaqueMate(turnoActual)) {
                    System.out.println("¡Jaque Mate! El juego ha terminado.");
                    System.out.println(turnoActual == TiposColor.BLANCO ? "Ganan las Negras" : "Ganan las Blancas");
                    break;
                }
            }

            System.out.print("Ingrese su movimiento: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Gracias por jugar. ¡Hasta pronto!");
                break;
            }

            try {
                // Parsear la entrada
                String[] partes = entrada.split(" ");
                String[] origen = partes[0].split(",");
                String[] destino = partes[1].split(",");

                int filaOrigen = Integer.parseInt(origen[0]);
                int columnaOrigen = Integer.parseInt(origen[1]);
                int filaDestino = Integer.parseInt(destino[0]);
                int columnaDestino = Integer.parseInt(destino[1]);

                Ficha ficha = tablero.getCelda(filaOrigen, columnaOrigen);

                // Verificar si la ficha pertenece al jugador actual
                if (ficha == null || ficha.getColor() != turnoActual) {
                    System.out.println("Movimiento inválido. No puedes mover las piezas del oponente.\n");
                    continue;
                }

                if (tablero.moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                    System.out.println("Movimiento realizado correctamente.\n");
                    // Cambiar de turno
                    turnoActual = (turnoActual == TiposColor.BLANCO) ? TiposColor.NEGRO : TiposColor.BLANCO;
                } else {
                    System.out.println("Movimiento inválido. Inténtalo de nuevo.\n");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Asegúrate de seguir el formato indicado.\n");
            }
        }

    }

    private static void imprimirTablero(Tablero tablero) {
        System.out.println("\nEstado actual del tablero:");
        System.out.println("   0 1 2 3 4 5 6 7");
        System.out.println("  -----------------");
        for (int fila = 0; fila < 8; fila++) {
            System.out.print(fila + " |");
            for (int columna = 0; columna < 8; columna++) {
                Ficha ficha = tablero.getCelda(fila, columna);
                if (ficha != null) {
                    System.out.print(" " + ficha.obtenerRepresentacion());
                } else {
                    System.out.print(" .");
                }
            }
            System.out.println(" |");
        }
        System.out.println("  -----------------");
    }
}
