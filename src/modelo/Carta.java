package modelo;

public class Carta {
	private int valor;
	private Palo palo;
	private int num;
	
	public Carta(int valor, Palo palo, int num) {
		this.valor = valor;
		this.palo = palo;
		this.num = num;
	}
	
	public int getValor() {
		return valor;
	}

	public Palo getPalo() {
		return palo;
	}

	public int getNum() {
		return num;
	}
	
	public String getToString() {
		return num+" de "+palo;
	}
	
}

