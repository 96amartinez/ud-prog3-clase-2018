package es.deusto.prog3.examenes.ord201801;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class UtilsGPSTest {

	@Test
	public void gpsDentroDePoligonoTest() {
		// TAREA 1
	}

		// Método para comprobar todas las zonas
		// (no se daba en el examen original)
		private boolean puntoEstaEnAlgunaZona( PuntoGPS punto ) {
			Iterator<Zona> itZona = GrupoZonas.jardinesErandio.getIteradorZonas();
			while (itZona.hasNext()) {
				Zona zona = itZona.next();
				for (ArrayList<PuntoGPS> subzona : zona.getPuntosGPS()) {
					if (UtilsGPS.gpsDentroDePoligono( punto, subzona )) return true;
				}
			}
			return false;
		}
	
}
