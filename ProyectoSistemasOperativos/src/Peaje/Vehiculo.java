package Peaje;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Queue;
import RapiPago.*;

public class Vehiculo extends Thread {

	private String matricula; // Identificador del Vehiculo
	private int tipo; // Auto, camion, bus. Cuando tipo = 1 son vehículos prioritarios (emergencias)
	private String marca; // Marca del Vehiculo
	private String modelo; // Modelo del Vehiculo
	private String color; // Color del  Vehiculo
	private Cliente clientePago; // Cliente de RapiPago (sticker en el parabrisas).
	private boolean haciaMontevideo; // verificar a que sentido va el auto
	private Cabina cabina; // Cabina que le fue asignada al vehículo.
	private boolean cobrado; // Indica si el auto ya paso por el Peaje.
	private Peaje peaje;

	public Vehiculo(String matricula, int tipo, String modelo, String color, boolean haciaMontevideo, Peaje peaje,int numeroTelefono,int saldo,int ci){
		this.matricula = matricula;
		this.tipo = tipo;
		this.modelo = modelo;
		this.color = color;
		this.haciaMontevideo = haciaMontevideo;
		this.cabina = null;
		this.cobrado = false;
		this.clientePago = new Cliente(numeroTelefono, saldo, matricula, ci);
		this.peaje = peaje;
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
	public boolean getSentido() {
		return this.haciaMontevideo;
	}

	/**
	 * 
	 * @return la cabina a la cual está asignado
	 */
	public Cabina getCabina() {
		return this.cabina;
	}

	/**
	 * Cuando se decida a que cabina ira el vehiculo,
	 * se llamara a este metodo
	 * @param cabina a ser asignada
	 */
	public void setCabina(Cabina cabina) {
		this.cabina = cabina;
	}

	/**
	 * Método encargado de asignar un RapiPago al vehículo.
	 * @param clientePago a ser asignada
	 */
	public void setCliente(Cliente clientePago) {
		this.clientePago = clientePago;
	}

	/**
	 * El Conductor mirara en el Monitor antes de llegar al Peaje y
	 * determinara cual es el Carril ideal al cual dirigirse.
	 * 
	 * Se determinara cual es este Carril Optimo segun la cantidad de Vehiculos
	 * en los Carriles disponibles.
	 */
	public Carril seleccionarCarrilHaciaMontevideo(){

		Carril carrilOptimo = null;
		Monitor monitor = this.peaje.getMonitorEste();
		
		if(this.getTipoVehiculo() == 5 || this.getTipoVehiculo() == 4){
			for(Carril c : monitor.getCarriles()){
				if(c.getNumeroCarril() == 1){
					carrilOptimo = c;
				}
			}
		}else{					
			for (Carril c : monitor.getCarriles()) {
				if (c.getHabilitado() && c.getCabina().getSentido() == this.haciaMontevideo
						&& c.getCabina().getHabilitada()) {
					if (carrilOptimo == null) {
						carrilOptimo = c;
					} else {
						if (c.getEsperaDeAutos().size() <= carrilOptimo.getEsperaDeAutos().size()) {
							carrilOptimo = c;
						}
					}
				}

			}	
		}	
		return carrilOptimo;
	} 
		
	public Carril seleccionarCarrilHaciaEste(){	
		Carril carrilOptimo = null;
		Monitor monitor = this.peaje.getMonitorOeste();
		if (this.getTipoVehiculo() == 5 || this.getTipoVehiculo() == 4 ) { // un bondi y camion
			for (Carril c : monitor.getCarriles()) {
				if(c.getNumeroCarril() == 10){
					carrilOptimo = c;
				}
			}
		}
		else {
			for (Carril c : monitor.getCarriles()) {
				if (c.getCabina() == null) {
					System.out.println("EL CARRIL NO TIENE CABINA");
				}
				if (c.getHabilitado() && c.getCabina().getSentido() == false && c.getCabina().getHabilitada()) {
					if (carrilOptimo == null) {
						carrilOptimo = c;
					} else {
						if (c.getEsperaDeAutos().size() <= carrilOptimo.getEsperaDeAutos().size()) {
							carrilOptimo = c;
						}
					}
				}
			}
		}
			return carrilOptimo;
		}
		
		
	/**
	 *  
	 */
	public void moverseDeCarril(Carril carrilOptimo){
		if(carrilOptimo == null){
			System.out.println("CARRIL OPTIMO ES NULO");
		}
		carrilOptimo.getDisponible().decrementar();
		///carrilOptimo.getSemListaEspera().decrementar();
		if(carrilOptimo.entrarAlCarril(this)){
			System.out.println("Vehiculo " + this.getId() + " entra al Carril " + carrilOptimo.getNumeroCarril());
			// Logear en Vehiculo y Carril con clase Logger.
		}
		//carrilOptimo.getSemListaEspera().incrementa();
		carrilOptimo.getDisponible().incrementa();
		
	}

	/*
	* Método que nos indica si se trata de un vehículo prioritario.
	*/
	public boolean es_prioritario(){
		if (this.tipo == 1){
			return true;
		} else{
			return false; 
		}

	}
		

	@Override
	public void run(){

		System.out.println("Vehiculo "+this.matricula+" se acerca al peaje");
		//Pasa por el sensor que cuenta la cantidad de vehiculos circulando
		if (this.getSentido()) {
			peaje.haciaMontevideo.vehiculoDetectado();
			System.out.println("pasa por el sensor hacia montevideo");
		}
		else {
			peaje.haciaElEste.vehiculoDetectado();
			System.out.println("pasa por el sensor hacie el este ");
		}
		//Tiempo que demora en llegar a la altura del monitor
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}	
		
		//if (this.es_prioritario()) {
		//	carrilElegido = peaje.vehiculoPrioritarioSeAcerca(this) // este es el carril a donde va a ir el vehiculo priori.
		//}
        // ambulancia toca sensor a 5km
        // peaje libera un carril
        // ambulancia viaja al peaje y demora un rato
        // el carril se desagota y la ambulancia pasa
		// falta lógica de cuando pasa por sensor y es prioritario
		
		Carril carril = null;
		if(haciaMontevideo){
			carril = this.seleccionarCarrilHaciaMontevideo();
		}else{
			carril = this.seleccionarCarrilHaciaEste();
		}
		if(carril != null)  {
			this.moverseDeCarril(carril);
		}
		// el auto ya se movio al carril que queria.


	}
}
