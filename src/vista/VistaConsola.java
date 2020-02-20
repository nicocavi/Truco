package vista;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import controlador.Controlador;
import modelo.CantoJuego;
import modelo.Carta;
import modelo.EstadoJuego;

public class VistaConsola implements IVistas {
	
	private Controlador controlador;
	private String nombre = "";
	private int numJugador;
	private int numOponente;
	Scanner sc = new Scanner(System.in);
	String version;
	int opcion;
	
	public VistaConsola(Controlador controlador) throws RemoteException {
		this.controlador=controlador;
		this.controlador.agregarVista(this);
		menuInicio();
	}
	
	private void menuInicio()throws RemoteException {
		System.out.println("");
		System.out.println("Bienvenido a TRUCO");
		System.out.println("");
		System.out.println("-----------------------------------------");
		System.out.print  ("Por favor, ingrese la IP del servidor: "			 );
		sc=new Scanner(System.in);
		String ipServidor=sc.nextLine();
		System.out.println("									  	");
		System.out.print  ("Por favor, ingrese el puerto del servidor: "			 );
		sc=new Scanner(System.in);
		String puerto=sc.nextLine();
		controlador.cambiarIP(ipServidor, Integer.parseInt(puerto));
		System.out.println("									  	");
		System.out.println("Conectando al servidor..."				 );
		System.out.println("-----------------------------------------");
		iniciar();
	}

	public void iniciar() throws RemoteException {
		
		
		menuPrincipal();
		opcion = sc.nextInt();
		while (opcion != 0) {
			
			if(opcion == 1) {
				while (opcion != 0) {
					System.out.println("1) Cambiar nombre = Nombre: "+nombre);
					System.out.println("0) Salir");
					opcion = sc.nextInt();
					if(opcion == 1) {
						System.out.println("INGRESE EL NUEVO NOMBRE: ");
						sc.nextLine();
						nombre = sc.nextLine();
					}else if(opcion != 0) {
						System.out.println("La opcion ingresada es incorrecta...");
					}
				}
			}if(opcion == 2) {
				if(nombre == "") {
					System.out.println("NECESITAS UN NOMBRE PARA PODER JUGAR!");
				}else {
					controlador.nuevoJugador(nombre);
					numJugador = controlador.getCantidadJugadores()-1;
					System.out.println("Esperando jugadores...");
					while (controlador.getEstadoJuego() == EstadoJuego.SETEANDO) {}
					numOponente = controlador.getOponente(numJugador);
					while (controlador.getEstadoJuego() == EstadoJuego.JUGANDO) {
					
							mostrarTablero();
							opciones();
						
						
					}
				}
			}
			
			menuPrincipal();
			opcion = sc.nextInt();
	
		}
		
	}
	
	private void opciones() throws RemoteException {
		while(!controlador.getTurno().equals(nombre)) {}
		espaciosEnBlanco();
		mostrarTablero();
		ArrayList<CantoJuego> cantos = controlador.cantosDisponibles();
		if(controlador.hayCantoPendiente()) {
			//Si hay un canto pendiente
			
			if(controlador.getCanto().getEstado() && controlador.getCanto().getCanto() != CantoJuego.TRUCO && controlador.getCanto().getCanto() != CantoJuego.RETRUCO && controlador.getCanto().getCanto() != CantoJuego.VALECUATRO ){
				//Si se acepto envido o flor (o variantes)
				opcion = -1;
				System.out.println("Elija una opcion: ");
				while(opcion < 0 || opcion > 1) {
					System.out.println("Tus puntos para "+ controlador.getCanto().getCanto().toString()+ " son: "+controlador.getPuntosCanto());
					System.out.println("-----------------------------------------------------------");
					System.out.println("0) No quiero");
					System.out.println("1) Cantar");
					opcion = sc.nextInt();
				}
				if(opcion == 0) {
					System.out.println("NO QUIERO!");
					controlador.respuestaJugador(false, numJugador);
				}else if(opcion == 1) {
					System.out.println(controlador.getPuntosCanto());
					controlador.respuestaJugador(true, numJugador);
				}
				
			}else if(!controlador.getCanto().getEstado()){
				//Si hay canto pendiente que no fue aceptado
				opcion = -1;
				while(opcion < 0 || opcion > cantos.size()+2) {
					System.out.println("----------------------------------------------");
					System.out.println(controlador.getNombreOponente(numOponente)+" dice "+controlador.getCanto().getCanto().toString()+"!!!");
					System.out.println("Elija una opcion:");	
					for(int i = 1; i <= cantos.size(); i++) {
						System.out.println(i+") "+cantos.get(i-1).toString());
					}
					System.out.println(cantos.size()+1+") Acepto");
					System.out.println(cantos.size()+2+") No quiero");
					System.out.println("0) Me retiro");
					opcion = sc.nextInt();
					
					if(opcion < 0 || opcion > cantos.size()+2) {
						System.out.println("ERROR, OPCION NO VALIDA!!!");
					}
				} 
				if(opcion == cantos.size()+1){
					//Acepta el canto realizado por el rival
					controlador.aceptarCanto();
				}else if(opcion == cantos.size()+2) {
					//Rechaza el canto realizado por el rival
					controlador.rechazarCanto(numJugador);
				}else if(opcion == 0){
					//El Jugador se retira de la mano actual
					System.out.println("ENTRO A FIN MANO");
					controlador.finMano(numJugador);
				}else {
					//El Jugador realiza un nuevo canto
					controlador.cantar(cantos.get(opcion - 1),numJugador);
				}
				
			}else if(controlador.getCanto().getCanto() == CantoJuego.TRUCO || controlador.getCanto().getCanto() == CantoJuego.RETRUCO || controlador.getCanto().getCanto() == CantoJuego.VALECUATRO){
				opcion = -1;
				ArrayList<Carta> mano = controlador.getManoJugador(numJugador);
				while(opcion < 0 || opcion > mano.size()+cantos.size()) {
					System.out.println("----------------------------------------------");
					System.out.println("Elija una opcion:");
					for(int i = 1; i <= cantos.size(); i++) {
						System.out.println(i+mano.size()+") "+cantos.get(i-1).toString());
					}
					System.out.println("0) Me retiro");
					opcion = sc.nextInt();
					if(opcion < 0 || opcion > mano.size()+cantos.size()){
						System.out.println("ERROR, OPCION NO VALIDA!!!");
					}
				}
				if(opcion == 0){
					//El Jugador se retira de la mano actual
					controlador.finMano(numJugador);
				}else if(opcion > 0 && opcion <= mano.size()) {
					//El Jugador tira una carta
					controlador.tirarCarta(opcion-1,numJugador);
				}else {
					//El Jugador realiza un nuevo canto
					controlador.cantar(cantos.get(opcion - mano.size() - 1),numJugador);
				}
			}
		}else {
			opcion = -1;
			ArrayList<Carta> mano = controlador.getManoJugador(numJugador);
			while(opcion < 0 || opcion > mano.size()+cantos.size()) {
				System.out.println("----------------------------------------------");
				System.out.println("Elija una opcion:");
				for(int i = 1; i <= cantos.size(); i++) {
					System.out.println(i+mano.size()+") "+cantos.get(i-1).toString());
				}
				System.out.println("0) Me retiro");
				opcion = sc.nextInt();
				if(opcion < 0 || opcion > mano.size()+cantos.size()){
					System.out.println("ERROR, OPCION NO VALIDA!!!");
				}
			}
			if(opcion == 0){
				//El Jugador se retira de la mano actual
				controlador.finMano(numJugador);
			}else if(opcion > 0 && opcion <= mano.size()) {
				//El Jugador tira una carta
				controlador.tirarCarta(opcion-1,numJugador);
			}else {
				//El Jugador realiza un nuevo canto
				controlador.cantar(cantos.get(opcion - mano.size() - 1),numJugador);
			}
		}
		
	}
	
	private void mostrarTablero() throws RemoteException{
		ArrayList<Carta> mano = controlador.getManoJugador(numJugador);
	
		System.out.println("----------------------------------------------");
		System.out.println("TURNO DE: "+controlador.getTurno());
		System.out.println("----------------------------------------------");
		System.out.println("PUNTOS: ");
		System.out.println(nombre+": "+controlador.getPuntos(numJugador));
		System.out.println(controlador.getNombreOponente(numOponente)+": "+controlador.getPuntos(numOponente));
		System.out.println("----------------------------------------------");
		System.out.println("MESA:");
		System.out.println(nombre+": "+controlador.getMesa(numJugador));
		System.out.println(controlador.getNombreOponente(numOponente)+": "+controlador.getMesa(numOponente));
		
		System.out.println("----------------------------------------------");
		System.out.println("Tus cartas son:");
		
		for(int i = 1; i <= mano.size(); i++) {
			System.out.println("| "+i+") "+mano.get(i-1).getToString()+" |");
		}
	}
	
	private void menuPrincipal() {
		System.out.println("Opciones: ");
		System.out.println("1) Configuracion");
		System.out.println("2) Jugar");
		System.out.println("0) Salir");
	}
	

	private void espaciosEnBlanco() {
		for(int i = 0; i < 40; i++) {
			System.out.println("");
		}
	}

}
