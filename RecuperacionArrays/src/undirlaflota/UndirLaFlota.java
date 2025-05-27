package undirlaflota;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class UndirLaFlota {	
	
	/**
	 * Inicializa tablero (Jugadores)
	 * @param numF Numero de filas
	 * @param numC Numero de columnas
	 * @return Un tablero con las filas y columnas especificadas
	 */
	public static char[][] inicializaTablero(int numF, int numC){
		char[][] tablero = new char[numF][numC];
		for (char[] t : tablero) {
			Arrays.fill(t, '-');
		}
		return tablero;
	}
	
	/**
	 * Crea el tablero (Donde estarán los barcos)
	 * @param numF Numero de filas
	 * @param numC Numero de columnas
	 * @return Un tablero con las filas y columnas especificadas
	 */
	public static char[][] creaTablero (int numF, int numC){
		char[][] tablero = new char[numF][numC];
		for (char[] t : tablero) {
			Arrays.fill(t, 'A');
		}
		return tablero;
	}
	
	
	/**
	 * Genera barquitos en posiciones random en un tablero
	 * @param tablero Tablero donde se van a generar los barcos
	 * @param numBarquitos Numero de barcos que se van a generar
	 */
	public static void generarBarquitos (char[][] tablero, int numBarquitos) {
		Random rand = new Random();
		int posF = 0;
		int posC = 0;
		
		for (int i = 0; i < numBarquitos; i++) {
			posF = rand.nextInt(0, tablero.length);
			posC = rand.nextInt(0, tablero[0].length);
			
			while (tablero[posF][posC] == 'B') {
				posF = rand.nextInt(0, tablero.length);
				posC = rand.nextInt(0, tablero[0].length);
			}
			tablero[posF][posC] = 'B';
		}
		
	}
	
	/**
	 * Pinta el tablero que se le pasa por parámetro
	 * @param tableroJugador Tablero del jugador que se va a pintar
	 */
	public static void pintaTablero (char tableroJugador[][]) {
		
		for (int j = 0; j < tableroJugador[0].length; j++) {
			System.out.print("\t" + (j + 1));
		}
		
		System.out.println();
		
		for (int i = 0; i < tableroJugador.length; i++) {
			System.out.print((char)(i + 'A'));
			for (int j = 0; j < tableroJugador[i].length; j++) {
				System.out.print("\t" + tableroJugador[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean turnoJugador(char[][] tablero, char[][] tableroJugador) {
		boolean drowned = false;
		Scanner rd = new Scanner(System.in);
		char fila;
		int columna;
		
		System.out.println("Introduce la fila:");
		fila = rd.nextLine().toUpperCase().charAt(0);
		
		System.out.println("Introduce la columna:");
		columna = rd.nextInt();
		
		//Limpio buffer
		rd.nextLine();
		
		while (!(fila >= 'A' && fila < tablero.length + 'A')) {
			System.out.println("Fila mal introducida. Introduce la fila:");
			fila = rd.nextLine().toUpperCase().charAt(0);
		}
		
		while (!(columna > 0 && columna <= tablero[0].length)) {
			System.out.println("Columna mal introducida. Introduce la columna:");
			columna = rd.nextInt();
		}
		
		if (tablero[fila - 'A'][columna - 1] == 'B') {
			System.out.println("HUNDIDO");
			tableroJugador[fila - 'A'][columna - 1] = 'B';
			drowned = true;
		} else {
			System.out.println("AGUA");
			tableroJugador[fila - 'A'][columna - 1] = 'A';
		}
		
		rd.close();
		
		return drowned;
	}
	
	
	
}
