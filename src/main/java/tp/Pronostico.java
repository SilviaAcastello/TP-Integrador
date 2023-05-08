package tp;


public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private EnumResultado resultado;
	private Ronda Ronda;
	private Participante participante;
	
	public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}
	
	public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado,Participante participante) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
		this.participante = participante;
	}
	
	public Partido getPartido() {
		return this.partido;
	}
	
	public Equipo getEquipo() {
		return this.equipo;
	}
	
	public EnumResultado getResultado() {
		return this.resultado;
	}
	
	public Ronda getRonda() {
		return this.Ronda;
	}
	
	public Participante getParticipante1() {
		return this.participante;
	}
		
	public int puntos() {
		EnumResultado resultadoReal = this.partido.resultado(this.equipo);
		if(this.resultado.equals(resultadoReal)) {
			return 1;
		}else {
			return 0;
		}

	}

}
