package Peaje;

import java.util.Queue;
import RapiPago.*;

public class Vehiculo extends Thread {
    
	private String matricula;   		// Identificador del Vehiculo
	private int tipo;        			// Auto, camion, ambulancia, bus, etc.
	private String marca;       		// Marca del Vehiculo
	private String modelo;      		// Modelo del Vehiculo
	private String color;       		// Color del  Vehiculo
	private Cliente clientePago;		// Cliente de RapiPago (sticker en el parabrisas).
	private boolean haciaMontevideo; 	// verificar a que sentido va el auto
	private Cabina cabina;				// Cabina que le fue asignada al vehículo.
	private boolean cobrado; 			// Indica si el auto ya paso por el Peaje.

	public Vehiculo(String matricula, int tipo, String modelo, String color,boolean haciaMontevideo) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.modelo = modelo;
		this.color = color;
		this.haciaMontevideo = haciaMontevideo;
		this.cabina = null;
		this.cobrado = false;
		this.clientePago = new Cliente(99123789,500,matricula,56709086);
	}

	public boolean getCobrado(){
		return this.cobrado;
	}
	public void setCobrado(boolean c){
		this.cobrado = c;
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

	/**
	 * 
	 * @return la cabina a la cual está asignado
	 */
	public Cabina getCabina()  {
		return this.cabina;
	}

	/**
	 * Cuando se decida a que cabina ira el vehiculo,
	 * se llamara a este metodo
	 * @param cabina a ser asignada
	 */
	public void setCabina(Cabina cabina){
		this.cabina = cabina;
	}

	/**
	 * Método encargado de asignar un RapiPago al vehículo.
	 * @param clientePago a ser asignada
	 */
	public void setCliente(Cliente clientePago){
		this.clientePago = clientePago;
	}
	
	public boolean entrarEnCabina(){
		if(this.cabina != null){
			if(this.cabina.meterEnCabina(this)){
				// Vehiculo esta actualmente en la Cabina
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}



	//Agregar listado de carriles, desarrollar lógica de a qué carril ingresar.
	@Override
	public void run(){
			while (this.cabina == null) {
				//try {
					//wait();
				//} catch (InterruptedException e) {
				//}
			}
			this.cabina.getCabinaOcupada().decrementar();

			if (entrarEnCabina()){
				this.cabina.getEsperaDeAutos().poll(); // El Vehiculo sale de la "cola de espera".
				System.out.println("El vehiculo matricula " + this.matricula + " se encuentra en la cabina");
			}else{
				// Error: tiene el permiso pero la cabina sigue ocupada
			}
			while(this.cabina != null){
				// Espero a que Cabina me desasigne mi Cabina
			}
			System.out.println("El Vehiculo " + this.getMatricula() + " sigue su camino");
		
	}
}
