package test;

import java.rmi.RemoteException;

import modelo.OperacionClienteRMI;
import vista.VistaConsola;
import controlador.Controlador;

public class TestConsolaPlayer2 {
	public static void main(String[] args) throws RemoteException {
		
		OperacionClienteRMI cliente = new OperacionClienteRMI();
		Controlador controlador = new Controlador(cliente);
		new VistaConsola(controlador);
	}
}
