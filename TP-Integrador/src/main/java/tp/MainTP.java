package tp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

//import java.sql.*;


//import static conexion.sql.conectorSQL.DB_URL;
//import static conexion.sql.conectorSQL.USER;
//import static conexion.sql.conectorSQL.PASS;

public class MainTP {
	
	public static void main(String[] args) {
		
		HashMap<String, Integer> participantes = new HashMap<String, Integer>();
		Collection<Partido> partidos = new ArrayList<Partido>();
		//Collection<Participante> participantes = new ArrayList<Participante>();
		//Leer resultados (archivo en resources)
		Path pathResultados = Paths.get("src/test/resources/resultados_test1.csv");
		List<String> lineasresultados = null;
		try {
			lineasresultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados.");
			System.out.println(e.getMessage()); // muestra el error
			System.exit(1); // System.exit()Finaliza el programa (solo en el main)
			//exit(0) si el programa finaliza correctamente
			//exit(1) si el programa no finaliza por causa de algun error
		}
		boolean primera = true; 
		for (String linearesultado : lineasresultados){    //leer las lineas del archivo
			if(primera) {             //evitar la primera linea
				primera = false;
			} else {
				String[] campos = linearesultado.split(","); //separar la linea (es un String)en elementos de un String[]
				Ronda ronda = new Ronda(campos[0]);
				Equipo equipo1 = new Equipo(campos[2]);
				Equipo equipo2 = new Equipo(campos[5]);
				//Partido partido = new Partido(campos[0]);
				Partido partido = new Partido(ronda, equipo1, equipo2);
				
				partido.setGolesEq1(Integer.parseInt(campos[3]));
				partido.setGolesEq2(Integer.parseInt(campos[4]));
				partidos.add(partido); //guardar cada partido (iteracion del for) en la coleccion
			}
		}
		int puntos = 0;
		//Leer pronostico (archivo en resources)		
		Path pathPronostico = Paths.get("src/test/resources/pronostico_test1.csv");
		List<String> lineasPronostico = null;
		//ArrayList<Pronostico> lineasPronostico =lectorSQL.obtenerPronostico();
		
		try {
			lineasPronostico = Files.readAllLines(pathPronostico);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de pronosticos.");
			System.out.println(e.getMessage()); // muestra el error
			System.exit(1); // System.exit()Finaliza el programa (solo en el main)
			//exit(0) si el programa finaliza correctamente
			//exit(1) si el programa no finaliza por causa de algun error
		}
		primera = true; //por que no me permite usar boolean nuevamente??
		for (String lineaPronostico : lineasPronostico){    //leer las lineas del archivo
			if(primera) {             //evitar la primera linea
				primera = false;
			} else {
				String[] campos = lineaPronostico.split(",");
				String[] participante = {campos[7]};
				Ronda ronda = new Ronda(campos[0]);
				Equipo equipo1 = new Equipo(campos[2]);
				Equipo equipo2 = new Equipo(campos[6]);
				//Participante participante1 = new Participante(campos[7]);
				Partido partido = null;
				
				//System.out.println(ronda.getRonda());
				//System.out.println(participante.getParticipante());
				//System.out.println(equipo1.getNombre());
				//System.out.println(equipo2.getNombre());
								
				for(Partido partidoCol: partidos) { //recorrer la coleccion
					if (partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) //comparar los equipos para identificar los partidos
						&& partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())
						&& partidoCol.getRonda().getRonda().equals(ronda.getRonda()) ){ //incorporar el id partido y ronda para la proxima entrega
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
								
				Pronostico pronostico = new Pronostico(partido,equipo,resultado);
				puntos = pronostico.puntos();
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
		
			
		for (String clave : participantes.keySet()) {
		    Integer valor = participantes.get(clave);
		    System.out.println("Los puntos obtenidos por el participante " + clave + " fueron: " +valor);
		}
		
		
	}


}
