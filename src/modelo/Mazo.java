package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
	private ArrayList<Carta> cartas;
	private int indice;
	
	public Mazo() {
		cartas = new ArrayList<Carta>();
		crearMazo();	
		indice = cartas.size()-1;
	}
	
	private void crearMazo() {
		for(int i = 1; i < 13; i++) {
			for(int j= 0; j < 4; j++) {
				if((i != 8)&&(i != 9)) {
					switch (i){
					case 4:
						cartas.add(new Carta(0, Palo.values()[j], i ));
						break;
					case 5:
						cartas.add(new Carta(1, Palo.values()[j], i ));
						break;
					case 6:
						cartas.add(new Carta(2, Palo.values()[j], i ));
						break;
					case 7:
						if(Palo.values()[j] == Palo.ORO) {
							cartas.add(new Carta(10, Palo.values()[j], i ));	
						}else if(Palo.values()[j] == Palo.ESPADA) {
							cartas.add(new Carta(11, Palo.values()[j], i ));
						}else {
							cartas.add(new Carta(3, Palo.values()[j], i ));
						}
						break;
					case 10:
						cartas.add(new Carta(4, Palo.values()[j], i ));
						break;
					case 11:
						cartas.add(new Carta(5, Palo.values()[j], i ));
						break;
					case 12:
						cartas.add(new Carta(6, Palo.values()[j], i ));
						break;
					case 1:
						if(Palo.values()[j] == Palo.BASTO) {
							cartas.add(new Carta(12, Palo.values()[j], i ));	
						}else if(Palo.values()[j] == Palo.ESPADA) {
							cartas.add(new Carta(13, Palo.values()[j], i ));
						}else {
							cartas.add(new Carta(7, Palo.values()[j], i ));
						}
						break;
					case 2:
						cartas.add(new Carta(8, Palo.values()[j], i ));
						break;
					case 3:
						cartas.add(new Carta(9, Palo.values()[j], i ));
						break;
					}
			
				}
				
			}
		}
		
		Collections.shuffle(cartas);
	}
	
	public Carta repartirCarta() {
		return cartas.get(--indice);
	}
	
}

