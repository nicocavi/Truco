package modelo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import vista.IOperaciones;
import controlador.Controlador;

public class OperacionClienteRMI {

	private String serverAddress = "localhost";
	private int serverPort = 8976;
	private IOperaciones servidorObj;
	private Controlador controlador;
	
	public OperacionClienteRMI() {
		iniciarCliente();
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void cambiarIP(String serverAddress, int serverPort) {
		this.serverAddress=serverAddress;
		this.serverPort= serverPort;
	} 
	
	private void iniciarCliente(){
		try {
			Registry registry = LocateRegistry.getRegistry(serverAddress, serverPort);
			servidorObj = (IOperaciones) (registry.lookup("operacionservidor"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////////////////
	public void agregarJugador(String nombre)throws RemoteException{
		servidorObj.nuevoJugador(nombre);
	}
	
	public EstadoJuego getEstadoJuego() throws RemoteException{
		return servidorObj.getEstadoJuego();
	};
	
	public String getNombreOponente(int indice) throws RemoteException{
		return servidorObj.getNombreOponente(indice);
	};
	
	public int getPuntos(int indice) throws RemoteException{
		return servidorObj.getPuntos(indice);
	}
	
	public String getTurno() throws RemoteException{
		return servidorObj.getTurno();
	}
	
	public ArrayList<Carta> getManoJugador(int indice) throws RemoteException{
		return servidorObj.getManoJugador(indice);
	}
	
	public String getMesa(int indice) throws RemoteException{
		return servidorObj.getMesa(indice);
	}
	
	public boolean hayCantoPendiente()throws RemoteException{
		return servidorObj.hayCantoPendiente();
	}
	
	public int getOponente(int indice) throws RemoteException{
		return servidorObj.getOponente(indice);
	}
	
	public Canto getCanto() throws RemoteException{
		return servidorObj.getCanto();
	}
	
	public  ArrayList<CantoJuego> cantosDisponibles()throws RemoteException{
		return servidorObj.cantosDisponibles();
	}
	
	public void aceptarCanto() throws RemoteException{
		servidorObj.aceptarCanto();
	}
	
	public void rechazarCanto(int indice)throws RemoteException{
		servidorObj.rechazarCanto(indice);
	}
	
	public void finMano(int indice) throws RemoteException{
		servidorObj.finMano(indice);
	}
	
	public void cantar(CantoJuego canto, int indice) throws RemoteException{
		servidorObj.cantar(canto, indice);
	}
	
	public void tirarCarta(int carta, int indice) throws RemoteException{
		servidorObj.tirarCarta(carta, indice);
	}
	
	public int getPuntosCanto() throws RemoteException{
		return servidorObj.getPuntosCanto();
	}
	
	public int getCantidadJugadores() throws RemoteException {
		return servidorObj.getCantidadJugadores();
	}
	
	public void addCanto(CantoJuego canto, int indice) throws RemoteException{
		servidorObj.addCanto(canto, indice);
		
	}

	public void respuestaJugador(boolean respuesta,int indice) throws RemoteException {
		servidorObj.respuestaJugador(respuesta, indice);
	}

	
}