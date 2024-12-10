package com.uax.chess.controller.juego;

import com.uax.chess.controller.fichas.Color;
import com.uax.chess.controller.fichas.Ficha;
import com.uax.chess.controller.fichas.TiposColor;
import com.uax.chess.controller.fichas.tipos.*;


public class Jugador implements Color {
    private String nombre;
    private TiposColor color;

    private ListaOrdenada<Ficha> fichasEnJuego;
    private ListaOrdenada<Ficha> fichasComidas;

    public Jugador(String nombre, TiposColor color) {
        this.nombre = nombre;
        this.color = color;
        fichasEnJuego = new ListaOrdenada<>();
        fichasComidas = new ListaOrdenada<>();

        for (int i = 0; i < 8; i++) {
            fichasEnJuego.agregar(new Peon(color));
        }
        for (int i = 0; i < 2; i++) {
            fichasEnJuego.agregar(new Torre(color));
            fichasEnJuego.agregar(new Caballo(color));
            fichasEnJuego.agregar(new Alfil(color));
        }
        fichasEnJuego.agregar(new Reina(color));
        fichasEnJuego.agregar(new Rey(color));
    }

    public void fichaComida(Object ficha) {
        for(Ficha i : fichasEnJuego.obtenerElementos()){
            if(ficha.equals(i)){
                fichasEnJuego.eliminar(i);
                fichasComidas.agregar(i);
                break;
            }
        }
    }

    @Override
    public void setColor(TiposColor color) {
        this.color = color;
    }

    @Override
    public TiposColor getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaOrdenada<Ficha> getFichasEnJuego() {
        return fichasEnJuego;
    }

    public void setFichasEnJuego(ListaOrdenada<Ficha> fichasEnJuego) {
        this.fichasEnJuego = fichasEnJuego;
    }

    public ListaOrdenada<Ficha> getFichasComidas() {
        return fichasComidas;
    }

    public void setFichasComidas(ListaOrdenada<Ficha> fichasComidas) {
        this.fichasComidas = fichasComidas;
    }

    @Override
    public String toString() {
        return  "≡ " + nombre + ":" + fichasComidas.obtenerElementos();
    }
}
