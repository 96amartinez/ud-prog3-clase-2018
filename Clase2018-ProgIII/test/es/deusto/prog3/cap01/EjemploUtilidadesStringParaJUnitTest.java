package es.deusto.prog3.cap01;

import static org.junit.Assert.*;
import org.junit.*;

public class EjemploUtilidadesStringParaJUnitTest {

	private String prueba;
	
	@Before
	public void init() {
		prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
	}
	
	@Test
	public void testQuitarTabsYSaltosLinea() {
		String prueba2 = "Hola#Esto es un string con tres líneas#y|varios|tabuladores.";
		// System.out.println( prueba );
		// System.out.println( quitarTabsYSaltosLinea( prueba ));
		assertEquals( prueba2, EjemploUtilidadesStringParaJUnit.quitarTabsYSaltosLinea(prueba) );
	}

	@Test  // (expected = IndexOutOfBoundsException.class)
	public void testWrapString() {
		// System.out.println( wrapString( prueba, 3 ) );
		assertEquals( "Hol...", EjemploUtilidadesStringParaJUnit.wrapString( prueba, 3) );
		// assertTrue( "Hol...".equals( EjemploUtilidadesStringParaJUnit.wrapString( prueba, 3) ) );
		assertEquals( "... hola", EjemploUtilidadesStringParaJUnit.wrapString( "... hola", 10 ));
		assertEquals( "......", EjemploUtilidadesStringParaJUnit.wrapString( "... hola", 3 ));
		assertEquals( "...", EjemploUtilidadesStringParaJUnit.wrapString( "hola", 0 ));
		try {
			EjemploUtilidadesStringParaJUnit.wrapString( "hola", -2 );
			fail( "Excepción no provocada" );
		} catch (IndexOutOfBoundsException e) {
			// assertTrue( true );
		} catch (Exception e) {
			fail( "Excepción no esperada" );
		}
		assertEquals( null, EjemploUtilidadesStringParaJUnit.wrapString( null, 0 ));
	}

}
