package tp;

public class Excepcion extends Exception{
	
	private final Partido partido;
	
	//Solo funciona si en los dos archivos figura un partido con dos equipos iguales
	public Excepcion(Partido partido) {
		this.partido = partido;
		
	}

	public Partido getPartido() {
		return partido;
	}
	

}
