package tp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MainTP {
	
	public static void main(String[] args) {
		
		
		Collection<Partido> partidos = new ArrayList<Partido>();
		//Leer resultados (archivo en resources)
		Path pathResultados = Paths.get(args[0]);
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
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[3]);
				Partido partido = new Partido(equipo1, equipo2);
				partido.setGolesEq1(Integer.parseInt(campos[1]));
				partido.setGolesEq2(Integer.parseInt(campos[2]));
				partidos.add(partido); //guardar cada partido (iteracion del for) en la coleccion
			}
		}
		int puntos = 0;
		//Leer pronostico (archivo en resources)		
		Path pathPronostico = Paths.get(args [1]);
		List<String> lineasPronostico = null;
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
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[4]);
				Partido partido = null;
				
				for(Partido partidoCol: partidos) { //recorrer la coleccion
					if (partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) //comparar los equipos para identificar los partidos
						&& partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())){ //incorporar el id partido y ronda para la proxima entrega
						partido = partidoCol; 
					}
				}
				
				Equipo equipo = null;
				EnumResultado resultado = null;
				if ("X".equals(campos[1])) {
					equipo = equipo1;
					resultado = EnumResultado.GANADOR;
				}
				if ("X".equals(campos[2])) {
					equipo = equipo1;
					resultado = EnumResultado.EMPATE;
				}
				if ("X".equals(campos[3])) {
					equipo = equipo1;
					resultado = EnumResultado.PERDEDOR;
				}
				Pronostico pronostico = new Pronostico(partido,equipo,resultado);
				puntos += pronostico.puntos();
				
			}
		}
		System.out.print("los puntos obtenidos por el usuario fueron: ");
		System.out.println(puntos);
	}

}
