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
}
