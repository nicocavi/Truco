package modelo;

import java.util.ArrayList;

public class EstadoCanto {
	private ArrayList<Canto> cantos;
	private Canto cantoPendiente;
	private boolean envido,truco,flor;
	
	public EstadoCanto() {
		cantos = new ArrayList<Canto>();
		cantos.add(new Canto());
		cantoPendiente = new Canto();
		envido = false;
		truco = false;
		flor = false;
	}
	
	public ArrayList<CantoJuego> cantosDisponibles(int ronda, Jugador jugador){
		ArrayList<CantoJuego> aux = new ArrayList<CantoJuego>();
		
		if((cantos.get(cantos.size() - 1).getCanto() == CantoJuego.VACIO) && !truco) {
			aux.add(CantoJuego.TRUCO);
			if(!envido && !flor && (ronda == 0)) {
				aux.add(CantoJuego.ENVIDO);
				aux.add(CantoJuego.REALENVIDO);
				aux.add(CantoJuego.FALTAENVIDO);
				if(jugador.hayFlor()){
					aux.add(CantoJuego.FLOR);
				}
			}
			
		}else if((cantos.get(cantos.size() - 1).getCanto() == CantoJuego.TRUCO) ) {
		
			if(!envido && !flor &&(!cantos.get(cantos.size() - 1).getEstado()) && (ronda == 0)) {
				aux.add(CantoJuego.ENVIDO);
				aux.add(CantoJuego.REALENVIDO);
				aux.add(CantoJuego.FALTAENVIDO);
				if(jugador.hayFlor()) {
					aux.add(CantoJuego.FLOR);
				}
			}
			if(cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
				aux.add(CantoJuego.RETRUCO);
			}
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDO && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.ENVIDOX2);
			aux.add(CantoJuego.ENVIDOREALENVIDO);
			aux.add(CantoJuego.ENVIDOFALTAENVIDO);
			if(jugador.hayFlor() && !cantos.get(cantos.size() - 1).getEstado()) {
				aux.add(CantoJuego.FLOR);
			}
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.REALENVIDO && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.REALENVIDOFALTAENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOX2 && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.ENVIDOX2REALENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOREALENVIDO && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.ENVIDOREALENVIDOFALTAENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOX2REALENVIDO && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.ENVIDOX2REALENVIDOFALTAENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.FLOR && jugador.hayFlor() && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()) {
			aux.add(CantoJuego.CONTRAFLOR);
			aux.add(CantoJuego.CONTRAFLORALRESTO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.RETRUCO && cantos.get(cantos.size() - 1).getJugador().getNombre() != jugador.getNombre()){
			aux.add(CantoJuego.VALECUATRO);
		}
		
		return aux;
	}
	
	public void limpiarCantos() {
		cantos.clear();
		if(cantoPendiente.getCanto() != CantoJuego.VACIO) {
			cantos.add(cantoPendiente);
			cantoPendiente = new Canto();
		}else {
			cantos.add(new Canto());
		}
		
	}
	
	public void aceptarCanto() {
		cantos.get(cantos.size() - 1).aceptarCanto();
		System.out.println("Cantos: "+cantos.size());
	}
	
	public void addCanto(Canto canto) {
		
		if(canto.getCanto() == CantoJuego.TRUCO) {
			truco = true;
		}else if(canto.getCanto() == CantoJuego.ENVIDO) {
			envido = true;
		}else if(canto.getCanto() == CantoJuego.FLOR) {
			flor = true;
		}
		
		if(cantos.get(0).getCanto() == CantoJuego.TRUCO && canto.getCanto() != CantoJuego.RETRUCO && canto.getCanto() != CantoJuego.VALECUATRO) {
			cantoPendiente = cantos.get(0);
			cantos.set(0, canto);
		}else if(cantos.get(0).getCanto() == CantoJuego.VACIO){
			cantos.set(0, canto);
		}else {
			cantos.add(canto);
		}

	}
	
	public boolean hayCantoPendiente() {
		if (cantos.get(cantos.size()-1).getCanto() != CantoJuego.VACIO){
			return true;
		}else {
			return false;
		}
	}
	
	public String cantoToString() {
		return cantos.get(cantos.size()-1).getCanto().toString();
	}
	
	public Canto getCanto() {
		return cantos.get(cantos.size()-1);
	}
	
	public Canto getPrimerCanto() {
		return cantos.get(0);
	}
	
	public boolean getTruco() {
		return truco;
	}
	
	public boolean getFlor() {
		return flor;
	}

	public boolean getEnvido() {
		return envido;
	}
}
