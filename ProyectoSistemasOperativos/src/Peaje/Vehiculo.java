package Peaje;

import RapiPago.*;

public class Vehiculo extends Thread {
    
	private String matricula;   // Identificador del Vehiculo
	private int tipo;        // Auto, camion, ambulancia, bus, etc.
	private String marca;       // Marca del Vehiculo
	private String modelo;      // Modelo del Vehiculo
	private String color;       // Color del  Vehiculo
	private Cliente clientePago;// Cliente de RapiPago (sticker en el parabrisas).


	public Vehiculo(String matricula, int tipo, String modelo, String color) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.modelo = modelo;
		this.color = color;

	}

	public String getMatricula(){
		return matricula;
	}

	public int getTipoVehiculo() {
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

	public Cliente getInformacionPago(){
		return this.clientePago;
	}



}
