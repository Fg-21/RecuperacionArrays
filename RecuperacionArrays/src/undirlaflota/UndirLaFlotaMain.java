package undirlaflota;

import java.util.Scanner;

public class UndirLaFlotaMain {
	public static void main(String[] args) {
		//Scanner
		Scanner rd = new Scanner(System.in);
				
		/**
		 * Tablero principal
		 */
		char[][] tableroMain;
		/**
		 * Tablero para el J1
		 */
		char[][] tableroJ1;
		/**
		 * Tablero para el J2
		 */
		char[][] tableroJ2;
		
		/**
		 * Numero de filas y columnas que van a tener los tableros
		 */
		int filas;
		int columnas;
		
		/**
		 * Numero de barcos en el tablero
		 */
		int barcos;
		
		/**
		 * Conteo de barcos para cada jugador
		 */
		int bcont1;
		int bcont2;
		
		//Bienvenida al usuario
		System.out.println("BIENVENIDO AL UNDIR LA FLOTA");
		
		//Pedimos las filas y columnas
		do {
			System.out.println("Introduce las filas que tendrá el tablero (No pueden ser negativas ni 0)");
			filas = rd.nextInt();
		} while (filas <= 0);
		
		do {
			System.out.println("Introduce las columnas que tendrá el tablero (No pueden ser negativas ni 0)");
			columnas = rd.nextInt();
		} while (columnas <= 0);
		
		rd.nextLine();
		
		//Generamos el tablero
		tableroMain = UndirLaFlota.creaTablero(filas, columnas);
		
		//Generamos los barquitos en funcion del tablero
		barcos = (int) Math.sqrt(filas * columnas);
		UndirLaFlota.generarBarquitos(tableroMain, barcos);
		
		//Asignamos el numero de barcos al conteo de los mismos para cada jugador
		bcont1 = barcos;
		bcont2 = barcos;
		
		//Inicializamos el tablero de ambos jugadores
		tableroJ1 = UndirLaFlota.inicializaTablero(filas, columnas);
		tableroJ2 = UndirLaFlota.inicializaTablero(filas, columnas);
		
		while (bcont1 > 0 && bcont2 > 0) {
		    System.out.println("Turno Jugador 1");
		    UndirLaFlota.pintaTablero(tableroJ1);
		    boolean acierto;
		    do {
		        acierto = UndirLaFlota.turnoJugador(tableroMain, tableroJ1);
		        if (acierto) {
		            bcont1--;
		            UndirLaFlota.pintaTablero(tableroJ1);
		        }
		    } while (acierto && bcont1 > 0);

		    if (bcont1 > 0) { // Solo juega el J2 si el J1 no ha ganado
		        System.out.println("Turno Jugador 2");
		        UndirLaFlota.pintaTablero(tableroJ2);
		        do {
		            acierto = UndirLaFlota.turnoJugador(tableroMain, tableroJ2);
		            if (acierto) {
		                bcont2--;
		                UndirLaFlota.pintaTablero(tableroJ2);
		            }
		        } while (acierto && bcont2 > 0);
		    }
		}


		
		if (bcont1 == 0) {
			System.out.println("JUGADOR 1 HA GANADO");
		} else {
			System.out.println("JUGADOR 2 HA GANADO");
		}
		
		rd.close();
				
	}
}
