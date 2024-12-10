package juego;

import fichas.Ficha;
import fichas.tipos.*;


public class Tablero {
    private Object[][] tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    public static int turno = 1; //Determinara si las piezas que se mueven son del jugador 1 o 2
    public static boolean victoria = false;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;

        this.tablero = new Object[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = " ";
            }
        }

        //Posicionar Fichas de jugador 1
        int i = 0;
        for (Ficha ficha : jugador1.getFichasEnJuego().obtenerElementos()) {
            if (ficha instanceof Peon) {
                tablero[6][i] = ficha;
                i++;
            } else if (ficha instanceof Torre) {
                if(i == 0){
                    tablero[7][0] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[7][7] = ficha;
                    i = 0;
                }
            } else if (ficha instanceof Caballo) {
                if(i == 0){
                    tablero[7][1] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[7][6] = ficha;
                    i = 0;
                }
            }else if (ficha instanceof Alfil) {
                if(i == 0){
                    tablero[7][2] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[7][5] = ficha;
                    i = 0;
                }
            }else if (ficha instanceof Reina) {
                tablero[7][3] = ficha;
            }else if (ficha instanceof Rey) {
                tablero[7][4] = ficha;
            }
        }

        //Posicionar Fichas de jugador 2
        i = 0;
        for (Ficha ficha : jugador2.getFichasEnJuego().obtenerElementos()) {
            if (ficha instanceof Peon) {
                tablero[1][i] = ficha;
                i++;
            } else if (ficha instanceof Torre) {
                if(i == 0){
                    tablero[0][0] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[0][7] = ficha;
                    i = 0;
                }
            } else if (ficha instanceof Caballo) {
                if(i == 0){
                    tablero[0][1] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[0][6] = ficha;
                    i = 0;
                }
            }else if (ficha instanceof Alfil) {
                if(i == 0){
                    tablero[0][2] = ficha;
                    i++;
                } else if (i == 1){
                    tablero[0][5] = ficha;
                    i = 0;
                }
            }else if (ficha instanceof Reina) {
                tablero[0][4] = ficha;
            }else if (ficha instanceof Rey) {
                tablero[0][3] = ficha;
            }
        }
    }

    public void mostrarTablero() {
        System.out.println("    A\u3000  B\u3000  C\u3000  D\u3000  E\u3000  F\u3000  G\u3000  H");
        System.out.println("  ╔═\u3000═╦═\u3000═╦═\u3000═╦═\u3000═╦═\u3000═╦═\u3000═╦═\u3000═╦═\u3000═╗");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 8; j++) {
                System.out.print(" ║ ");
                if (tablero[i][j] == " ") {
                    System.out.print("\u3000");
                } else if (tablero[i][j] == "x") {
                    System.out.print("\uFF4F");
                } else {
                    System.out.print(tablero[i][j]);
                }
            }
            if(i == 1){
                System.out.println(" ║" + "  == FICHAS COMIDAS ==");
            } else if (i == 6) {
                System.out.println(" ║  " + jugador1.toString());
            } else{
                System.out.println(" ║");
            }
            if(i != 7 && i != 1 && i != 5){
                System.out.println("  ╠═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╣");
            } else if (i == 1){
                System.out.println("  ╠═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╣  " + jugador2.toString());
            } else if (i == 5){
                System.out.println("  ╠═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╬═\u3000═╣  " + "== FICHAS COMIDAS ==");
            }
            else {
                System.out.println("  ╚═\u3000═╩═\u3000═╩═\u3000═╩═\u3000═╩═\u3000═╩═\u3000═╩═\u3000═╩═\u3000═╝");
                System.out.println("    A\u3000  B\u3000  C\u3000  D\u3000  E\u3000  F\u3000  G\u3000  H");
            }
        }
    }

    public boolean moverPieza(String posPieza, String posMov) {

        //Combertir movimientos a int............
        char[] arrLetras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        Ficha guardarFicha;

        int x1 = 0;
        for (int i = 0; i < 8; i++) {
            if(arrLetras[i] == Character.toUpperCase(posPieza.charAt(0))){
                x1 = i;
            }
        }
        int y1 = Integer.parseInt(posPieza.substring(1));
        y1 -= 1;

        int x2 = 0;
        for (int i = 0; i < 8; i++) {
            if(arrLetras[i] == Character.toUpperCase(posMov.charAt(0))){
                x2 = i;
            }
        }
        int y2 = Integer.parseInt(posMov.substring(1));
        y2 -= 1;
        //..........................................

        limpiarTableroDeJaque();

        //COMPROBACION DE MOVIMIENTOS
        if(tablero[y1][x1] == " "){
            System.out.println("No hay ninguna pieza en esta posicion");
            return false;
        } else if (turno == 1) {
            if(((Ficha)tablero[y1][x1]).getColor() != jugador1.getColor()){
                System.out.println("Esta no es tu pieza");
                return false;
            }
        } else if (turno == 2) {
            if(((Ficha)tablero[y1][x1]).getColor() != jugador2.getColor()){
                System.out.println("Esta no es tu pieza");
                return false;
            }
        }

        //===================== MOVIMIENTO PEON =====================
        if (tablero[y1][x1] instanceof Peon) {
            if(((Peon) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)){
                //System.out.println("Movimiento valido");
                if(x1 == x2 && tablero[y2][x2] == " "){
                    tablero[y2][x2] = tablero[y1][x1];
                    tablero[y1][x1] = " ";
                    if(turno == 1){
                        turno = 2;
                    }else if(turno == 2){
                        turno = 1;
                    }
                    return true;
                }else if(x1 == x2 && tablero[y2][x2] != " "){
                    System.out.println("Movimiento invalido");
                    return false;
                }else if(tablero[y2][x2] == " "){
                    System.out.println("Movimiento invalido");
                    return false;
                }else if (((Ficha)tablero[y2][x2]).getColor() != ((Peon) tablero[y1][x1]).getColor()) {
                    if(turno == 1){
                        jugador2.fichaComida(tablero[y2][x2]);
                    } else if (turno == 2) {
                        jugador1.fichaComida(tablero[y2][x2]);
                    }
                    tablero[y2][x2] = tablero[y1][x1];
                    tablero[y1][x1] = " ";
                    if(turno == 1){
                        turno = 2;
                    }else if(turno == 2){
                        turno = 1;
                    }
                    return true;
                }else {
                    System.out.println("Movimiento invalido");
                    return false;
                }
            } else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        //===================== MOVIMIENTO TORREE =====================
        else if (tablero[y1][x1] instanceof Torre){
            if (((Torre) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)){
                //System.out.println("Movimiento valido");

                //Comprobar si hay fichas entre medias:
                int a;

                //Verical
                if(x1 == x2){
                    for (int i = 1; i < Math.abs(y1 - y2) ; i++) {
                        a = i;
                        if(turno == 1){
                            if(y1 < y2){
                                a = Math.abs(a += y1);
                            }else {
                                a = Math.abs(a -= y1);
                            }
                        }

                        if (tablero[a][x1] == " "){
                            //System.out.println("Movimiento valido");
                        } else if (((Ficha)tablero[a][x2]).getColor() != ((Torre) tablero[y1][x1]).getColor()) {
                            System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                            return false;
                        } else if (((Ficha)tablero[a][x2]).getColor() == ((Torre) tablero[y1][x1]).getColor()) {
                            System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                            return false;
                        }

                    }
                }
                //Horizontal
                else if (y1 == y2) {
                    for (int i = 1; i < Math.abs(x1 - x2) ; i++){
                        if(x1 < x2){
                            a = i + x1;
                        }else {
                            a = i;
                        }

                        if (tablero[y1][a] == " "){
                            //System.out.println("Movimiento valido");
                        } else if (((Ficha)tablero[y2][a]).getColor() != ((Torre) tablero[y1][x1]).getColor()) {
                            System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                            return false;
                        } else if (((Ficha)tablero[y2][a]).getColor() == ((Torre) tablero[y1][x1]).getColor()) {
                            System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                            return false;
                        }
                    }

                }
            }else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        //===================== MOVIMIENTO CABALLO =====================
        else if (tablero[y1][x1] instanceof Caballo){
            if (((Caballo) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)){
                //System.out.println("Movimiento valido");
            } else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        //===================== MOVIMIENTO ALFIL =====================
        else if (tablero[y1][x1] instanceof Alfil){
            if (((Alfil) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)){
                //System.out.println("Movimiento valido");

                if (y1 > y2){
                    for (int i = 1; i < (y1 - y2); i++) {
                        if (x1 < x2) {
                            if (tablero[y1 - i][x1 + i] == " "){
                                //System.out.println("Movimiento valido");
                            } else if (((Ficha)tablero[y1 - i][x1 + i]).getColor() == ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 - i][x1 + i]).getColor() != ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        } else if (x1 > x2){
                            if (tablero[y1 - i][x1 - i] == " " ){
                                //System.out.println("Movimiento valido");
                            }else if (((Ficha)tablero[y1 - i][x1 - i]).getColor() == ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 - i][x1 - i]).getColor() != ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        }
                    }
                } else if (y1 < y2){
                    for (int i = 1; i < (y1 + y2); i++) {
                        if (x1 < x2) {
                            if (tablero[y1 + i][x1 + i] == " "){
                                //System.out.println("Movimiento valido");
                            } else if (((Ficha)tablero[y1 + i][x1 + i]).getColor() == ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 + i][x1 + i]).getColor() != ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        } else if (x1 > x2){
                            if (tablero[y1 + i][x1 - i] == " " ){
                                //System.out.println("Movimiento valido");
                            }else if (((Ficha)tablero[y1 + i][x1 - i]).getColor() == ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 + i][x1 - i]).getColor() != ((Alfil) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        //===================== MOVIMIENTO REINA =====================
        else if (tablero[y1][x1] instanceof Reina){
            if (((Reina) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)) {
                //System.out.println("Movimiento valido");

                //Horizontal y vertical

                int a;

                //Verical
                if(x1 == x2){
                    for (int i = 1; i < Math.abs(y1 - y2) ; i++) {
                        a = i;
                        if(turno == 1){
                            if(y1 < y2){
                                a = Math.abs(a += y1);
                            }else {
                                a = Math.abs(a -= y1);
                            }
                        }

                        if (tablero[a][x1] == " "){
                            //System.out.println("Movimiento valido");
                        } else if (((Ficha)tablero[a][x2]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                            System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                            return false;
                        } else if (((Ficha)tablero[a][x2]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                            System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                            return false;
                        }

                    }
                }
                //Horizontal
                else if (y1 == y2) {
                    for (int i = 1; i < Math.abs(x1 - x2) ; i++){
                        if(x1 < x2){
                            a = i + x1;
                        }else {
                            a = i;
                        }

                        if (tablero[y1][a] == " "){
                            //System.out.println("Movimiento valido");
                        } else if (((Ficha)tablero[y2][a]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                            System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                            return false;
                        } else if (((Ficha)tablero[y2][a]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                            System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                            return false;
                        }
                    }

                }

                //Diagonal

                if (y1 > y2){
                    for (int i = 1; i < (y1 - y2); i++) {
                        if (x1 < x2) {
                            if (tablero[y1 - i][x1 + i] == " "){
                                //System.out.println("Movimiento valido");
                            } else if (((Ficha)tablero[y1 - i][x1 + i]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 - i][x1 + i]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        } else if (x1 > x2){
                            if (tablero[y1 - i][x1 - i] == " " ){
                                //System.out.println("Movimiento valido");
                            }else if (((Ficha)tablero[y1 - i][x1 - i]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 - i][x1 - i]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        }
                    }
                } else if (y1 < y2){
                    for (int i = 1; i < (y1 + y2); i++) {
                        if (x1 < x2) {
                            if (tablero[y1 + i][x1 + i] == " "){
                                //System.out.println("Movimiento valido");
                            } else if (((Ficha)tablero[y1 + i][x1 + i]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 + i][x1 + i]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        } else if (x1 > x2){
                            if (tablero[y1 + i][x1 - i] == " " ){
                                //System.out.println("Movimiento valido");
                            }else if (((Ficha)tablero[y1 + i][x1 - i]).getColor() == ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una de tus piezas bloquea el camino movimiento ivalido");
                                return false;
                            } else if (((Ficha)tablero[y1 + i][x1 - i]).getColor() != ((Reina) tablero[y1][x1]).getColor()) {
                                System.out.println("Una pieza enemiga bloquea el cmino, desaste de ella primero");
                                return false;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        //===================== MOVIMIENTO REY =====================
        else if (tablero[y1][x1] instanceof Rey){
            if (((Rey) tablero[y1][x1]).validarMovimiento(y1, y2, x1, x2)){
                //System.out.println("Movimiento valido");
            } else {
                System.out.println("Movimiento invalido");
                return false;
            }
        }

        if(tablero[y2][x2] == " ") {
            tablero[y2][x2] = tablero[y1][x1];
            tablero[y1][x1] = " ";

            //Comprobar si se mueve el rey a una posicion en peligro (No te tiene que permitir)
            if(!movimientoJaque()){
                System.out.println("No puedes mover esta ficha ahí porque probocaria un jaque");
                tablero[y1][x1] = tablero[y2][x2];
                tablero[y2][x2] = " ";
                limpiarTableroDeJaque();
                return false;
            }
        } else if (((Ficha)tablero[y2][x2]).getColor() != ((Ficha) tablero[y1][x1]).getColor()) {
            if(turno == 1){
                jugador2.fichaComida(tablero[y2][x2]);
            } else if (turno == 2) {
                jugador1.fichaComida(tablero[y2][x2]);
            }
            guardarFicha = (Ficha) tablero[y2][x2];
            tablero[y2][x2] = tablero[y1][x1];
            tablero[y1][x1] = " ";

            //Comprobar si se mueve el rey a una posicion en peligro (No te tiene que permitir)
            if(!movimientoJaque()){
                System.out.println("No puedes mover esta ficha ahí porque probocaria un jaque");
                tablero[y1][x1] = tablero[y2][x2];
                tablero[y2][x2] = guardarFicha;
                limpiarTableroDeJaque();
                return false;
            }
        }else {
            System.out.println("Movimiento invalido");
            return false;
        }



        if(turno == 1){
            turno = 2;
        }else if(turno == 2){
            turno = 1;
        }

        if(!movimientoJaque()){
            System.out.println("Hay jaque");
        }else {
            System.out.println("No jaque");
            limpiarTableroDeJaque();
        }

        return true;
    }

    public boolean[] comprobarJaque(int x, int y){
        boolean[] posSeguras = {true, true, true, true, true, true, true, true, true};
        //Posiciones seguras: {PosActual, Arriba, Abajo, Derecha, Izquierda, D_ArribaIzquierda, D_ArribaDerecha, D_AbajoIzquierda, D_AbajoDerecha}
        boolean jaque = false;


        //Comprobar arriba
        for (int i = 1; i < y; i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y - i][x] == " " || tablero[y - i][x] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y - i][x] instanceof Ficha){
                    if (((Ficha) tablero[y - i][x]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y - i][x] == " " || tablero[y - i][x] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y - i][x] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y - i][x]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x]).getColor() != jugador1.getColor()
                                && !((tablero[y - i][x] instanceof Torre) || (tablero[y - i][x] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y - i][x]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x]).getColor() != jugador2.getColor()
                                && !((tablero[y - i][x] instanceof Torre) || (tablero[y - i][x] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y - i][x] instanceof Torre){
                if(turno == 1){
                    if (((Torre) tablero[y - i][x]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Arriba");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Torre) tablero[y - i][x]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Arriba");
                        jaque = true;
                    }
                }
            } else if(tablero[y - i][x] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y - i][x]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Arriba");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y - i][x]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Arriba");
                        jaque = true;
                    }
                }

            }
        }
        if(jaque){
            posSeguras[1] = false;
        }
        jaque = false;

        //Comprobar abajo
        for (int i = 1; i < (7 - y); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y + i][x] == " " || tablero[y + i][x] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y + i][x] instanceof Ficha){
                    if (((Ficha) tablero[y + i][x]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y + i][x] == " " || tablero[y + i][x] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y + i][x] instanceof Ficha){
                     if(turno == 1){
                        if(((Ficha) tablero[y + i][x]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x]).getColor() != jugador1.getColor()
                                && !((tablero[y + i][x] instanceof Torre) || (tablero[y + i][x] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y + i][x]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x]).getColor() != jugador2.getColor()
                                && !((tablero[y + i][x] instanceof Torre) || (tablero[y + i][x] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y + i][x] instanceof Torre){
                if(turno == 1){
                    if (((Torre) tablero[y + i][x]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Abajo");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Torre) tablero[y + i][x]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Abajo");
                        jaque = true;
                    }
                }
            } else if(tablero[y + i][x] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y + i][x]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Abajo");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y + i][x]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Abajo");
                        jaque = true;
                    }
                }

            }
        }
        if(jaque){
            posSeguras[2] = false;
        }
        jaque = false;

        //Comprobar Derecha
        for (int i = 1; i < (7 - x); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y][x + i] == " " || tablero[y][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y][x + i] instanceof Ficha){
                    if (((Ficha) tablero[y][x + i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y][x + i] == " " || tablero[y][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y][x + i] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y][x + i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y][x + i]).getColor() != jugador1.getColor()
                                && !((tablero[y][x + i] instanceof Torre) || (tablero[y][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y][x + i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y][x + i]).getColor() != jugador2.getColor()
                                && !((tablero[y][x + i] instanceof Torre) || (tablero[y][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y][x + i] instanceof Torre){
                if(turno == 1){
                    if (((Torre) tablero[y][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Torre) tablero[y][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Derecha");
                        jaque = true;
                    }
                }
            } else if(tablero[y][x + i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque Derecha");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[3] = false;
        }
        jaque = false;

        //Comprobar Izquierda
        for (int i = 1; i < x; i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y][x - i] == " " || tablero[y][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y][x - i] instanceof Ficha){
                    if (((Ficha) tablero[y][x - i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y][x - i] == " " || tablero[y][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y][x - i] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y][x - i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y][x - i]).getColor() != jugador1.getColor()
                                && !((tablero[y][x - i] instanceof Torre) || (tablero[y][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y][x - i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y][x - i]).getColor() != jugador2.getColor()
                                && !((tablero[y][x - i] instanceof Torre) || (tablero[y][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y][x - i] instanceof Torre){
                if(turno == 1){
                    if (((Torre) tablero[y][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Torre) tablero[y][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque  Izquierda");
                        jaque = true;
                    }
                }
            } else if(tablero[y][x - i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque  Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque  Izquierda");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[4] = false;
        }
        jaque = false;

        // Comprobar diagonal arriba izquierda
        for (int i = 1; i <= Math.min(x, y); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y - i][x - i] == " " || tablero[y - i][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y - i][x - i] instanceof Ficha){
                    if (((Ficha) tablero[y - i][x - i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y - i][x - i] == " " || tablero[y - i][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                }else if(tablero[y - i][x - i] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y - i][x - i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x - i]).getColor() != jugador1.getColor()
                                && !((tablero[y - i][x - i] instanceof Alfil) || (tablero[y - i][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y - i][x - i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x - i]).getColor() != jugador2.getColor()
                                && !((tablero[y - i][x - i] instanceof Alfil) || (tablero[y - i][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y - i][x - i] instanceof Alfil){
                if(turno == 1){
                    if (((Alfil) tablero[y - i][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ar_Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Alfil) tablero[y - i][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ar_Izquierda");
                        jaque = true;
                    }
                }
            } else if(tablero[y - i][x - i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y - i][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ar_Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y - i][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ar_Izquierda");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[5] = false;
        }
        jaque = false;

        // Comprobar diagonal arriba derecha
        for (int i = 1; i <= Math.min(7 - x, y); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y - i][x + i] == " " || tablero[y - i][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y - i][x + i] instanceof Ficha){
                    if (((Ficha) tablero[y - i][x + i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y - i][x + i] == " " || tablero[y - i][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y - i][x + i] instanceof Ficha){
                     if(turno == 1){
                        if(((Ficha) tablero[y - i][x + i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x + i]).getColor() != jugador1.getColor()
                                && !((tablero[y - i][x + i] instanceof Alfil) || (tablero[y - i][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                     } else if (turno == 2) {
                        if(((Ficha) tablero[y - i][x + i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y - i][x + i]).getColor() != jugador2.getColor()
                                && !((tablero[y - i][x + i] instanceof Alfil) || (tablero[y - i][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y - i][x + i] instanceof Alfil){
                if(turno == 1){
                    if (((Alfil) tablero[y - i][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ar_Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Alfil) tablero[y - i][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ar_Derecha");
                        jaque = true;
                    }
                }
            } else if(tablero[y - i][x + i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y - i][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ar_Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y - i][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ar_Derecha");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[6] = false;
        }
        jaque = false;

        // Comprobar diagonal abajo izquierda
        for (int i = 1; i <= Math.min(x, 7 - y); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y + i][x - i] == " " || tablero[y + i][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y + i][x - i] instanceof Ficha){
                    if (((Ficha) tablero[y + i][x - i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y + i][x - i] == " " || tablero[y + i][x - i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y + i][x - i] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y + i][x - i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x - i]).getColor() != jugador1.getColor()
                                && !((tablero[y + i][x - i] instanceof Alfil) || (tablero[y + i][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y + i][x - i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x - i]).getColor() != jugador2.getColor()
                                && !((tablero[y + i][x - i] instanceof Alfil) || (tablero[y + i][x - i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y + i][x - i] instanceof Alfil){
                if(turno == 1){
                    if (((Alfil) tablero[y + i][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ab_Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Alfil) tablero[y + i][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ab_Izquierda");
                        jaque = true;
                    }
                }
            } else if(tablero[y + i][x - i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y + i][x - i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ab_Izquierda");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y + i][x - i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ab_Izquierda");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[7] = false;
        }
        jaque = false;

        // Comprobar diagonal abajo derecha
        for (int i = 1; i <= Math.min(7 - x, 7 - y); i++) {
            if (tablero[y][x] instanceof Rey) {
                if (tablero[y + i][x + i] == " " || tablero[y + i][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y + i][x + i] instanceof Ficha){
                    if (((Ficha) tablero[y + i][x + i]).getColor() == ((Rey) tablero[y][x]).getColor()) {
                        System.out.println("No hay jaque");
                        break;
                    }
                }
            } else {
                if (tablero[y + i][x + i] == " " || tablero[y + i][x + i] == "\uFF4F"){
                    System.out.println("No hay jaque");
                } else if(tablero[y + i][x + i] instanceof Ficha){
                    if(turno == 1){
                        if(((Ficha) tablero[y + i][x + i]).getColor() == jugador1.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x + i]).getColor() != jugador1.getColor()
                                && !((tablero[y + i][x + i] instanceof Alfil) || (tablero[y + i][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    } else if (turno == 2) {
                        if(((Ficha) tablero[y + i][x + i]).getColor() == jugador2.getColor()){
                            System.out.println("No hay jaque");
                            break;
                        } else if (((Ficha) tablero[y + i][x + i]).getColor() != jugador2.getColor()
                                && !((tablero[y + i][x + i] instanceof Alfil) || (tablero[y + i][x + i] instanceof Reina))) {
                            System.out.println("No hay jaque");
                            break;
                        }
                    }
                }
            }
            if(tablero[y + i][x + i] instanceof Alfil){
                if(turno == 1){
                    if (((Alfil) tablero[y + i][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ab_Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Alfil) tablero[y + i][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ab_Derecha");
                        jaque = true;
                    }
                }
            } else if(tablero[y + i][x + i] instanceof Reina){
                if(turno == 1){
                    if (((Reina) tablero[y + i][x + i]).getColor() != jugador1.getColor()) {
                        System.out.println("Jaque D_Ab_Derecha");
                        jaque = true;
                    }
                }else if (turno == 2) {
                    if (((Reina) tablero[y + i][x + i]).getColor() != jugador2.getColor()) {
                        System.out.println("Jaque D_Ab_Derecha");
                        jaque = true;
                    }
                }
            }
        }
        if(jaque){
            posSeguras[8] = false;
        }
        jaque = false;

        // Comprobar movimiento caballo
        int[][] movimientosCaballo = {
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
        };

        for (int[] movimiento : movimientosCaballo) {
            int caballoY = y + movimiento[0];
            int caballoX = x + movimiento[1];

            if (caballoY >= 0 && caballoY < 8 && caballoX >= 0 && caballoX < 8) {
                if (tablero[caballoY][caballoX] instanceof Caballo) {
                    if(turno == 1){
                        if (((Ficha) tablero[caballoY][caballoX]).getColor() != jugador1.getColor()) {
                            System.out.println("Jaque");
                            jaque = true;
                        }
                    }else if (turno == 2) {
                        if (((Ficha) tablero[caballoY][caballoX]).getColor() != jugador2.getColor()) {
                            System.out.println("Jaque");
                            jaque = true;
                        }
                    }
                }
            }
        }
        if(jaque){
            posSeguras[0] = false;
        }
        jaque = false;

        // Comprobar movimiento peon
        int[][] movimientosPeon = new int[0][0];

        if (turno == 1) {
            movimientosPeon = new int[][]{{-1, -1}, {-1, 1}};
        } else if (turno == 2) {
            movimientosPeon = new int[][]{{1, -1}, {1, 1}};
        }
        for (int[] movimiento : movimientosPeon) {
            int peonY = y + movimiento[0];
            int peonX = x + movimiento[1];

            if (peonY >= 0 && peonY < 8 && peonX >= 0 && peonX < 8) {
                if (tablero[peonY][peonX] instanceof Peon) {
                    if(turno == 1){
                        if (((Ficha) tablero[peonY][peonX]).getColor() != jugador1.getColor()) {
                            System.out.println("Jaque");
                            jaque = true;
                        }
                    } else if (turno == 2) {
                        if (((Ficha) tablero[peonY][peonX]).getColor() != jugador2.getColor()) {
                            System.out.println("Jaque");
                            jaque = true;
                        }
                    }

                }
            }
        }
        if(jaque){
            posSeguras[0] = false;
        }

        return posSeguras;
    }

    public boolean movimientoJaque(){
        boolean[] posSeguras;
        boolean[] posSeguras2;
        boolean hayJaque = false;
        int x =0 ,y = 0;
        outerLoop:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(turno == 1){
                    if(tablero[i][j] instanceof Rey && ((Rey) tablero[i][j]).getColor() == jugador1.getColor()){
                        y = i;
                        x = j;
                        break outerLoop;
                    }
                } else if (turno == 2) {
                    if(tablero[i][j] instanceof Rey && ((Rey) tablero[i][j]).getColor() == jugador2.getColor()){
                        y = i;
                        x = j;
                        break outerLoop;
                    }
                }
            }
        }
        posSeguras = comprobarJaque(x, y);
        for (int i = 1; i < 9; i++) {
            if (posSeguras[i]) {
                switch (i) {
                    case 1: // Arriba
                        if(y != 0 && !(tablero[y - 1][x] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x, y - 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y - 1][x] == " " && !hayJaque) {
                                tablero[y - 1][x] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 2: // Abajo
                        if(y != 7 && !(tablero[y + 1][x] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x, y + 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y + 1][x] == " " && !hayJaque) {
                                tablero[y + 1][x] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 3: // Derecha
                        if (x != 7 && !(tablero[y][x + 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x + 1, y);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y][x + 1] == " " && !hayJaque) {
                                tablero[y][x + 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 4: // Izquierda
                        if (x != 0 && !(tablero[y][x - 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x - 1, y);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y][x - 1] == " " && !hayJaque) {
                                tablero[y][x - 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 5: // Diagonal arriba izquierda
                        if (x != 0 && y != 0 && !(tablero[y - 1][x - 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x - 1, y - 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y - 1][x - 1] == " " && !hayJaque) {
                                tablero[y - 1][x - 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;
                    case 6: // Diagonal arriba derecha
                        if (x != 7 && y != 0 && !(tablero[y - 1][x + 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x + 1, y - 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y - 1][x + 1] == " " && !hayJaque) {
                                tablero[y - 1][x + 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 7: // Diagonal abajo izquierda
                        if (x != 0 && y != 7 && !(tablero[y + 1][x - 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x - 1, y + 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y + 1][x - 1] == " " && !hayJaque) {
                                tablero[y + 1][x - 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;

                    case 8: // Diagonal abajo derecha
                        if (x != 7 && y != 7 && !(tablero[y + 1][x + 1] instanceof Ficha)){
                            posSeguras2 = comprobarJaque(x + 1, y + 1);
                            for (boolean noJaque : posSeguras2) {
                                if (!noJaque) {
                                    hayJaque = true;
                                    break;
                                }
                            }
                            if (tablero[y + 1][x + 1] == " " && !hayJaque) {
                                tablero[y + 1][x + 1] = "x";
                            }
                        }
                        hayJaque = false;
                        break;
                }
            }
        }
        for(boolean jaque: posSeguras){
            if(!jaque){
                return false;
            }
        }
        return true;
    }

    private void limpiarTableroDeJaque() {

        /*Para que las marcas del jaque no afecten al movimiento ya que solo son
         necesrias en la visualizacion del tablero que se printa antes de que estas se borren*/

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tablero[i][j] == "x"){
                    tablero[i][j] = " ";
                }
            }
        }
    }
}