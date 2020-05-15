package Peaje;
import RapiPago.*;


public class Cabina extends Thread {

	private Carril carril;			 // Carril de entrada al Punto de Control "Cabina"
	private boolean haciaMontevideo; // Determina el sentido del trafico en el carril/cabina.
	private Vehiculo enCabina;		 // Vehiculo que se encuentra en el Punto de Control
	private boolean cabinaOcupada;  // Si el Punto De Control/Cabina se encuentra ocupada por un vehiculo.
	private boolean habilitada;
	private Cobrador cobrador;

	public Cabina(Carril carril, boolean haciaMontevideo, boolean habilitada) {
		this.haciaMontevideo = haciaMontevideo;
		this.enCabina = null;
		this.cabinaOcupada = false;
		this.habilitada = habilitada;
		this.cobrador = new Cobrador(this);
	}

	@Override
	public void run() {
		while (habilitada) {
			try {
				if (this.dejarEntrarVehiculo()) {
					boolean pagoExitoso = false;
					switch (this.enCabina.getTipoVehiculo()) {

						case 1: //Prioritario

							pagoExitoso = this.cobrar(this.enCabina, 0);
							Thread.sleep(500);

						case 2: // Automovil
							pagoExitoso = this.cobrar(this.enCabina, 115);
							sleep(500);

						case 3: // Furgon
							pagoExitoso = this.cobrar(this.enCabina, 130);
							sleep(500);

						case 4: // Bus
							pagoExitoso = this.cobrar(this.enCabina, 150);
							sleep(500);

						case 5: //Camión
							pagoExitoso = this.cobrar(this.enCabina, 180);
							sleep(500);

						default:
							System.out.println("Es un UFO");
					}
					if (!pagoExitoso) {
						this.cobrador.multa(enCabina);
						System.out.println("El vehiculo matricula " + enCabina.getMatricula()
								+ " fue multado por falta de saldo.");
					} else {
						System.out.println("El pago de la matricula " + enCabina.getMatricula() + " se realizó con éxito");
					}
					if (this.haciaMontevideo) {
						System.out.println("El vehiculo matricula " + enCabina.getMatricula() + " se dirige hacia el Oeste");
					}
					else {
						System.out.println("El vehiculo matricula " + enCabina.getMatricula() + " se dirige hacia el Este");
					}
					this.enCabina = null; 		// El vehiculo abandona el Punto de Control/Cabina.
					this.cabinaOcupada = false;	// Se vacia el Punto de Control/Cabina.
					
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * Si no hay nadie en el Punto de Control
	 * deja avanzar a un vehiculo que este esperando
	 * en el carril
	 * 
	 * @return true si entro un vehiculo
	 * 
	 */
	
	private boolean dejarEntrarVehiculo(){		
		if(!cabinaOcupada){
			if(!(this.carril.getVehiculos().peek() == null)){
				this.enCabina = this.carril.getVehiculos().poll(); // Entra al Punto de Control
				this.cabinaOcupada = true;						   // Se ocupa el Punto de Control
				return true;
			}else{
				return false; // La Cabina no esta ocupada pero no hay vehiculos esperando.
			}
		}else{
			return false; // La Cabina esta ocupada
		} 
	}

	private boolean cobrar(Vehiculo vehiculo, int tarifa) {

		boolean resultado = false;
		if (cabinaOcupada && vehiculo.getMatricula() == enCabina.getMatricula()) {			
			resultado = this.cobrador.cobrarACliente(vehiculo,tarifa);
		}

		if (resultado) {
			System.out.println("El cobro se ha realizado con exito");
		}
		return resultado;
	}

	
}

