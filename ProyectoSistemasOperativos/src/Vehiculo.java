public class Vehiculo extends Thread {
    
	private String matricula;   // Identificador del Vehiculo
	private String tipo;        // Auto, camion, ambulancia, bus, etc.
	private String marca;       // Marca del Vehiculo
	private String modelo;      // Modelo del Vehiculo
	private String color;       // Color del  Vehiculo


	public Vehiculo(String matricula, String tipo, String modelo, String color) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.modelo = modelo;
		this.color = color;

	}

	public String getMatricula(){
		return matricula;
	}

	public String getTipoVehiculo() {
		return tipo;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getColor() {
		return color;
	}



}
