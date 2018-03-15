package es.deusto.prog3.cap01;

import static es.deusto.prog3.cap01.EjemploUtilidadesStringParaJUnit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EjemploUtilidadesStringParaJUnitTest2 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// Pruebas del método quitarTabsYSaltosLinea
	@Test
	public void quitarTabsYSaltosLineaTest() {
		String prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
		String prueba2 = "Hola#Esto es un string con tres líneas#y|varios|tabuladores.";
		// System.out.println( prueba );
		// System.out.println( quitarTabsYSaltosLinea( prueba ));
		assertEquals( prueba2,
				EjemploUtilidadesStringParaJUnit.
				quitarTabsYSaltosLinea(prueba)
				);
		double d = 4.5;
		assertEquals( 4.5, d, 0.0000001 );
		// Probar "\n" -> "#"
		// y ... "\n\n" -> "##"
		// y así hasta 10 \n
		String orig = "";
		String objetivo = "";
		for (int i=0; i<10; i++) {
			orig += "\n";
			objetivo += "#";
			assertEquals( objetivo, 
					EjemploUtilidadesStringParaJUnit.
					quitarTabsYSaltosLinea( orig ) );
		}
		//assertSame( null, quitarTabsYSaltosLinea(null) );
		try {
			assertNull( quitarTabsYSaltosLinea(null) );
		} catch (Exception e) {
			fail( "Excepción no esperada por null" );
		}
	}

}
