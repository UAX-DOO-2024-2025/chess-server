import fichas.TiposColor;
import juego.Jugador;
import juego.Tablero;
import juego.ValidacionPosicion;

import java.util.Scanner;

import static juego.Tablero.turno;
import static juego.Tablero.victoria;

public class Main {
    public static void main(String[] args) {

        boolean entradaValida = false;
        Scanner sc = new Scanner(System.in);
        String pos1 = "", pos2 = "";
        String nombre1, nombre2;
        int numero;

        Jugador jug1, jug2;

        System.out.println();
        System.out.println("==========================================");
        System.out.println(" ██████╗██╗  ██╗███████╗███████╗███████╗");
        System.out.println("██╔════╝██║  ██║██╔════╝██╔════╝██╔════╝");
        System.out.println("██║     ███████║█████╗  ███████╗███████╗");
        System.out.println("██║     ██╔══██║██╔══╝  ╚════██║╚════██║");
        System.out.println("╚██████╗██║  ██║███████╗███████╗███████╗");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝");
        System.out.println("==========================================");
        System.out.println();

        numero = (int) (Math.random() * 10) + 1;
        System.out.print("-Ingrese el nombre del JUGADOR 1: ");
        nombre1 = sc.nextLine();
        if(numero >= 5){
            jug1 = new Jugador(nombre1, TiposColor.BLANCO);
        }else {
            jug1 = new Jugador(nombre1, TiposColor.NEGRO);
        }


        System.out.print("-Ingrese el nombre del JUGADOR 2: ");
        nombre2 = sc.nextLine();
        if(numero < 5){
            jug2 = new Jugador(nombre2, TiposColor.BLANCO);
        }else {
            jug2 = new Jugador(nombre2, TiposColor.NEGRO);
        }

        System.out.println("====== Color de fichas al azar \uD83C\uDFB2 ======");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println();
            }
        }
        System.out.println();

        if(numero >= 5){
            System.out.println("JUGADOR 1 {BLANCO} || JUGADOR 2 {NEGRO}");
            turno = 1;
        }else {
            System.out.println("JUGADOR 1 {NEGRO} || JUGADOR 2 {BLANCO}");
            turno = 2;
        }

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println();
        }

        Tablero t1 = new Tablero(jug1, jug2);


        while (!victoria) {
            do {
                System.out.println("\n");
                t1.mostrarTablero();
                System.out.println();
                if(turno == 1){
                    System.out.println("|| Turno de " + nombre1 + ": ");
                }else if(turno == 2){
                    System.out.println("|| Turno de " + nombre2 + ": ");
                }

                // Validar posición inicial
                while (!entradaValida) {
                    try {
                        System.out.print("Selecciona pieza ----> ");
                        pos1 = sc.nextLine();
                        ValidacionPosicion.validar(pos1);
                        entradaValida = true;
                    } catch (ValidacionPosicion e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                entradaValida = false;

                // Validar posición destino
                while (!entradaValida) {
                    try {
                        System.out.print("Posición destino ----> ");
                        pos2 = sc.nextLine();
                        ValidacionPosicion.validar(pos2);
                        entradaValida = true;
                    } catch (ValidacionPosicion e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                entradaValida = false;

            }while(!t1.moverPieza(pos1, pos2));
        }

    }
}