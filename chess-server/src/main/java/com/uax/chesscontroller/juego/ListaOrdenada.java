package juego;

import java.util.ArrayList;
import java.util.Collections;

public class ListaOrdenada <T extends Comparable<T>>{
    private ArrayList<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
        Collections.sort(elementos);
    }

    public void eliminar(T elemento) {
        elementos.remove(elemento);
    }

    public ArrayList<T> obtenerElementos() {
        return elementos;
    }
}
