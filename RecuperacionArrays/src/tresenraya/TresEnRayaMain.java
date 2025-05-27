package tresenraya;

import java.util.Scanner;

public class TresEnRayaMain {
	public static void main(String[] args) {
		// Scanner
		Scanner rd = new Scanner(System.in);

		// String para la opcion de seguir jugando
		String keepPlaying = "";

		// Posiciones de fila y columna para el usuario
		int posF = 0;
		int posC = 0;
		
		//Variable que almacena de quien es el turno
		int turno = 0;

		// Contador de turnos
		int cont = 0;

		// booleano para la victoria
		boolean win = false;

		// Bienvenida
		System.out.println("Bienvenido/a al 3 en Raya!");

		do {
			// Inicializamos tablero
			TresEnRaya.limpiaTablero();
			System.out.println("Lanzando Moneda...");
			turno = TresEnRaya.jugadorInicial();
			cont = 0;
			do {
				if (turno == 1) {
					// Informamos del turno
					System.out.println("Tu Turno!");

					// Imprimimos tablero
					TresEnRaya.imprimeTablero();

					// Pedimos posiciones de columna y fila
					System.out.println("Introduce la posicion de la fila");
					posF = rd.nextInt();
					System.out.println("Introduce la posicion de la columna");
					posC = rd.nextInt();

					// Intentamos colocar la ficha y damos feedback del resultado
					while (!TresEnRaya.usuarioMueveFicha(posF, posC)) {
						System.out.println("Posiciones incorrectas, introducelas de nuevo");

						// Pedimos posiciones de columna y fila
						System.out.println("Introduce la posicion de la fila");
						posF = rd.nextInt();
						System.out.println("Introduce la posicion de la columna");
						posC = rd.nextInt();
					}

					//Limpio buffer
					rd.nextLine();
					
					System.out.println("Ficha colocada");
					
					//Imprime el tablero
					TresEnRaya.imprimeTablero();

					
					win = TresEnRaya.esGanador('X');
						
					
				} else {
					System.out.println("Turno de la maquina");
					
					//la maquina mueve ficha
					TresEnRaya.mueveFichaAleatoria();
					
					//Se imprime el tablero
					TresEnRaya.imprimeTablero();
					
					win = TresEnRaya.esGanador('O');
				}
				
				//Se incrementa el turno
				cont++;
				
				//Cambiamos el turno
				turno = turno == 1 ? 0 : 1;
			} while (!win && cont < 9);
			
			if (win) {
				System.out.println(turno==1 ? "Ha ganado la máquina" : "Has ganado tu");
			} else {
				System.out.println("Empate");
			}

			//Se le pregunta si quiere seguir
			System.out.println("Quieres seguir jugando? (y/n)");
			keepPlaying = rd.nextLine();
			
		} while (keepPlaying.equalsIgnoreCase("y"));
		
		//Cierro scanner
		rd.close();
	}
}
