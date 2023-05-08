package tp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

class pronosticoTest {

	//test para validar que los compos (0,1,2,6,7) del archivo pronostico no esten vacios.
	@Test
	void PronosticoTest() {
		Path pathPronostico = Paths.get("src/test/resources/pronostico_test1.csv");
		List<String> lineasPronostico = null;
		try {
			lineasPronostico = Files.readAllLines(pathPronostico);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de pronosticos.");
			System.out.println(e.getMessage()); 
			System.exit(1);
		}
		for (String lineaPronostico : lineasPronostico){ 
			String[] campos = lineaPronostico.split(",");
			
			assertNotSame(campos[0],"");
			assertNotSame(campos[1],"");
			assertNotSame(campos[2],"");
			assertNotSame(campos[6],"");
			assertNotSame(campos[7],"");
			assertNotSame(campos[2],campos[6]);
		}
	}

}
