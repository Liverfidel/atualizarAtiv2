package entidades;

public class Quarto {

	private int numQuarto;
	private String andar;
	private String tipo;
	private Hotel hotel;
	
	public Quarto(String andar, String tipo, Hotel hotel) {
		this.andar = andar;
		this.tipo = tipo;
		this.hotel = hotel;
	}
	
	public Quarto(int numQuarto, String andar, String tipo, Hotel hotel) {
		this.numQuarto = numQuarto;
		this.andar = andar;
		this.tipo = tipo;
		this.hotel = hotel;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	

	

}
