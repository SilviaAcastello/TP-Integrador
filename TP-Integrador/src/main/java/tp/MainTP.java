package tp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainTP {
	
	public static void main(String[] args) {
		
		HashMap<String, Integer> participantes = new HashMap<String, Integer>();
		Collection<Partido> partidos = new ArrayList<Partido>();
		
		//leer el archivo resultados_test1
		Path pathResultados = Paths.get("src/test/resources/resultados_test1.csv");
		List<String> lineasresultados = null;
		try {
			lineasresultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados.");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		//leer las lineas del archivo resultados_test1
		boolean primera = true; 
		for (String linearesultado : lineasresultados){    
			
			//evitar la primera linea
			if(primera) {             
				primera = false;
			} else {
				
				//separar la linea 
				String[] campos = linearesultado.split(","); 
				Ronda ronda = new Ronda(campos[0]);
				Equipo equipo1 = new Equipo(campos[2]);
				Equipo equipo2 = new Equipo(campos[5]);
				Partido partido = new Partido(ronda, equipo1, equipo2);
				
				//guardar cada partido en la coleccion
				partido.setGolesEq1(Integer.parseInt(campos[3]));
				partido.setGolesEq2(Integer.parseInt(campos[4]));
				partidos.add(partido); 
			}
		}
		
		//Leer el archivo pronostico_test1	
		int puntos = 0;	
		Path pathPronostico = Paths.get("src/test/resources/pronostico_test1.csv");
		List<String> lineasPronostico = null;
		try {
			lineasPronostico = Files.readAllLines(pathPronostico);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de pronosticos.");
			System.out.println(e.getMessage()); 
			System.exit(1);
		}
		
		//leer las lineas del archivo pronostico_test1
		primera = true;
		for (String lineaPronostico : lineasPronostico){ 
			//evitar la primera linea
			if(primera) {             
				primera = false;
			} else {
				String[] campos = lineaPronostico.split(",");
				String[] participante = {campos[7]};
				Ronda ronda = new Ronda(campos[0]);
				Equipo equipo1 = new Equipo(campos[2]);
				Equipo equipo2 = new Equipo(campos[6]);
				Partido partido = null;
				
				//recorrer la coleccion
				for(Partido partidoCol: partidos) { 
					//comparar los equipos para identificar los partidos
					if (partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) 
						&& partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())
						&& partidoCol.getRonda().getRonda().equals(ronda.getRonda()) ){ 
						partido = partidoCol; 					
					}
				}
				
						
				Equipo equipo = null;
				EnumResultado resultado = null;
				if ("X".equals(campos[3])) {
					equipo = equipo1;
					resultado = EnumResultado.GANADOR;
				}
				if ("X".equals(campos[4])) {
					equipo = equipo1;
					resultado = EnumResultado.EMPATE;
				}
				if ("X".equals(campos[5])) {
					equipo = equipo1;
					resultado = EnumResultado.PERDEDOR;
				}					
				
				//generar un nuevo pronostico
				Pronostico pronostico = new Pronostico(partido,equipo,resultado);
				puntos = pronostico.puntos();
				
				//recorrer el diccionario participantes y asignar los puntos a cada jugador
				participantes.put(campos[7], 0);
				for (String clave : participante) {
					if (participantes.containsKey(clave)){
						participantes.put(clave, participantes.get(clave)+ puntos);
					}else {
						participantes.put(clave, puntos);
					}
				}
			}			
		}
		
		//recorrer el deccionario para mostrar cada participante y su respectivo puntaje	
		for (String clave : participantes.keySet()) {
		    Integer valor = participantes.get(clave);
		    System.out.println("Los puntos obtenidos por el participante " + clave + " fueron: " +valor);
		}
		
		
	}


}
