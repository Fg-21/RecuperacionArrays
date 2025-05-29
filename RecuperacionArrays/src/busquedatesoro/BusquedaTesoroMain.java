package busquedatesoro;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BusquedaTesoroMain {

	static char[][] tablero;
	static int posI;
	static int posJ;

	public static void main(String[] args) {
		// Scanner
		Scanner rd = new Scanner(System.in);

		// Filas del tablero
		int filas;

		// Columnas del tablero
		int col;

		// Numero de obstaculos
		int obs;

		// Movimiento del jugador
		String movimiento;

		// Resultado del mov del jugador
		int mov;

		// Bienvenida al jugador
		System.out.println("Bienvenido a la busqueda del tesoro");

		do {
			// Preguntamos el tamaño que quiere el usuario para el tablero
			System.out.println("Introduce el numero de filas para el tamaño del tablero (No pueden ser menos que 2)");
			filas = rd.nextInt();
		} while (filas < 2);

		do {
			// Preguntamos el tamaño que quiere el usuario para el tablero
			System.out.println("Introduce el numero de columnas para el tamaño del tablero (No pueden ser menos que 2)");
			col = rd.nextInt();
		} while (col < 2);

		// Limpio Buffer
		rd.nextLine();

		// Calculo los obstaculos en base a las filas y columnas del tablero
		obs = (int) Math.sqrt(filas * col);

		// Creo el tablero con las filas y columnas pedidas
		inicializaTablero(filas, col);

		// Genero la posicion del tesoro
		generaPosicionTesoro();

		// Genera los obstaculos
		generaObstáculos(obs);

		// Genero la posicion del jugador
		generaPosicionJugador();

		// Comienza el juego
		do {
			// Pinto tablero
			pintaTablero();

			// Pregunto hacia donde se quiere mover el jugador
			System.out.println("Donde te quieres mover? (ARRIBA, ABAJO, IZQUIERDA, DERECHA)");
			movimiento = rd.nextLine();
			
			//Intento ejecutar el movimiento
			mov = mueveJugador(movimiento);
			
			//Depende del resultado doy feedback
			if (mov == -1) {
				System.out.println("No puedes moverte hacia allí");
			} else if (mov == -2) {
				System.out.println("Movimiento no entendido");
			}

		} while (tablero[posI][posJ] != 'X');
		
		//Felicito al jugador
		System.out.println("Enhorabuena");

	}

	/**
	 * Crea el tablero de juego de tamaño numFilas x numColumnas y lo rellena con ‘
	 * ’ (espacio en blanco).
	 * 
	 * @param numF Numero de filas del tablero
	 * @param numC Numero de columnas del tablero
	 */
	public static void inicializaTablero(int numF, int numC) {
		tablero = new char[numF][numC];

		for (char[] c : tablero) {
			Arrays.fill(c, ' ');
		}
	}

	/**
	 * Genera de forma aleatoria los índices i y j del tesoro, y escribe en la
	 * posición [i][j] una ‘X’.
	 */
	public static void generaPosicionTesoro() {
		Random rand = new Random();
		int posF = rand.nextInt(0, tablero.length);
		int posC = rand.nextInt(0, tablero[0].length);

		tablero[posF][posC] = 'X';

	}

	/**
	 * Genera tantos obstáculos como se indica en numObstaculos. Estos no son
	 * colocados de manera diagonal uno al lado del otro
	 * 
	 * @param numObstaculos Numero de obstaculos a colocar
	 */
	public static void generaObstáculos(int numObstaculos) {
		Random rand = new Random();
		int posI;
		int posJ;
		char caracter;

		int ObsPlaced = 0;

		while (ObsPlaced < numObstaculos) {
			posI = rand.nextInt(0, tablero.length);
			posJ = rand.nextInt(0, tablero[0].length);

			caracter = tablero[posI][posJ];

			if (caracter == ' ' && !obsAround(posI, posJ)) {
				tablero[posI][posJ] = '*';
				ObsPlaced++;
			}
		}
	}

	/**
	 * Comprueba si hay obstaculos en las diagonales de las coordenadas pasadas
	 * 
	 * @param posI Posicion de la fila
	 * @param posJ Posicion de la columna
	 * @return True si ha encontrado un obstaculo y False en caso contrario
	 */
	public static boolean obsAround(int posI, int posJ) {
		boolean obstaculo = false;

		int i = (posI - 1) < 0 ? posI + 1 : posI - 1;
		int j;

		while (i < tablero.length && i <= posI + 1 && !obstaculo) {
			j = (posJ - 1) < 0 ? posJ + 1 : posJ - 1;
			while (j < tablero[0].length && j <= posJ +1 && !obstaculo) {
				if (tablero[i][j] == '*') {
					obstaculo = true;
				}
				j += 2;
			}
			i += 2;
		}
		return obstaculo;
	}

	/**
	 * Genera una posicion para el jugador aleatoria en la que no esté ya el tesoro
	 * (X) o un obstaculo (*)
	 */
	public static void generaPosicionJugador() {
		Random rand = new Random();

		int posiI;
		int posiJ;

		do {
			posiI = rand.nextInt(0, tablero.length);
			posiJ = rand.nextInt(0, tablero[0].length);
		} while (tablero[posiI][posiJ] == 'X' || tablero[posiI][posiJ] == '*');

		posI = posiI;
		posJ = posiJ;
	}

	/**
	 * Imprime el tablero para que el usuario lo vea, omitiendo los guiones y la
	 * posicion del tesoro. Además de pintar al jugador en su sitio correspondiente
	 */
	public static void pintaTablero() {
		// Numeros de indice de columnas
		for (int j = 0; j < tablero[0].length; j++) {
			System.out.print("\t" + j);
		}

		System.out.println();

		// Tablero con el numero de indice de fila
		for (int i = 0; i < tablero.length; i++) {
			System.out.print(i + "\t");
			for (int j = 0; j < tablero[0].length; j++) {
				if (tablero[i][j] == 'X' || tablero[i][j] == '-') {
					System.out.print("\t");
				} else if (i == posI && j == posJ) {
					System.out.print("J\t");
				} else {
					System.out.print(tablero[i][j] + "\t");
				}
			}
			System.out.println();
		}

	}

	public static int mueveJugador(String movimiento) {
		int res = -1;

		switch (movimiento.toUpperCase()) {
		case "ARRIBA" -> {
			if (posI - 1 >= 0 && tablero[posI - 1][posJ] != '*') {
				posI--;
				res = 0;
			}
		}
		case "ABAJO" -> {
			if (posI + 1 < tablero.length && tablero[posI + 1][posJ] != '*') {
				posI++;
				res = 0;
			}
		}
		case "DERECHA" -> {
			if (posJ + 1 < tablero[0].length && tablero[posI][posJ + 1] != '*') {
				posJ++;
				res = 0;
			}
		}
		case "IZQUIERDA" -> {
			if (posJ - 1 >= 0 && tablero[posI][posJ - 1] != '*') {
				posJ--;
				res = 0;
			}
		}
		default -> res = -2;
		}

		return res;
	}
}
