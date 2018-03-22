package es.deusto.prog3.cap04;

import java.io.File;

public class VerCarpeta {

	public static void main(String[] args) {
		File file = new File( "d:/workspace" );
		System.out.println( file.getName() );
		visualizarN( file, 2 );
	}
	
	// pre file es un directorio
	/** visualiza los ficheros y carpetas de nivel N
	 * @param file	directorio de inicio de la visualización
	 * @param n	su nivel
	 */
	private static void visualizarN( File file, int n ) {
		for (File f2 : file.listFiles()) {
			for (int esp=0; esp<(n-1)*2; esp++) System.out.print( " " );
			if (f2.isFile()) {
				System.out.println( "(F) " + f2.getName() );
			} else {
				System.out.println( "(D) " + f2.getName() );
				visualizarN( f2, n+1 );
			}
		}
	}
	
}
