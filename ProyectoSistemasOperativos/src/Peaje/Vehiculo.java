package Peaje;

import java.util.Queue;
import RapiPago.*;

public class Vehiculo extends Thread {
    
	private String matricula;   	// Identificador del Vehiculo
	private int tipo;        		// Auto, camion, ambulancia, bus, etc.
	private String marca;       	// Marca del Vehiculo
	private String modelo;      	// Modelo del Vehiculo
	private String color;       	// Color del  Vehiculo
	private Cliente clientePago;	// Cliente de RapiPago (sticker en el parabrisas).
	private boolean haciaMontevideo; // verificar a que sentido va el auto
	private Cabina cabina;

	public Vehiculo(String matricula, int tipo, String modelo, String color,boolean haciaMontevideo,Cabina cabina) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.modelo = modelo;
		this.color = color;
		this.haciaMontevideo = haciaMontevideo;
		this.cabina = cabina;

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

	public Cliente getInformacionPago() {
		return this.clientePago;
	}
	/**
	 * 
	 * @return hacia donde se dirige el vehiculo
	 */
	public boolean getSentido()  {
		return this.haciaMontevideo;
	}

/*
	public Carril seleccionarCarril() {
		 	
		//solo queremos el carril optimo( queue mas corta para q el auto se una)
	}
*/
	//Agregar listado de carriles, desarrollar lógica de a qué carril ingresar.
	@Override
	public void run(){
		
		for (;;) {
			this.cabina.getCabinaOcupada().decrementar();
			System.out.println("El vehiculo matricula " + this.matricula + "se encuentra en la cabina");
			this.cabina.esperaDeAutos.add(this);

		}
	}
}
