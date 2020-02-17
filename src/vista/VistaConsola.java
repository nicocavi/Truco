package vista;

import java.util.ArrayList;
import java.util.Scanner;

import controlador.Controlador;
import modelo.CantoJuego;
import modelo.Carta;
import modelo.EstadoJuego;

public class VistaConsola {
	
	private Controlador controlador;
	private String nombre = "";
	Scanner sc = new Scanner(System.in);
	String version;
	int opcion;
	
	public VistaConsola() {
		controlador = new Controlador();
		version = "sadas";
	}

	public void iniciar() {
		
		
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
					controlador.nuevoJugador("CPU");
					while (controlador.getEstadoJuego() == EstadoJuego.SETEANDO) {
						System.out.println("Esperando jugadores...");
					}
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
	
	private void opciones() {
		
		while(controlador.getTurno() != nombre) {}
		ArrayList<CantoJuego> cantos = controlador.cantosDisponibles();
		if(controlador.hayCantoPendiente()) {
			System.out.println(controlador.getCanto());
			opcion = -1;
			while(opcion < 0 || opcion > cantos.size()+2) {
				System.out.println("----------------------------------------------");
				System.out.println(controlador.getOponente()+" dice "+controlador.getCanto()+"!!!");
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
				}else if(opcion == cantos.size()+1){
					//Acepta el canto realizado por el rival
					controlador.aceptarCanto();
					
				}else if(opcion == cantos.size()+2) {
					//Rechaza el canto realizado por el rival
					controlador.rechazarCanto();
					
				}else if(opcion == 0){
					//El Jugador se retira de la mano actual
					System.out.println("ENTRO A FIN MANO");
					controlador.finMano();
					
				}else {
					System.out.println("EL JUGADOR CANTO!!!------------------------------ OPCION: "+opcion);
					//El Jugador realiza un nuevo canto
					controlador.cantar(cantos.get(opcion));
				}
			}

		}else {
			System.out.println("NO HAY CANTO PENDIENTE");
			opcion = -1;
			ArrayList<Carta> mano = controlador.getManoJugador(nombre);
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
				controlador.finMano();
			}else if(opcion > 0 && opcion <= mano.size()) {
				//El Jugador tira una carta
				controlador.tirarCarta(opcion-1);
			}else {
				//El Jugador realiza un nuevo canto
				controlador.cantar(cantos.get(opcion - mano.size()-1));
			}
			
		}
		
		
	}
	
	private void mostrarTablero(){
		ArrayList<Carta> mano = controlador.getManoJugador(nombre);
	
		System.out.println("----------------------------------------------");
		System.out.println("TURNO DE: "+controlador.getTurno());
		System.out.println("----------------------------------------------");
		System.out.println("PUNTOS: ");
		System.out.println(controlador.getNombreJUno()+": "+controlador.getPuntosJUno());
		System.out.println(controlador.getNombreJDos()+": "+controlador.getPuntosJDos());
		System.out.println("----------------------------------------------");
		System.out.println("MESA:");
		System.out.println(controlador.getNombreJUno()+": "+controlador.getMesaJUno());
		System.out.println(controlador.getNombreJDos()+": "+controlador.getMesaJDos());
		
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
	

}
