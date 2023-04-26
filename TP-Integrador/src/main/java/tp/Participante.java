package tp;

public class Participante {
	
	private String participante;
	private String descripcion;
	
	public Participante(String participante) {
		this.participante = participante;
	}		
	public String getParticipante() {
		return participante;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//public int puntosParticipante() {
		
		//if(this.participante.equals(participante)) {
		//	cont += pronostico.puntos();
		//	return cont;
		//}
	//}
	//	EnumResultado resultadoReal = this.partido.resultado(this.equipo);
	//	if(this.resultado.equals(resultadoReal)) {
	//		return 1;
	//	}else {
	//		return 0;
	//	}
	//	Pronostico pronostico = new Pronostico(partido,equipo,resultado);
	//	puntos += pronostico.puntos();
}
