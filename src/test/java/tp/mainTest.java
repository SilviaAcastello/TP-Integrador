package tp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class mainTest {
	
	//verificar que se crea el objeto de la clase Equipo
	@Test
	void EquipoTest() {
		Equipo equipo1=new Equipo("Argentina");
		assertNotNull(equipo1);
	}
	
	//si no se carga la cantidad de goles de los equipos se toma como 0 y el resultado es empate.
	@Test
	void ResultadoTest() throws Excepcion{
		Equipo equipo1=new Equipo("Chile");
		Equipo equipo2=new Equipo("Brasil");
		Partido partido1=new Partido(equipo1,equipo2);
		assertNotNull(partido1.resultado(equipo2));
		assertEquals(partido1.resultado(equipo2),EnumResultado.EMPATE);
		assertEquals(partido1.resultado(equipo1),EnumResultado.EMPATE);
	}
	

}
