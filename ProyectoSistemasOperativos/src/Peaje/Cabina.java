package Peaje;

import java.util.LinkedList;
import java.util.Queue;
import RapiPago.*;
import java.util.Vector;


public class Cabina extends Thread {

	
	private boolean haciaMontevideo; // Determina el sentido del trafico en el carril/cabina.
	private Vehiculo enCabina;		 // Vehiculo que se encuentra en el Punto de Control
	private boolean habilitada;		
	private Cobrador cobrador;
	private int id;
	private Carril carril;
	private boolean suspendida; // verifica que el auto se puede poner en la cabina( si no esta cerrada para cambio de sentido)
	private Semaforo puedePasar;
	private Logger logger = new Logger(true,false,false);

	public Cabina(boolean haciaMontevideo, boolean habilitada, int id){
		this.haciaMontevideo = haciaMontevideo;
		this.enCabina = null;
		this.habilitada = habilitada;
		this.cobrador = new Cobrador();
		this.id = id;
		this.puedePasar = new Semaforo();
		this.cobrador.empezarLogger();
		logger.start();

	}

	public int getID(){
		return this.id;
	}

	public boolean getHabilitada(){
		return this.habilitada;
	}

	public boolean getSentido(){
		return this.haciaMontevideo;
	}
	
	public Vehiculo getEnCabina(){
		return this.enCabina;
	}
	
	public void setEnCabina(Vehiculo vehiculo){
		this.enCabina = vehiculo;
	}
	public void setHabilitada(boolean habilitada){
		this.habilitada = habilitada;
	}
	public void setCarril(Carril carril) {
		this.carril = carril;
	}
	public void setSentido(boolean sentido){
		this.haciaMontevideo = sentido;
	}
	public void setCabinaHabilitada(boolean habilitada){
		this.habilitada = habilitada;
	}

	public Carril getCarril(){
		 return this.carril;
	}

	@Override
	public void run() {
            while (habilitada){
				try {
					if (!carril.getEsperaDeAutos().isEmpty()) {
						
						if (this.enCabina == null) {
							this.setEnCabina(carril.getEsperaDeAutos().firstElement());
						}										
						if (this.enCabina != null) {						
							boolean pagoExitoso = false;
							switch (this.enCabina.getTipoVehiculo()) {

								case 1: //Prioritario
									pagoExitoso = this.cobrar(this.enCabina, 0);
									Thread.sleep(1000);
									break;

								case 2: // Automovil
									pagoExitoso = this.cobrar(this.enCabina, 115);
									Thread.sleep(5000);
									break;

								case 3: // Furgon
									pagoExitoso = this.cobrar(this.enCabina, 130);
									Thread.sleep(5500);
									break;

								case 4: // Bus
									pagoExitoso = this.cobrar(this.enCabina, 150);
									Thread.sleep(10000);
									break;

								case 5: //Camión
									pagoExitoso = this.cobrar(this.enCabina, 180);
									Thread.sleep(15000);
									break;
								default:
									System.out.println("UFO");
							}
														
							if(!pagoExitoso) {
								this.cobrador.multa(enCabina,500);
								this.logger.logCabina(this.id, "El vehiculo matricula " + enCabina.getMatricula()
								+ " fue multado por falta de saldo.");
								
							} else {
								System.out.println(
										"El pago de la matricula " + enCabina.getMatricula() + " se realizó con éxito");

							}
							if (this.haciaMontevideo) {
								this.logger.logCabina(this.id, "El vehiculo matricula " + enCabina.getMatricula()
								+ " se dirige hacia el Oeste");
								
							} else {
								this.logger.logCabina(this.id,"El vehiculo matricula " + enCabina.getMatricula()
								+ " se dirige hacia el Este");
								
							}

							//this.imprimirCola();
							this.enCabina.setCobrado(true);
							this.carril.getSemListaEspera().decrementar();
							this.carril.getSemListaEspera().incrementa();
							this.carril.getEsperaDeAutos().remove(enCabina);
                                                        this.setEnCabina(null);
						}
					}
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	
	
	private boolean cobrar(Vehiculo vehiculo, int tarifa){
		boolean resultado = false;
		if (enCabina != null && vehiculo.getMatricula().equals(enCabina.getMatricula())){			
			resultado = this.cobrador.cobrarACliente(vehiculo,tarifa);
		}
		if (resultado) {
			this.logger.logCabina(this.id,"El cobro se ha realizado con exito del vehiculo matricula:"+ vehiculo.getMatricula());			
		}else{
			this.logger.logCabina(this.id,"El Vehiculo matricula " + vehiculo.getMatricula() + " no pago");			
		}
		return resultado;
	}


}

