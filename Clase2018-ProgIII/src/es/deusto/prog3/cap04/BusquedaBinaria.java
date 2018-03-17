package es.deusto.prog3.cap04;

public class BusquedaBinaria {
	private static int TAMANYO_ARRAY = 100000;
	private static int[] array;

	private static int[] inicializaVector( int tam ) {
		array = new int[tam];
		for (int i=0; i<tam; i++) {
			array[i] = i*2+1;   // Mete los "n" primeros impares
		}
		return array;
	}
	
	/** Busca un valor en un vector de enteros ORDENADO
	 * @param array	Array de valores ordenado
	 * @param valor	Valor que se busca
	 * @return	Posici�n en el que el valor est�, -1 si no existe
	 */
	public static int buscaEnVector( int[] array, int valor ) {
		// Previo
		return buscaEnVector( array, valor, 0, array.length-1 );
		// Posterior
	}
		private static int contLlamadas = 0;
		private static int contComparaciones = 0;
		private static int buscaEnVector( int[] array, int valor, int ini, int fin  ) {
			contLlamadas++;
			// System.out.println( "Busco en " + ini + "," + fin );
			contComparaciones++;
			if (ini>fin) return -1;  // Caso base: no encontrado
			int mitad = (ini + fin) / 2;
			contComparaciones++;
			if (array[mitad]==valor) { // Caso base: encontrado
				return mitad;
			} else {
				contComparaciones++;
				if (array[mitad]>valor) {  // Izquierda
					return buscaEnVector( array, valor, ini, mitad-1 );
				} else {  // Derecha
					return buscaEnVector( array, valor, mitad+1, fin );
				}
			}
		}

		// Versi�n 2: Alguna comparaci�n menos (atrasando el == que es el que menos veces pasa)
		private static int buscaEnVector2( int[] array, int valor, int ini, int fin  ) {
			contLlamadas++;
			contComparaciones++;
			if (ini>fin) return -1;
			int mitad = (ini + fin) / 2;
			contComparaciones++;
			if (array[mitad]>valor) {
				return buscaEnVector2( array, valor, ini, mitad-1 );
			} else {
				contComparaciones++;
				if (array[mitad]==valor) {
					return mitad;
				} else {
					return buscaEnVector2( array, valor, mitad+1, fin );
				}
			}
		}
		
		// Versi�n 3: Dividiendo el vector siempre en mitades (no en 3 partes) SIN COMPARAR el elemento intermedio
		private static int buscaEnVector3( int[] array, int valor, int ini, int fin  ) {
			contLlamadas++;
			contComparaciones++;
			if (ini==fin) {
				contComparaciones++;
				if (array[ini]==valor) {
					return ini;
				} else {
					return -1;
				}
			} else {
				int mitad = (ini + fin) / 2;
				contComparaciones++;
				if (array[mitad]>=valor) {
					return buscaEnVector3( array, valor, ini, mitad );  // Donde va el == va la mitad
				} else {  // <
					return buscaEnVector3( array, valor, mitad+1, fin ); // Donde no, el de la mitad se desprecia (2 partes)
				}
			}
		}
		
	public static void main(String[] args) {
		array = inicializaVector( TAMANYO_ARRAY ); // Inicializa del 1 al n
		System.out.println( buscaEnVector( array, 10 ) ); // Pos
		System.out.println( contLlamadas );
		// Comentar a partir de aqu� si no se quiere hacer el test de llamadas y comparaciones
		// Test con versi�n 1
		contLlamadas = 0; contComparaciones = 0;
		for (int i=0; i<= TAMANYO_ARRAY; i++) buscaEnVector( array, i );
		System.out.println( "Versi�n 1: " + contLlamadas + " llamadas y " + contComparaciones + " comparaciones." );
		// Test con versi�n 2
		contLlamadas = 0; contComparaciones = 0;
		for (int i=0; i<= TAMANYO_ARRAY; i++) buscaEnVector2( array, i, 0, TAMANYO_ARRAY-1 );
		System.out.println( "Versi�n 2: " + contLlamadas + " llamadas y " + contComparaciones + " comparaciones." );
		// Test con versi�n 1
		contLlamadas = 0; contComparaciones = 0;
		for (int i=0; i<= TAMANYO_ARRAY; i++) buscaEnVector3( array, i, 0, TAMANYO_ARRAY-1  );
		System.out.println( "Versi�n 3: " + contLlamadas + " llamadas y " + contComparaciones + " comparaciones." );
		// Chequeo de correcci�n si se quisiera hacer (por ejemplo para test de unidad):
		// for (int i=0; i<= TAMANYO_ARRAY; i++) { 
		// 		int b = buscaEnVector3( array, i, 0, TAMANYO_ARRAY-1  ); 
		//		if ((i%2==0 && b!=-1) || (i%2!=0 && b==-1)) 
		//			System.out.println( "Error en b�squeda de " + i + " con retorno " + b );
		// }
	}

}