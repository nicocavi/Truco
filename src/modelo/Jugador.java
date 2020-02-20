package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable{
	private String nombre;
	private int puntos;
	private ArrayList<Carta> cartas;
	
	public Jugador() {
		nombre = "";
		puntos = 0;
		cartas = new ArrayList<Carta>();
	}
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		puntos = 0;
		cartas = new ArrayList<Carta>();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void addPuntos(int puntos) {
		this.puntos += puntos;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	public void addCarta(Carta carta) {
		cartas.add(carta);
	}
	
	public ArrayList<Carta> getMano(){
		return this.cartas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean hayFlor() {
		if(cartas.size() == 3) {
			if(cartas.get(0).getPalo().equals(cartas.get(1).getPalo()) && (cartas.get(0).getPalo().equals(cartas.get(2).getPalo())) ) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public void limpiarMano() {
		cartas.clear();
	}
	
	public Carta tirarCarta(int carta) {
		Carta aux = cartas.get(carta);
		cartas.remove(carta);
		return aux;
	}
	
	private int sumarValor(Carta cUno, Carta cDos) {
		int envido = 20;
		
		if(cUno.getNum() != 10 && cUno.getNum() != 11 && cUno.getNum() != 12) {
			envido = envido + cUno.getNum();
		}
		if(cDos.getNum() != 10 && cDos.getNum() != 11 && cDos.getNum() != 12) {
			envido = envido + cDos.getNum();
		}
		
		return envido;
	}
	
	public int calcularEnvido() {

		if(cartas.get(0).getPalo() == cartas.get(1).getPalo()) {
			return sumarValor(cartas.get(0), cartas.get(1));
		}else if(cartas.get(0).getPalo() == cartas.get(2).getPalo()) {
			return sumarValor(cartas.get(0), cartas.get(2));
		}else if(cartas.get(1).getPalo() == cartas.get(2).getPalo()) {
			return sumarValor(cartas.get(1), cartas.get(2));
		}else {
			ArrayList<Carta> aux= cartas; 
			for(int i = 0; i < cartas.size()-1; i++) {
				for(int j = 0; j < cartas.size()-1;j++) {
					if(aux.get(j).getNum() < aux.get(j+1).getNum()) {
						Carta menor = aux.get(j);
						aux.set(j, aux.get(j+1));
						aux.set(j+1, menor);
					}
				}
			}
			System.out.println(aux.get(0).getNum()+" - "+aux.get(1).getNum()+" - "+aux.get(2).getNum());
			if(aux.get(0).getNum() < 10) {
				return aux.get(0).getNum();
			}else if(aux.get(1).getNum() < 10) {
				return aux.get(1).getNum();
			}else if(aux.get(2).getNum() < 10) {
				return aux.get(2).getNum();
			}else {
				return 0;
			}
		}
	}
	
	public int calcularFlor() {
		if(hayFlor()) {
			return 20 + cartas.get(0).getNum() + cartas.get(1).getNum() + cartas.get(2).getNum();
		}else {
			return 0;
		}
	}
	

}
