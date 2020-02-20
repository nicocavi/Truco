package test;

import controlador.Controlador;

import java.rmi.RemoteException;

import modelo.OperacionClienteRMI;
import vista.VistaConsola;

public class TestConsolaPlayer1 {

	public static void main(String[] args) throws RemoteException {
	
		OperacionClienteRMI cliente = new OperacionClienteRMI();
		Controlador controlador = new Controlador(cliente);
		new VistaConsola(controlador);
	}
}
