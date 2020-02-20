package vista;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import modelo.Canto;
import modelo.CantoJuego;
import modelo.Carta;
import modelo.EstadoJuego;

public interface IOperaciones extends Remote {

	
	public void nuevoJugador(String nombre) throws RemoteException;
	
	public EstadoJuego getEstadoJuego() throws RemoteException;
	
	public String getNombreOponente(int indice) throws RemoteException;
	
	public int getPuntos(int indice) throws RemoteException;
	
	public String getTurno() throws RemoteException;
	
	public ArrayList<Carta> getManoJugador(int indice) throws RemoteException;
	
	public String getMesa(int indice) throws RemoteException;
	
	public int getOponente(int indice) throws RemoteException;
	
	public boolean hayCantoPendiente()throws RemoteException;
	
	public Canto getCanto() throws RemoteException;
	
	public  ArrayList<CantoJuego> cantosDisponibles()throws RemoteException;
	
	public void aceptarCanto() throws RemoteException;
	
	public void rechazarCanto(int indice)throws RemoteException;
	
	public void finMano(int indice) throws RemoteException;
	
	public int getCantidadJugadores() throws RemoteException;
	
	public void cantar(CantoJuego canto, int indice) throws RemoteException;
	
	public void tirarCarta(int carta, int indice) throws RemoteException;
	 
	public int getPuntosCanto() throws RemoteException;

	public void addCanto(CantoJuego canto, int indice)throws RemoteException;

	public void respuestaJugador(boolean respuesta, int indice)throws RemoteException;
	
}
