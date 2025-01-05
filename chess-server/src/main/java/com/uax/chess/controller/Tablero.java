package com.uax.chess.controller;

import java.util.Scanner;

public class Tablero {
    private static Tablero instancia;
    private Ficha[][] celdas;

    public Tablero() {
        celdas = new Ficha[8][8];
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Peones
        for (int i = 0; i < 8; i++) {
            celdas[1][i] = new Peon(TiposColor.NEGRO); // Peones negros
            celdas[6][i] = new Peon(TiposColor.BLANCO); // Peones blancos
        }

        // Torres
        celdas[0][0] = new Torre(TiposColor.NEGRO);
        celdas[0][7] = new Torre(TiposColor.NEGRO);
        celdas[7][0] = new Torre(TiposColor.BLANCO);
        celdas[7][7] = new Torre(TiposColor.BLANCO);

        // Caballos
        celdas[0][1] = new Caballo(TiposColor.NEGRO);
        celdas[0][6] = new Caballo(TiposColor.NEGRO);
        celdas[7][1] = new Caballo(TiposColor.BLANCO);
        celdas[7][6] = new Caballo(TiposColor.BLANCO);

        // Alfiles
        celdas[0][2] = new Alfil(TiposColor.NEGRO);
        celdas[0][5] = new Alfil(TiposColor.NEGRO);
        celdas[7][2] = new Alfil(TiposColor.BLANCO);
        celdas[7][5] = new Alfil(TiposColor.BLANCO);

        // Reinas
        celdas[0][3] = new Reina(TiposColor.NEGRO);
        celdas[7][3] = new Reina(TiposColor.BLANCO);

        // Reyes
        celdas[0][4] = new Rey(TiposColor.NEGRO);
        celdas[7][4] = new Rey(TiposColor.BLANCO);
    }

    public static Tablero getInstance() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    public Ficha getCelda(int fila, int columna) {
        if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
            throw new IllegalArgumentException("Coordenadas fuera del tablero");
        }
        return celdas[fila][columna];
    }

    public void setCelda(int fila, int columna, Ficha ficha) {
        if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
            throw new IllegalArgumentException("Coordenadas fuera del tablero");
        }
        celdas[fila][columna] = ficha;
    }

    public boolean moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        if (!posicionValida(filaOrigen, columnaOrigen) || !posicionValida(filaDestino, columnaDestino)) {
            return false;
        }

        Ficha ficha = getCelda(filaOrigen, columnaOrigen);
        if (ficha == null || !ficha.validarMovimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino, this)) {
            return false;
        }

        // Manejar enroque
        if (ficha instanceof Rey && Math.abs(columnaDestino - columnaOrigen) == 2) {
            if (manejarEnroque(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                return true;
            } else {
                return false; // Enroque inválido
            }
        }

        // Simular el movimiento del rey u otra pieza
        Ficha temporal = getCelda(filaDestino, columnaDestino);
        setCelda(filaDestino, columnaDestino, ficha);
        setCelda(filaOrigen, columnaOrigen, null);

        // Verificar si el movimiento deja al rey en jaque
        if (estaEnJaque(ficha.getColor())) {
            // Revertir el movimiento
            setCelda(filaOrigen, columnaOrigen, ficha);
            setCelda(filaDestino, columnaDestino, temporal);
            return false;
        }

        // Manejar promoción de peón
        if (ficha instanceof Peon && (filaDestino == 0 || filaDestino == 7)) {
            manejarPromocionPeon(filaDestino, columnaDestino);
        }

        // Marcar que la ficha ha sido movida
        ficha.mover();

        return true;
    }

    private boolean manejarEnroque(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Rey rey = (Rey) getCelda(filaOrigen, columnaOrigen);
        if (rey == null || rey.seHaMovido()) {
            return false; // El rey ya se ha movido
        }

        int direccion = (columnaDestino > columnaOrigen) ? 1 : -1;
        int torreColumna = (direccion == 1) ? 7 : 0;
        Ficha torre = getCelda(filaOrigen, torreColumna);

        if (!(torre instanceof Torre) || ((Torre) torre).seHaMovido()) {
            return false; // La torre ya se ha movido o no es una torre
        }

        // Verificar que no haya piezas entre el rey y la torre
        for (int col = columnaOrigen + direccion; col != torreColumna; col += direccion) {
            if (getCelda(filaOrigen, col) != null) {
                return false; // Hay obstrucciones
            }
        }

        // Verificar que el rey no pase por casillas amenazadas
        for (int col = columnaOrigen; col != columnaDestino + direccion; col += direccion) {
            if (estaCasillaAmenazada(filaOrigen, col, rey.getColor())) {
                return false; // El rey pasa por o termina en una casilla amenazada
            }
        }

        // Realizar el enroque
        int nuevaTorreColumna = columnaOrigen + direccion;
        setCelda(filaOrigen, columnaDestino, rey);
        setCelda(filaOrigen, columnaOrigen, null);
        setCelda(filaOrigen, nuevaTorreColumna, torre);
        setCelda(filaOrigen, torreColumna, null);

        rey.mover();
        ((Torre) torre).mover();

        return true;
    }

    private boolean estaCasillaAmenazada(int fila, int columna, TiposColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Ficha ficha = getCelda(i, j);
                if (ficha != null && ficha.getColor() != color) {
                    if (ficha.validarMovimiento(i, j, fila, columna, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hayObstruccion(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        int incrementoFila = Integer.compare(filaDestino, filaOrigen);
        int incrementoColumna = Integer.compare(columnaDestino, columnaOrigen);

        int fila = filaOrigen + incrementoFila;
        int columna = columnaOrigen + incrementoColumna;

        while (fila != filaDestino || columna != columnaDestino) {
            if (getCelda(fila, columna) != null) {
                return true; // Hay una obstrucción
            }
            fila += incrementoFila;
            columna += incrementoColumna;
        }

        return false; // No hay obstrucción
    }

    private boolean posicionValida(int fila, int columna) {
        return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
    }

    private void manejarCapturaAlPaso(Peon peon, int filaOrigen, int columnaOrigen, int filaDestino,
            int columnaDestino) {
        if (peon.isCapturaPaso() && Math.abs(columnaDestino - columnaOrigen) == 1) {
            int filaCaptura = filaOrigen + (peon.getColor() == TiposColor.BLANCO ? -1 : 1);
            if (posicionValida(filaCaptura, columnaDestino)) {
                setCelda(filaCaptura, columnaDestino, null);
            }
        }
    }

    public boolean estaEnJaque(TiposColor colorRey) {
        int[] posicionRey = buscarRey(colorRey);
        if (posicionRey == null) {
            return false; // Si no hay rey, no puede estar en jaque
        }

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Ficha ficha = getCelda(fila, columna);
                // Solo considerar piezas enemigas
                if (ficha != null && ficha.getColor() != colorRey) {
                    // Si es un caballo, no necesita verificar obstrucciones
                    if (ficha instanceof Caballo) {
                        if (ficha.validarMovimiento(fila, columna, posicionRey[0], posicionRey[1], this)) {
                            return true; // Rey está amenazado por un caballo
                        }
                    } else {
                        // Verificar línea de visión y obstrucciones
                        if (ficha.validarMovimiento(fila, columna, posicionRey[0], posicionRey[1], this) &&
                                !hayObstruccion(fila, columna, posicionRey[0], posicionRey[1])) {
                            return true; // Rey está amenazado por una pieza con línea de visión directa
                        }
                    }
                }
            }
        }
        return false; // No hay amenazas al rey
    }

    private int[] buscarRey(TiposColor colorRey) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Ficha ficha = getCelda(fila, columna);
                if (ficha instanceof Rey && ficha.getColor() == colorRey) {
                    return new int[] { fila, columna };
                }
            }
        }
        return null; // No se encontró el rey
    }

    public boolean esJaqueMate(TiposColor colorRey) {
        // Verifica primero si el rey está en jaque
        if (!estaEnJaque(colorRey)) {
            return false; // Si no está en jaque, no puede ser jaque mate
        }

        // Recorre todas las piezas del mismo color
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Ficha ficha = getCelda(fila, columna);
                if (ficha != null && ficha.getColor() == colorRey) {
                    // Intenta mover la pieza a cada casilla del tablero
                    for (int destinoFila = 0; destinoFila < 8; destinoFila++) {
                        for (int destinoColumna = 0; destinoColumna < 8; destinoColumna++) {
                            if (ficha.validarMovimiento(fila, columna, destinoFila, destinoColumna, this)) {
                                // Simula el movimiento
                                Ficha temporal = getCelda(destinoFila, destinoColumna);
                                setCelda(destinoFila, destinoColumna, ficha);
                                setCelda(fila, columna, null);

                                // Verifica si el rey sigue en jaque después del movimiento
                                boolean enJaque = estaEnJaque(colorRey);

                                // Revertir el movimiento
                                setCelda(fila, columna, ficha);
                                setCelda(destinoFila, destinoColumna, temporal);

                                if (!enJaque) {
                                    return false; // Si hay al menos un movimiento válido, no es jaque mate
                                }
                            }
                        }
                    }
                }
            }
        }

        // Si no hay movimientos válidos, es jaque mate
        return true;
    }

    private void manejarPromocionPeon(int filaDestino, int columnaDestino) {
        Ficha ficha = getCelda(filaDestino, columnaDestino);

        if (ficha instanceof Peon && (filaDestino == 0 || filaDestino == 7)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "Promoción de peón. Elija una pieza para promocionar: (Q) Reina, (R) Torre, (B) Alfil, (N) Caballo:");
            String eleccion = scanner.nextLine().toUpperCase();

            Ficha nuevaFicha;
            switch (eleccion) {
                case "Q":
                    nuevaFicha = new Reina(ficha.getColor());
                    break;
                case "R":
                    nuevaFicha = new Torre(ficha.getColor());
                    break;
                case "B":
                    nuevaFicha = new Alfil(ficha.getColor());
                    break;
                case "N":
                    nuevaFicha = new Caballo(ficha.getColor());
                    break;
                default:
                    System.out.println("Selección inválida. El peón será promovido a reina por defecto.");
                    nuevaFicha = new Reina(ficha.getColor());
                    break;
            }

            // Colocar la nueva pieza en la posición del peón
            setCelda(filaDestino, columnaDestino, nuevaFicha);
        }
    }
}
