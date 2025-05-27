package tresenraya;

import java.util.Arrays;
import java.util.Random;

public class TresEnRaya {
	static char[][] tablero = new char[3][3];

	// Objeto random
	static Random rand = new Random();

	public static void main(String[] args) {

	}

	/**
	 * Imprime el tablero
	 */
	public static void imprimeTablero() {
		for (char[] fila : tablero) {
			for (char celda : fila) {
				System.out.print(celda + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Define quien empieza a jugar
	 * 
	 * @return 1 si empieza el jugador y 0 si empieza la maquina
	 */
	public static int jugadorInicial() {
		int res;

		res = rand.nextInt(0, 2);

		return res;
	}

	/**
	 * Placea una ficha "O" en el tablero. Comprobando antes si la celda esta
	 * disponible
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
		} while (!valid);

		tablero[posFila][posCol] = 'O';
	}

	/**
	 * Se actualiza el tablero poniendo una "X" en la posicion pasada por parámetro
	 * 
	 * @param posF Fila de la posicion indicada
	 * @param posC Columna de la posicion indicada
	 * @return True si se ha colocado la ficha, false si no se ha colocado
	 */
	public static boolean usuarioMueveFicha(int posF, int posC) {
		boolean moved = false;

		if (compPosicion(posF) && compPosicion(posC) && tablero[posF][posC] == '-') {
			tablero[posF][posC] = 'X';
			moved = true;
		}

		return moved;
	}

	/**
	 * Comprueba que la posicion es valida (Está en los limites del tablero)
	 * 
	 * @param posicion a comprobar
	 * @return True si la posicion esta dentro de los limites del tablero. False en
	 *         caso contrario
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

	/**
	 * Define si es ganador comprobando las diferentes combinaciones para la victoria
	 * @param caracter Caracter a identificar como ganador o no
	 * @return True si estos caracteres han ganado y false en caso contrario
	 */
	public static boolean esGanador(char caracter) {
		return compFilas(caracter) 
				|| compColumnas(caracter) 
				|| compDiagonalDerecha(caracter)
				|| compDiagonalIzquierda(caracter);

	}

	/**
	 * Comprueba la victoria en Horizontal
	 * @param caracter Caracter para comprobar la victoria
	 * @return True si ha ganado en horizontal y false en caso contrario
	 */
	public static boolean compFilas(char caracter) {
		boolean res = false;
		boolean auxRes = true;
		int i = 0;
		int j = 0;

		while (i < tablero.length && !res) {
			j = 0;
			while (j < tablero[0].length && auxRes) {
				if (tablero[i][j] != caracter) {
					auxRes = false;
				}
				j++;
			}
			res = auxRes;
			auxRes = true;
			i++;
		}

		return res;
	}

	/**
	 * Comprobacion de victoria de un caracter en vertical
	 * @param caracter Caracter a comprobar 
	 * @return True si ha ganado en vertical y false en caso contrario
	 */
	public static boolean compColumnas(char caracter) {
		boolean res = true;
		boolean auxRes = false;
		int i = 0;
		int j = 0;

		while (j < tablero[0].length && !auxRes) {
			i = 0;
			while (i < tablero.length && res) {
				if (tablero[i][j] != caracter) {
					res = false;
				}

				i++;
			}
			auxRes = res;
			res = true;
			j++;
		}

		return auxRes;
	}

	/**
	 * Comprobacion de victoria en diagonal de derecha a izquierda
	 * @param caracter Caracter a comprobar
	 * @return True si ha ganado el diagonal de derecha a izquierda y false en caso contrario
	 */
	public static boolean compDiagonalDerecha(char caracter) {
		boolean res = true;
		int i = 0;

		while (i < tablero.length && res) {
			if (tablero[i][i] != caracter) {
				res = false;
			}
			i++;
		}

		return res;
	}

	/**
	 * Comprobacion de victoria en diagonal de izquierda a derecha
	 * @param caracter Caracter a comprobar
	 * @return True si ha ganado el diagonal de izquierda a derecha y false en caso contrario
	 */
	public static boolean compDiagonalIzquierda(char caracter) {
		boolean res = true;
		int i = 0;

		while (i < tablero.length && res) {
			if (tablero[i][tablero[0].length - i - 1] != caracter) {
				res = false;
			}
			i++;
		}

		return res;
	}
	


}
