package es.deusto.prog3.cap04;

public class PruebasRecursivas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//f(0);
		System.out.println( factorial(12) );
		System.out.println( string1_10_1(1) );
		System.out.println( 1/c(32) );
	}
	
		private static double c(int n) { if (n==1) return 1/4.0; else return c(n-1)/4.0; } 
	
	private static void f(int i) {
		if (i<1000)
			f(i+1);
		// else caso base
		System.out.println( i );
	}
	
	/** Devuelve el factorial de un número
	 * @param n	Número mayor o igual a 0
	 * @return	Factorial de n
	 */
	private static long factorial(long n) {
		// Definición factorial:
		// factorial(n) = n * factorial(n-1)  si n>0
		// factorial(n) = 1 si n==0
		if (n==0)
			return 1;
		else
			return n * factorial(n-1);
	}

	/** Devuelve un string formado de todos los números
	 * subiendo entre n y 10 y bajando de nuevo hasta n
	 * Ejemplo: "1 2 3 ... 9 10 9 ... 3 2 1". 
	 * @param n	número en el que empezar (menor o igual a 10)
	 * @return String buscado
	 */
	private static String string1_10_1( int n ) {
		// Definición recursiva
		// devuelvo n + " " + string_1_10_1(n+1) + " " + n
		// caso base: n==10 devuelvo "10"
		if (n==10)
			return "10";
		else
			return n + " " + string1_10_1(n+1) + " " + n;
	}
}
