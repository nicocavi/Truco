package modelo;

import java.util.ArrayList;

public class Mesa {
	private ArrayList<Carta> mesaJUno;
	private ArrayList<Carta> mesaJDos;
	private ArrayList<Integer> turnosGanados;
	
	public Mesa(){
		mesaJUno = new ArrayList<Carta>();
		mesaJDos = new ArrayList<Carta>();
		turnosGanados = new ArrayList<Integer>();
	}
	
	public String getMesa(int indice){
		if(indice == 0) {
			if(mesaJUno.size() != 0){
				return mesaJUno.get(mesaJUno.size()-1).getToString();	
			}else {
				return "VACIO";
			}
		}else {
			if(mesaJDos.size() != 0){
				return mesaJDos.get(mesaJDos.size()-1).getToString();	
			}else {
				return "VACIO";
			}
		}
		
	}
	
	public void setMesaJUno(Carta carta){
		mesaJUno.add(carta);
		evaluarMesa();
	}
	
	public void setMesaJDos(Carta carta){
		mesaJDos.add(carta);
		evaluarMesa();
	}
	
	public ArrayList<Integer> getTurnosGanados(){
		return turnosGanados;
	}
	
	private void evaluarMesa() {
		
		if(mesaJUno.size() == mesaJDos.size()) {
			
			if(mesaJUno.get(mesaJUno.size()-1).getValor() > mesaJDos.get(mesaJDos.size()-1).getValor()) {
				turnosGanados.add(0);
			}else if(mesaJUno.get(mesaJUno.size()-1).getValor() < mesaJDos.get(mesaJDos.size()-1).getValor()){
				turnosGanados.add(1);
			}else {
				turnosGanados.add(-1);
			}
			
		}
		
	}
	
}
