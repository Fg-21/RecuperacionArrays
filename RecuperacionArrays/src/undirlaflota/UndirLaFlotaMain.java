package undirlaflota;

public class UndirLaFlotaMain {
	public static void main(String[] args) {
		char[][] tableroMain;
		char[][] tableroJ1;
		char[][] tableroJ2;
		
		tableroMain = UndirLaFlota.creaTablero(5, 5);
		UndirLaFlota.generarBarquitos(tableroMain, 9);
		tableroJ1 = UndirLaFlota.inicializaTablero(5, 5);
		tableroJ2 = UndirLaFlota.inicializaTablero(5, 5);
		
		UndirLaFlota.pintaTablero(tableroMain);
		System.out.println();
		UndirLaFlota.pintaTablero(tableroJ1);
		UndirLaFlota.turnoJugador(tableroMain, tableroJ1);
		UndirLaFlota.pintaTablero(tableroJ1);
	}
}
