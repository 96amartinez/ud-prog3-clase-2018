
/** Ejemplo de anotación clásica de toString  (@Override)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjAnots2 /* extends Object */ {
	private int i;
	public static void main(String[] args) {
		EjAnots2 a = new EjAnots2();
		System.out.println( a );
	}

	@Override
	public String toString() {
		return i + "";
	}
	
}
