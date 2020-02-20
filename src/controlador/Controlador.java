package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;

import modelo.Canto;
import modelo.CantoJuego;
import modelo.Carta;
import modelo.EstadoJuego;
import modelo.Juego;
import modelo.OperacionClienteRMI;
import vista.IVistas;

public class Controlador {
	
	private ArrayList<IVistas> vistas = new ArrayList<IVistas>();
	
	private OperacionClienteRMI cliente;
	
	public Controlador(OperacionClienteRMI cliente) {
		this.cliente=cliente;
		this.cliente.setControlador(this);
	}
	
	public void agregarVista(IVistas vista) {
		vistas.add(vista);
	}
	
	public void cambiarIP(String serverAddress, int serverPort)throws RemoteException {
		cliente.cambiarIP(serverAddress, serverPort);	
	}	
	
	public void nuevoJugador(String nombre) throws RemoteException {
		cliente.agregarJugador(nombre);
	}
	
	public EstadoJuego getEstadoJuego() throws RemoteException {
		return cliente.getEstadoJuego();
	}
	
	public String getNombreOponente(int indice) throws RemoteException{
		return cliente.getNombreOponente(indice);
	}

	public int getPuntos(int indice) throws RemoteException {
		return cliente.getPuntos(indice);
	}
	
	public String getTurno() throws RemoteException {
		return cliente.getTurno();
	}
	
	public ArrayList<Carta> getManoJugador(int indice) throws RemoteException{
		return cliente.getManoJugador(indice);
	}
	
	public String getMesa(int indice) throws RemoteException {
		return cliente.getMesa(indice);
	}
	
	public boolean hayCantoPendiente() throws RemoteException {
		return cliente.hayCantoPendiente();
	}
	
	public int getOponente(int indice) throws RemoteException {
		return cliente.getOponente(indice);
	}
	
	public Canto getCanto() throws RemoteException {
		return cliente.getCanto();
	}
	
	public  ArrayList<CantoJuego> cantosDisponibles() throws RemoteException{
		return cliente.cantosDisponibles();
	}
	
	public void aceptarCanto() throws RemoteException {
		cliente.aceptarCanto();
	}
	
	public void rechazarCanto(int indice) throws RemoteException {
		cliente.rechazarCanto(indice);
	}
	
	public void finMano(int indice) throws RemoteException {
		cliente.finMano(indice);
	}
	
	public void cantar(CantoJuego canto, int indice) throws RemoteException {
		cliente.addCanto(canto, indice);
	}
	
	public void tirarCarta(int carta, int indice) throws RemoteException {
		cliente.tirarCarta(carta, indice);
	}
	
	public int getPuntosCanto() throws RemoteException {
		return cliente.getPuntosCanto();
	}
	
	public int getCantidadJugadores() throws RemoteException{
		return cliente.getCantidadJugadores();
	}
	
	public void respuestaJugador(boolean respuesta, int indice) throws RemoteException {
		cliente.respuestaJugador(respuesta, indice);
	}
}
