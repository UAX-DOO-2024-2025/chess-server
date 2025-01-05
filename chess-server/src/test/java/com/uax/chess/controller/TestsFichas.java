package com.uax.chess.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FichaTest {
    private Ficha fichaBlanca;
    private Ficha fichaNegra;

    @BeforeEach
    void setUp() {
        fichaBlanca = new Ficha(TiposColor.BLANCO) {
            @Override
            public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Tablero tablero) {
                return true;
            }

            @Override
            public char obtenerRepresentacion() {
                return 'B';
            }

            @Override
            protected int getOrdenPrioridad() {
                return 1;
            }
        };

        fichaNegra = new Ficha(TiposColor.NEGRO) {
            @Override
            public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Tablero tablero) {
                return true;
            }

            @Override
            public char obtenerRepresentacion() {
                return 'N';
            }

            @Override
            protected int getOrdenPrioridad() {
                return 2;
            }
        };
    }

    @Test
    void compareToDifferentColors() {
        assertTrue(fichaBlanca.compareTo(fichaNegra) < 0);
        assertTrue(fichaNegra.compareTo(fichaBlanca) > 0);
    }

    @Test
    void compareToSameColorDifferentPriority() {
        Ficha anotherFichaBlanca = new Ficha(TiposColor.BLANCO) {
            @Override
            public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Tablero tablero) {
                return true;
            }

            @Override
            public char obtenerRepresentacion() {
                return 'B';
            }

            @Override
            protected int getOrdenPrioridad() {
                return 2;
            }
        };

        assertTrue(fichaBlanca.compareTo(anotherFichaBlanca) < 0);
        assertTrue(anotherFichaBlanca.compareTo(fichaBlanca) > 0);
    }

    @Test
    void compareToSameColorSamePriority() {
        Ficha anotherFichaBlanca = new Ficha(TiposColor.BLANCO) {
            @Override
            public boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Tablero tablero) {
                return true;
            }

            @Override
            public char obtenerRepresentacion() {
                return 'B';
            }

            @Override
            protected int getOrdenPrioridad() {
                return 1;
            }
        };

        assertEquals(0, fichaBlanca.compareTo(anotherFichaBlanca));
    }

    @Test
    void toStringRepresentation() {
        assertEquals("Ficha: B Color: BLANCO", fichaBlanca.toString());
        assertEquals("Ficha: N Color: NEGRO", fichaNegra.toString());
    }

    @Test
    void setColorChangesColor() {
        fichaBlanca.setColor(TiposColor.NEGRO);
        assertEquals(TiposColor.NEGRO, fichaBlanca.getColor());
    }
}