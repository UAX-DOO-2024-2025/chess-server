package com.uax.chess.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableroTest {
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = Tablero.getInstance();
    }
    void singletonInstance() {
        Tablero anotherInstance = Tablero.getInstance();
        assertSame(tablero, anotherInstance);
    }

    void initialBoardSetup() {
        assertNotNull(tablero.getCelda(0, 0));
        assertTrue(tablero.getCelda(0, 0) instanceof Torre);
        assertTrue(tablero.getCelda(1, 0) instanceof Peon);
        assertTrue(tablero.getCelda(7, 7) instanceof Torre);
        assertTrue(tablero.getCelda(6, 7) instanceof Peon);
    }

    void movePieceSuccessfully() {
        assertTrue(tablero.moverFicha(1, 0, 3, 0));
        assertNull(tablero.getCelda(1, 0));
        assertTrue(tablero.getCelda(3, 0) instanceof Peon);
    }

    void movePieceOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> tablero.moverFicha(1, 0, 8, 0));
    }

    void movePieceToOccupiedSquare() {
        assertFalse(tablero.moverFicha(1, 0, 1, 1));
    }

    void movePieceInvalidMove() {
        assertFalse(tablero.moverFicha(1, 0, 4, 0));
    }

    void checkForCheck() {
        tablero.moverFicha(1, 4, 3, 4);
        tablero.moverFicha(6, 5, 4, 5);
        assertTrue(tablero.estaEnJaque(TiposColor.NEGRO));
    }

    void checkForCheckmate() {
        tablero.moverFicha(1, 5, 2, 5);
        tablero.moverFicha(6, 4, 4, 4);
        tablero.moverFicha(0, 3, 4, 7);
        assertTrue(tablero.esJaqueMate(TiposColor.NEGRO));
    }

    void handlePawnPromotion() {
        tablero.setCelda(6, 0, null);
        tablero.setCelda(1, 0, new Peon(TiposColor.BLANCO));
        assertTrue(tablero.moverFicha(1, 0, 0, 0));
        assertTrue(tablero.getCelda(0, 0) instanceof Reina);
    }

    // New tests...

    void movePieceToEmptySquare() {
        assertTrue(tablero.moverFicha(1, 0, 2, 0));
        assertNull(tablero.getCelda(1, 0));
        assertTrue(tablero.getCelda(2, 0) instanceof Peon);
    }

    void movePieceToSameColorOccupiedSquare() {
        assertFalse(tablero.moverFicha(1, 0, 1, 1));
    }

    void movePieceToDifferentColorOccupiedSquare() {
        tablero.setCelda(2, 0, new Peon(TiposColor.NEGRO));
        assertTrue(tablero.moverFicha(1, 0, 2, 0));
        assertNull(tablero.getCelda(1, 0));
        assertTrue(tablero.getCelda(2, 0) instanceof Peon);
        assertEquals(TiposColor.BLANCO, tablero.getCelda(2, 0).getColor());
    }

    void movePieceInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> tablero.moverFicha(1, 0, 9, 0));
    }

    void movePieceToSamePosition() {
        assertFalse(tablero.moverFicha(1, 0, 1, 0));
    }

    void movePieceThroughObstruction() {
        tablero.setCelda(2, 0, new Peon(TiposColor.BLANCO));
        assertFalse(tablero.moverFicha(1, 0, 3, 0));
    }

    void movePieceIntoCheck() {
        tablero.setCelda(1, 4, null);
        tablero.setCelda(6, 4, null);
        tablero.setCelda(4, 4, new Reina(TiposColor.NEGRO));
        assertFalse(tablero.moverFicha(7, 4, 6, 4));
    }

    void movePieceOutOfCheck() {
        tablero.setCelda(1, 4, null);
        tablero.setCelda(6, 4, null);
        tablero.setCelda(4, 4, new Reina(TiposColor.NEGRO));
        assertTrue(tablero.moverFicha(7, 4, 7, 5));
    }

    void handleEnPassantCapture() {
        tablero.setCelda(4, 3, new Peon(TiposColor.BLANCO));
        tablero.setCelda(3, 4, new Peon(TiposColor.NEGRO));
        assertTrue(tablero.moverFicha(4, 3, 3, 4));
        assertNull(tablero.getCelda(4, 3));
        assertTrue(tablero.getCelda(3, 4) instanceof Peon);
        assertEquals(TiposColor.BLANCO, tablero.getCelda(3, 4).getColor());
    }

    void handleCastling() {
        tablero.setCelda(0, 4, new Rey(TiposColor.NEGRO));
        tablero.setCelda(0, 7, new Torre(TiposColor.NEGRO));
        assertTrue(tablero.moverFicha(0, 4, 0, 6));
        assertTrue(tablero.getCelda(0, 5) instanceof Torre);
        assertTrue(tablero.getCelda(0, 6) instanceof Rey);
        assertNull(tablero.getCelda(0, 4));
        assertNull(tablero.getCelda(0, 7));
    }

}