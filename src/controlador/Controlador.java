package controlador;

import java.util.ArrayList;

import modelo.Canto;
import modelo.CantoJuego;
import modelo.Carta;
import modelo.EstadoJuego;
import modelo.Juego;

public class Controlador {
	
	private Juego juego;
	
	public Controlador() {
		juego = new Juego();
	}
	
	public void nuevoJugador(String nombre) {
		juego.agregarJugador(nombre);
	}
	
	public EstadoJuego getEstadoJuego() {
		return juego.getEstado();
	}
	
	public String getNombreJUno() {
		return juego.getNombreJUno();
	}
	
	public String getNombreJDos() {
		return juego.getNombreJDos();
	}

	public int getPuntosJUno() {
		return juego.getPuntosJUno();
	}
	
	public int getPuntosJDos() {
		return juego.getPuntosJDos();
	}
	
	public String getTurno() {
		return juego.getTurnoJugador();
	}
	
	public ArrayList<Carta> getManoJugador(String nombre){
		return juego.getManoJugador(nombre);
	}
	
	public String getMesaJUno() {
		return juego.getMesaJUno();
	}
	
	public String getMesaJDos() {
		return juego.getMesaJDos();
	}
	
	public boolean hayCantoPendiente() {
		return juego.hayCantoPendiente();
	}
	
	public String getOponente() {
		return juego.getOponente();
	}
	
	public Canto getCanto() {
		return juego.getCanto();
	}
	
	public  ArrayList<CantoJuego> cantosDisponibles(){
		return juego.cantosDisponibles();
	}
	
	public void aceptarCanto() {
		juego.aceptarCanto();
	}
	
	public void rechazarCanto() {
		juego.rechazarCanto();
	}
	
	public void finMano() {
		juego.finMano();
	}
	
	public void cantar(CantoJuego canto) {
		juego.addCanto(canto);
	}
	
	public void tirarCarta(int carta) {
		juego.tirarCarta(carta);
	}
	
	public int getPuntosCanto() {
		return juego.getPuntosCanto();
	}
	
	public void respuestaJugador(boolean respuesta) {
		juego.respuestaJugador(respuesta);
	}
}
