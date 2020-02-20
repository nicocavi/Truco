package test;

import java.rmi.RemoteException;

import modelo.OperacionServidorRMI;

public class TestServer {

	public static void main(String[] args) throws RemoteException {
		new OperacionServidorRMI();
	}

}
