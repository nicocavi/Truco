package modelo;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import java.util.ArrayList;

import vista.IOperaciones;

public class OperacionServidorRMI extends UnicastRemoteObject implements IOperaciones{
	
	private static final long serialVersionUID = 906186373895430621L;
	private final int PUERTO = 8976;
	private Juego juego; 
	
	public OperacionServidorRMI() throws RemoteException{
		iniciarServidor();
		juego = new Juego();
	}
	
	private void iniciarServidor() {
		try {
			String dirIP = (InetAddress.getLocalHost()).toString();
			System.out.println("Escuchando en.. " + dirIP + ":" + PUERTO);
			Registry registry = LocateRegistry.createRegistry(PUERTO);
			registry.bind("operacionservidor", (IOperaciones) this);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void nuevoJugador(String nombre) {
		juego.agregarJugador(nombre);
	}
	
	@Override
	public EstadoJuego getEstadoJuego() {
		return juego.getEstado();
	}
	
	@Override
	public String getNombreOponente(int indice) {
		return juego.getNombreOponente(indice);
	}
	
	@Override
	public int getCantidadJugadores() {
		return juego.getCantidadJugadores();
	}

	@Override
	public int getPuntos(int indice) {
		return juego.getPuntos(indice);
	}
	
	@Override
	public String getTurno() {
		return juego.getTurnoJugador();
	}
	
	@Override
	public ArrayList<Carta> getManoJugador(int indice){
		return juego.getManoJugador(indice);
	}
	
	@Override
	public String getMesa(int indice) {
		return juego.getMesa(indice);
	}
	
	@Override
	public boolean hayCantoPendiente() {
		return juego.hayCantoPendiente();
	}
	
	@Override
	public int getOponente(int indice) {
		return juego.getOponente(indice);
	}
	
	@Override
	public Canto getCanto() {
		return juego.getCanto();
	}
	
	@Override
	public  ArrayList<CantoJuego> cantosDisponibles(){
		return juego.cantosDisponibles();
	}
	
	@Override
	public void aceptarCanto() {
		juego.aceptarCanto();
	}
	
	@Override
	public void rechazarCanto(int indice) {
		juego.rechazarCanto(indice);
	}
	
	@Override
	public void finMano(int indice) {
		juego.finMano(indice);
	}
	
	@Override
	public void cantar(CantoJuego canto, int indice) {
		juego.addCanto(canto,indice);
	}
	
	@Override
	public void tirarCarta(int carta, int indice) {
		juego.tirarCarta(carta, indice);
	}
	
	@Override
	public int getPuntosCanto() {
		return juego.getPuntosCanto();
	}

	@Override
	public void addCanto(CantoJuego canto, int indice) {
		juego.addCanto(canto, indice);
	}

	@Override
	public void respuestaJugador(boolean respuesta, int indice) {
		juego.respuestaJugador(respuesta, indice);
		
	}

}
