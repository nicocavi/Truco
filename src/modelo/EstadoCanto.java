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
			if(!envido && !flor && (ronda == 1)) {
				aux.add(CantoJuego.ENVIDO);
				aux.add(CantoJuego.REALENVIDO);
				aux.add(CantoJuego.FALTAENVIDO);
				if(jugador.hayFlor()){
					aux.add(CantoJuego.FLOR);
				}
			}
			
		}else if((cantos.get(cantos.size() - 1).getCanto() == CantoJuego.TRUCO) ) {
		
			if(!envido && !flor &&(!cantos.get(cantos.size() - 1).getEstado()) && (ronda == 1)) {
				aux.add(CantoJuego.ENVIDO);
				aux.add(CantoJuego.REALENVIDO);
				aux.add(CantoJuego.FALTAENVIDO);
				if(jugador.hayFlor()) {
					aux.add(CantoJuego.FLOR);
				}
			}
			aux.add(CantoJuego.RETRUCO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDO) {
			aux.add(CantoJuego.ENVIDOX2);
			aux.add(CantoJuego.ENVIDOREALENVIDO);
			aux.add(CantoJuego.ENVIDOFALTAENVIDO);
			if(jugador.hayFlor() && !cantos.get(cantos.size() - 1).getEstado()) {
				aux.add(CantoJuego.FLOR);
			}
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.REALENVIDO) {
			aux.add(CantoJuego.REALENVIDOFALTAENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOX2) {
			aux.add(CantoJuego.ENVIDOX2REALENVIDO);
		}if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOREALENVIDO) {
			aux.add(CantoJuego.ENVIDOREALENVIDOFALTAENVIDO);
		}if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.ENVIDOX2REALENVIDO) {
			aux.add(CantoJuego.ENVIDOX2REALENVIDOFALTAENVIDO);
		}else if(cantos.get(cantos.size() - 1).getCanto() == CantoJuego.FLOR && jugador.hayFlor()) {
			aux.add(CantoJuego.CONTRAFLOR);
			aux.add(CantoJuego.CONTRAFLORALJUEGO);
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
	}
	
	public void addCanto(Canto canto) {
		if(cantos.get(0).getCanto() == CantoJuego.TRUCO && canto.getCanto() != CantoJuego.RETRUCO) {
			cantoPendiente = cantos.get(0);
			cantos.set(0, canto);
		}else{
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
