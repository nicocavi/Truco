package modelo;

import java.util.ArrayList;

public class Juego {
	private ArrayList<Jugador> jugadores;
	private int puntosLimite;
	private EstadoJuego estadoJuego;
	private ArrayList<Integer> manosGanadas;
	private EstadoCanto estadoCanto;
	private int ronda;
	private Mesa mesa;
	private int turno;
	private int turnoInicio;
	private Mazo mazo;
	private int jugadas;
	private CantoJuego cantoJugador;
	
	public Juego() {
		estadoJuego = EstadoJuego.SETEANDO;
		puntosLimite = 30;
		jugadores = new ArrayList<Jugador>();
		turnoInicio = 1;
	}
	
	// METODOS PUBLICOS --------------------------------- 
	
	public void agregarJugador(String nombre) {
		jugadores.add(new Jugador(nombre));
		if(jugadores.size() == 2) {
			estadoJuego = EstadoJuego.JUGANDO;
			iniciar();
		}
	}

	public void tirarCarta(int carta) {
		
		Carta cartaAux = jugadores.get(turno).tirarCarta(carta);
		jugadas++;
		if(turno == 0) {
			mesa.setMesaJUno(cartaAux);
		}else {
			mesa.setMesaJDos(cartaAux);
		}
		
		ArrayList<Integer> turnosGanados = mesa.getTurnosGanados();
		int j1 = 0, j2 = 0;
		
		for(int i = 0; i < turnosGanados.size(); i++) {
			if(turnosGanados.get(i) == 0) {
				j1++;
			}else if(turnosGanados.get(i) == 1) {
				j2++;
			}
		}
		
		if(j2 >= 2) {
			calcularPuntos(jugadores.get(1));
		}else if(j1 >= 2) {
			calcularPuntos(jugadores.get(0));
		}else if(ronda >= 2){
			calcularPuntos(jugadores.get(turnoInicio));
		}else {
			if(finRonda()) {
				if(turnosGanados.get(ronda-1) == -1) {
					turno = turnoInicio;
				}else {
					turno = turnosGanados.get(ronda-1);
				}
			}else {
				siguienteTurno();
			}
		}	
		
		
	}
	
	public EstadoJuego getEstado() {
		return estadoJuego;
	}
	
	public String getNombreJUno() {
		return jugadores.get(0).getNombre();
	}
	
	public String getNombreJDos() {
		return jugadores.get(1).getNombre();
	}

	public int getPuntosJUno() {
		return jugadores.get(0).getPuntos();
	}
	
	public int getPuntosJDos() {
		return jugadores.get(1).getPuntos();
	}
	
	public String getTurnoJugador() {
		return jugadores.get(turno).getNombre();
	}
	
	public ArrayList<Carta> getManoJugador(String nombre){
		return buscarJugador(nombre).getMano();
	}
	
	public String getMesaJUno(){
		return mesa.getMesaJUno();
	}
	
	public String getMesaJDos(){
		return mesa.getMesaJDos();
	}
	
	public boolean hayCantoPendiente() {
		return estadoCanto.hayCantoPendiente();
	}
	
	public String getOponente() {
		if(turno == 0) {
			return jugadores.get(1).getNombre();
		}else {
			return jugadores.get(0).getNombre();
		}
	}
	
	public Canto getCanto() {
		return estadoCanto.getCanto();
	}
	
	public Jugador jugadorGanador() {
		if(jugadores.get(0).getPuntos() > jugadores.get(1).getPuntos()) {
			return jugadores.get(0);
		}else {
			return jugadores.get(1);
		}
	}
	
	public ArrayList<CantoJuego> cantosDisponibles(){
		return estadoCanto.cantosDisponibles(ronda, jugadores.get(turno));
	}
	
	public void aceptarCanto() {
		estadoCanto.aceptarCanto();
		
		CantoJuego aux = estadoCanto.getCanto().getCanto();
		turno = jugadores.indexOf(estadoCanto.getPrimerCanto().getJugador());
		
		/*if(aux != CantoJuego.TRUCO && aux != CantoJuego.RETRUCO && aux != CantoJuego.VALECUATRO) {
			turno = jugadores.indexOf(estadoCanto.getCanto().getJugador());
		}else {
			siguienteTurno();
		}*/
		
		
	}
	
	public void rechazarCanto() {
		if (turno == 0) {
			calcularPuntos(jugadores.get(1));
		}else {
			calcularPuntos(jugadores.get(0));
		}
	}
	
	public void finMano() {
		if(ronda == 0 && !estadoCanto.getTruco() && !estadoCanto.getEnvido() && !estadoCanto.getFlor()) {
			if (turno == 0) {
				jugadores.get(1).addPuntos(2);
			}else {
				jugadores.get(0).addPuntos(2);;
			}
			iniciar();	
		}else {
			
			if (turno == 0) {
				jugadores.get(1).addPuntos(1);
				if(estadoCanto.getCanto().getCanto() != CantoJuego.VACIO) {
					calcularPuntos(jugadores.get(1));
				}
				
			}else {
				jugadores.get(0).addPuntos(1);
				if(estadoCanto.getCanto().getCanto() != CantoJuego.VACIO) {
					calcularPuntos(jugadores.get(0));
				}
			}
		}
		
			
		
	}
	
	public void addCanto(CantoJuego canto) {
		estadoCanto.addCanto(new Canto(canto, jugadores.get(turno)));
		siguienteTurno();
	}
	
	public int getPuntosCanto() {
		if(estadoCanto.getCanto().getCanto().ordinal() > 0 && estadoCanto.getCanto().getCanto().ordinal() < 12) {
			return jugadores.get(turno).calcularEnvido();
		}else if(estadoCanto.getCanto().getCanto().ordinal() > 14){
			return jugadores.get(turno).calcularFlor();
		}else {
			return 0;
		}
	}
	
	
	
	public void respuestaJugador(boolean respuesta) {
		if(respuesta) {
			if(cantoJugador == CantoJuego.VACIO) {
				cantoJugador = CantoJuego.ACEPTO;
				siguienteTurno();
			}else {
				calcularPuntos(ganadorCanto());
			}
			
		}else {
			cantoJugador = CantoJuego.NOACEPTO;
			rechazarCanto();
		}
		
	}
	
	public CantoJuego getCantoJugador() {
		return cantoJugador;
	}
	
	// METODOS PRIVADOS --------------------------------- 
	
	
	private void iniciar(){
		if(estadoJuego == EstadoJuego.JUGANDO) {
			
			jugadores.get(0).limpiarMano();
			jugadores.get(1).limpiarMano();
			cantoJugador = CantoJuego.VACIO;
			
			manosGanadas = new ArrayList<Integer>();
			for(int i = 0; i < 3; i++) {
				manosGanadas.add(-1);
			}
			jugadas=0;
			ronda = 0;
			mazo = new Mazo();
			repartirCartas();
			if(turnoInicio == 1) {
				turnoInicio = 0;
				turno = 0;
			}else if(turnoInicio == 0){
				turnoInicio = 1;
				turno = 1;
			}
			mesa = new Mesa();
			estadoCanto = new EstadoCanto();
		}
	}
	
	
	private Jugador buscarJugador(String nombre) {
		if(jugadores.get(0).getNombre() == nombre) {
			return jugadores.get(0);
		}else {
			return jugadores.get(1);
		}
	}
	
	private boolean finRonda() {
		
		if((jugadas % 2) == 0 && jugadas != 0) {
			ronda++;
			return true;
		}
		return false;
	}
	
	private void repartirCartas(){
		for(int i = 0; i<3; i++) {
			jugadores.get(0).addCarta(mazo.repartirCarta());
			jugadores.get(1).addCarta(mazo.repartirCarta());
		}
	}
	
	private void siguienteTurno() {

		if(turno == 0) {
			turno = 1;
		}else {
			turno = 0;
		}
	}
	
	private void calcularPuntos(Jugador ganador) {
		Canto canto = estadoCanto.getCanto();
		
		if(canto.getEstado()) {
			//Si el Canto fue aceptado...
			
			switch(canto.getCanto()) {
			
				case TRUCO:
					ganador.addPuntos(2);
					break;
				case RETRUCO:
					ganador.addPuntos(3);
					break;
				case VALECUATRO:
					ganador.addPuntos(4);
					break;
				case ENVIDO:
					ganador.addPuntos(2);
					break;
				case REALENVIDO:
					ganador.addPuntos(3);
					break;
				case ENVIDOX2:
					ganador.addPuntos(4);
					break;
				case ENVIDOREALENVIDO:
					ganador.addPuntos(5);
					break;
				case ENVIDOX2REALENVIDO:
					ganador.addPuntos(7);
					break;
				case FLOR:
					ganador.addPuntos(4);
					break;
				case CONTRAFLOR:
					ganador.addPuntos(6);
					break;
				default:
					if(jugadorGanador().getPuntos() > puntosLimite/2 ) {
						ganador.addPuntos(puntosLimite - jugadorGanador().getPuntos());
					}else {
						ganador.addPuntos(puntosLimite - ganador.getPuntos());
					}
					break;
			}

		}else {
			//Si el Canto no fue aceptado...
			
			switch(canto.getCanto()) {
			
				case TRUCO:
					ganador.addPuntos(1);
					break;
				case RETRUCO:
					ganador.addPuntos(2);
					break;
				case VALECUATRO:
					ganador.addPuntos(3);
					break;
				case ENVIDO:
					ganador.addPuntos(1);
					break;
				case REALENVIDO:
					ganador.addPuntos(1);
					break;
				case ENVIDOX2:
					ganador.addPuntos(2);
					break;
				case ENVIDOREALENVIDO:
					ganador.addPuntos(2);
					break;
				case ENVIDOX2REALENVIDO:
					ganador.addPuntos(4);
					break;
				case FALTAENVIDO:
					ganador.addPuntos(1);
					break;
				case ENVIDOFALTAENVIDO:
					ganador.addPuntos(2);
					break;
				case REALENVIDOFALTAENVIDO:
					ganador.addPuntos(3);
					break;
				case ENVIDOREALENVIDOFALTAENVIDO:
					ganador.addPuntos(5);
					break;
				case ENVIDOX2REALENVIDOFALTAENVIDO:
					ganador.addPuntos(7);
					break;
				case FLOR:
					ganador.addPuntos(3);
					break;
				case CONTRAFLOR:
					ganador.addPuntos(4);
					break;
				case CONTRAFLORALRESTO:
					ganador.addPuntos(6);
					break;
				default:
					ganador.addPuntos(1);
					break;
			}
		}
		
		if(hayGanador()) {
			estadoJuego = EstadoJuego.FINALIZANDO;
		}else {
			
			if(canto.getCanto() == CantoJuego.TRUCO || canto.getCanto() == CantoJuego.RETRUCO || canto.getCanto() == CantoJuego.VALECUATRO || canto.getCanto() == CantoJuego.VACIO) {
				iniciar();
			}else {
				estadoCanto.limpiarCantos();
				siguienteTurno();
			}

		}
		
	}
	
	private boolean hayGanador() {
		if(jugadores.get(0).getPuntos() == puntosLimite || jugadores.get(1).getPuntos() == puntosLimite) {
			return true;
		}else {
			return false;
		}
	}
	
	private Jugador ganadorCanto() {
		cantoJugador = CantoJuego.VACIO;
		if(estadoCanto.getCanto().getCanto().ordinal() > 0 && estadoCanto.getCanto().getCanto().ordinal() < 12) {
			if(jugadores.get(0).calcularEnvido() < jugadores.get(1).calcularEnvido()) {
				return jugadores.get(1);
			}else if(jugadores.get(0).calcularEnvido() > jugadores.get(1).calcularEnvido()){
				return jugadores.get(0);
			}else {
				return jugadores.get(turnoInicio);
			}
		}else{
			if(jugadores.get(0).calcularFlor() < jugadores.get(1).calcularFlor()) {
				return jugadores.get(1);
			}else if(jugadores.get(0).calcularFlor() > jugadores.get(1).calcularFlor()){
				return jugadores.get(0);
			}else {
				return jugadores.get(turnoInicio);
			}
		}
	}
}
