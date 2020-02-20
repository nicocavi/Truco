package modelo;

import java.io.Serializable;

public class Canto implements Serializable{
	private CantoJuego cantoJuego;
	private Jugador jugador;
	private boolean estado;
	
	public Canto() {
		cantoJuego = CantoJuego.VACIO;
		jugador = new Jugador();
		estado = false;
	}
	
	public Canto(CantoJuego cantoJuego, Jugador jugador) {
		this.cantoJuego = cantoJuego;
		this.jugador = jugador;
		estado = false;
	}
	
	public CantoJuego getCanto() {
		return cantoJuego;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void aceptarCanto() {
		estado = true;
	}
}
