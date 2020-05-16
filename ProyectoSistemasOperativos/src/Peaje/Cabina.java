package Peaje;
import java.util.LinkedList;
import java.util.Queue;
import RapiPago.*;


public class Cabina extends Thread {

	private Carril carril;			 // Carril de entrada al Punto de Control "Cabina"
	private boolean haciaMontevideo; // Determina el sentido del trafico en el carril/cabina.
	private Vehiculo enCabina;		 // Vehiculo que se encuentra en el Punto de Control
	private Semaforo cabinaOcupada;  // Si el Punto De Control/Cabina se encuentra ocupada por un vehiculo.
	private boolean habilitada;		
	private Cobrador cobrador;
	private int id;
	private Queue<Vehiculo> esperaDeAutos; // autos en espera para entrar a cabina
	private boolean suspendida; // verifica que el auto se puede poner en la cabina( si no esta cerrada para cambio de sentido)

	public Cabina(Carril carril, boolean haciaMontevideo, boolean habilitada, int id){
		this.haciaMontevideo = haciaMontevideo;
		this.enCabina = null;
		this.cabinaOcupada = new Semaforo();
		this.habilitada = habilitada;
		this.cobrador = new Cobrador();
		this.id = id;
		this.esperaDeAutos = new LinkedList<Vehiculo>();		
	}

	public int getID(){
		return this.id;
	}

	public boolean getCabinaHabilitada(){
		return this.habilitada;
	}

	public boolean getSentido(){
		return this.haciaMontevideo;
	}
	/**
	 * 
	 * @return Cola de espera de autos para entrar a la Cabina/Punto de Control
	 */
	public Queue<Vehiculo> getEsperaDeAutos(){
		return this.esperaDeAutos;
	}
	
	public Semaforo getCabinaOcupada(){
		return this.cabinaOcupada;
	}

	public Vehiculo getEnCabina(){
		return this.enCabina;
	}
	
	public void setEnCabina(Vehiculo vehiculo){
		this.enCabina = vehiculo;
	}

	public void setCabinaHabilitada(boolean habilitada){
		this.habilitada = habilitada;
	}

	@Override
	public void run() {
		while (habilitada) {
			try {
				if (this.enCabina != null) {
					boolean pagoExitoso = false;
					switch (this.enCabina.getTipoVehiculo()) {

						case 1: //Prioritario
							pagoExitoso = this.cobrar(this.enCabina, 0);
							Thread.sleep(1000);

						case 2: // Automovil
							pagoExitoso = this.cobrar(this.enCabina, 115);
							Thread.sleep(5000);

						case 3: // Furgon
							pagoExitoso = this.cobrar(this.enCabina, 130);
							Thread.sleep(5500);

						case 4: // Bus
							pagoExitoso = this.cobrar(this.enCabina, 150);
							Thread.sleep(10000);

						case 5: //Camión
							pagoExitoso = this.cobrar(this.enCabina, 180);
							Thread.sleep(15000);

						default:
							System.out.println("Es un UFO");
					}
					if (!pagoExitoso) {
						this.cobrador.multa(enCabina);
						System.out.println("El vehiculo matricula " + enCabina.getMatricula()
								+ " fue multado por falta de saldo.");
					} else {
						System.out.println(
								"El pago de la matricula " + enCabina.getMatricula() + " se realizó con éxito");
					}
					if (this.haciaMontevideo) {
						System.out.println(
								"El vehiculo matricula " + enCabina.getMatricula() + " se dirige hacia el Oeste");
					} else {
						System.out.println(
								"El vehiculo matricula " + enCabina.getMatricula() + " se dirige hacia el Este");
					}
					this.cabinaOcupada.incrementa();
					this.enCabina.setCabina(null); // Se settea null la cabina del auto.
					this.enCabina = null; // El vehiculo abandona el Punto de Control/Cabina.		
					
				}
			} catch (InterruptedException e) {
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
			System.out.println("El cobro se ha realizado con exito");
		}else{
			System.out.println("El Vehiculo matricula " + vehiculo.getMatricula() + " no pago");
		}
		return resultado;
	}

	public boolean meterEnCabina(Vehiculo vehiculo){
		if(this.enCabina == null){
			this.enCabina = vehiculo;
			return true;
		}else{
			return false;
		}
	}


}

