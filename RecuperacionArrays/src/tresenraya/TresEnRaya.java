package tresenraya;

import java.util.Arrays;
import java.util.Random;

public class TresEnRaya {
	static //Tablero
	char[][] tablero = new char[9][9];
	
	//Objeto random
	static Random rand = new Random();
	
	public static void main(String[] args) {
		
		
		
	}
	
	/**
	 * Imprime el tablero
	 */
	public static void imprimeTablero() {
		for (char[] fila : tablero) {
			for (char celda : fila) {
				System.out.println(celda);
			}
		}
	}
	
	/**
	 * Define quien empieza a jugar
	 * @return 1 si empieza el jugador y 0 si empieza la maquina
	 */
	public static int jugadorInicial() {
		int res;
		
		res = rand.nextInt(0, 2);
		
		return res;
	}
	
	
	/**
	 * Placea una ficha "O" en el tablero. Comprobando antes si la celda esta disponible
	 */
	public static void mueveFichaAleatoria() {
		boolean valid = false;
		int posFila;
		int posCol;
		
		do {
			posFila = rand.nextInt(0, tablero.length);
			posCol = rand.nextInt(0, tablero[posFila].length);
			
			if (tablero[posFila][posCol] == '-') {
				valid = true;
			}
		} while(!valid);
		
		tablero[posFila][posCol] = 'O';		
	}
	
	
	/**
	 * Se actualiza el tablero poniendo una "X" en la posicion pasada por parámetro
	 * @param posF Fila de la posicion indicada
	 * @param posC Columna de la posicion indicada
	 * @return True si se ha colocado la ficha, false si no se ha colocado
	 */
	public static boolean usuarioMueveFicha(int posF, int posC) {
		boolean moved = false;
				
		if (tablero[posF][posC] == '-' && compPosicion(posF) && compPosicion(posC)) {
			tablero[posF][posC] = 'X';
			moved = true;
		}
		
		return moved;
	}
	
	/**
	 * Comprueba que la posicion es valida (Está en los limites del tablero)
	 * @param posicion a comprobar
	 * @return True si la posicion esta dentro de los limites del tablero. False en caso contrario
	 */
	public static boolean compPosicion(int posicion) {
		boolean valid = false;
		
		if (posicion >= 0 && posicion < tablero.length) {
			valid = true;
		}
		
		return valid;
	}
	
	
	/**
	 * Inicializa el tablero a '-'
	 */
	public static void limpiaTablero() {
		for (char[] fila : tablero) {
			Arrays.fill(fila, '-');
		}
	}
	
	public static boolean esGanador(char caracter) {
		boolean winner = false;
		
		int contCar = 0;
		
		int cont = 0;
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				//Anterior Horizontal
				if (j - 1 >= 0 && tablero[i][j - 1] == caracter) {
					contCar++;
				}
				
				//Actual Horizontal
				if (tablero[i][j] == caracter) {
					contCar++;
				}
				
				//Posterior Horizontal
				if (j + 1 < tablero[i].length && tablero[i][j + 1] == caracter) {
					contCar++;
				}
			}
		}
		
		
		
		
		
		if (contCar == 3) {
			winner = true;
		}
		
		return winner;
	}
	
}
