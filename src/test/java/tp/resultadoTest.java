package tp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

class resultadoTest {
	
	//test para validar que todos los datos del archivo resultado esten cargados.
	@Test
	void ResultadoTest() {
		Path pathResultados = Paths.get("src/test/resources/resultados_test1.csv");
		List<String> lineasresultados = null;
		try {
			lineasresultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados.");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		for (String linearesultado : lineasresultados){
			String[] campos = linearesultado.split(","); 			
			for (String campo : campos) {
			assertNotSame(campo,"");
			}
			assertNotSame(campos[2],campos[5]);
		}
		
		
	}
	

}
