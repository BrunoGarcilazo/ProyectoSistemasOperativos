package Peaje;


import java.util.Vector;

public class Peaje extends Thread {

	private String ubicacion;
	private Vector<Cabina> cabinas; // LinkedHashMap: Pares, orden de insercion
	private Vector<Carril> carriles;
	private Monitor monitorEste;
	private Monitor monitorOeste;
	private Sensor desdeMontevideo;
	private Sensor desdeElEste;
	//    private Logger logger;

	public Peaje(String ubicacion) {
		this.ubicacion = ubicacion;
		this.cabinas = new Vector<>();
		this.carriles = new Vector<>();
		this.desdeMontevideo = new Sensor(this,true);
		this.desdeElEste = new Sensor(this,false);
		//            this.logger = logger;
	}

	public Vector<Carril> getCarriles(){
		return this.carriles;
	}

	public Vector<Cabina> getCabinas(){
		return this.cabinas;
	}

	public Monitor getMonitorEste(){
		return this.monitorEste;
	}

	public Monitor getMonitorOeste(){
		return this.monitorOeste;
	}
	/**
	 * Metodo que se ejecuta al iniciar el Peaje
	 * Crea 5 cabinas hacia el Este
	 * Crea 5 cabinas hacia el Oeste
	 */
	public void crearCabinasYCarriles() {

		for (int i = 1; i <= 5; i++) {
			Cabina cabina = new Cabina(false, true, i);
			Carril carril_1 = new Carril(i, cabina,true);
			cabina.setCarril(carril_1);

			this.cabinas.add(cabina);
			this.carriles.add(carril_1);
			// Crea 5 Cabinas con su Carril, con direccion al Este. Habilitadas por defecto.
		}

		for (int i = 6; i <= 10; i++) {
			Cabina cabina2 = new Cabina(false, true, i);
			Carril carril_2 = new Carril(i, cabina2,true);
			cabina2.setCarril(carril_2);

			this.cabinas.add(cabina2);
			this.carriles.add(carril_2);
			// Crea 5 Cabinas con su Carril, con direccion al Oeste. Habilitadas por defecto.            
		}

	}

	/**
	 *  Realiza el Thread.start() de 
	 *  todas las Cabinas.
	 */
	public void startCabinas() {
		for (Cabina c : this.cabinas) {
			c.start();
		}
	}

	public  vehiculoPrioritarioSeAcerca(Vehiculo vehiculo){
		//método a desarrollar. esperando respuesta.
	}

	@Override
	public void run() {
		crearCabinasYCarriles(); // Crea las Cabinas.
		startCabinas(); // Comienzan a "trabajar" todas las Cabinas.

		while (true) {

		}
	}

	


		/*if (!cabinas.isEmpty()) {
		    for( Cabina cab : cabinas){
		    	if (this.haciaMontevideo == cab.getSentido()) {
				    if (cab.getHabilitada()) {
						Carril car = buscarCarril(cab.getID());
					    System.out.println("Cabina numero: "+ cab.getID() + " HABILITADA "+ "Con "+ car.getEsperaDeAutos().size()+ " autos" );
				    }
				    else{
                        System.out.println("Cabina numero: " + cab.getID() + "  NO HABILITADA");
				    }
			    }
		    }*/
	}


	








	

	/*
	public boolean asignarCabina(Vehiculo vehiculo){
	    if(!vehiculo.getCobrado()){ // Solo se le asigna si aun no fue cobrado
	        boolean salida = false; 
	        Vector<Cabina>  cabinasConUnMismoSentido = this.listaDeCabinas(vehiculo);
	        if (cabinasConUnMismoSentido != null) {
	            Cabina cabinaColaMasCorta = null;
	            for (Cabina cabina : cabinasConUnMismoSentido){
	                if (cabinaColaMasCorta == null){
	                    cabinaColaMasCorta = cabina;
	                }else{
	                    if (cabinaColaMasCorta.getEsperaDeAutos().size() > cabina.getEsperaDeAutos().size()){
	                    cabinaColaMasCorta = cabina;
	                    }
	                }
	            }                                   
	            vehiculo.setCabina(cabinaColaMasCorta);
	            if (cabinaColaMasCorta != null){
	                cabinaColaMasCorta.getEsperaDeAutos().add(vehiculo);
			            salida = true;
	                System.out.println("Se agrego el auto a la espera");			
	            }                           
	            return salida;
	        }
	    }
	    return false;
	    
	}
	*/

	//    public void impirmirVehiculos(){
	//        if(!this.vehiculos.isEmpty()){
	//            for(Vehiculo v: this.vehiculos){
	//                System.out.println("Lista de vehiculos disponibles:");
	//                System.out.println(v.getMatricula());
	//            }
	//        }
	//    }
	//    public boolean eliminarVehiculoMatricula(String matricula){
	//        for(Vehiculo v : this.vehiculos){
	//            if(v.getMatricula().compareTo(matricula) == 0){
	//                this.vehiculos.remove(v);
	//                return true;
	//            }
	//        }
	//        return false;
	//    }
	/* public boolean eliminarVehiculo(Vehiculo vehiculo){
		    if(vehiculo != null){
		        if (this.vehiculos.contains(vehiculo)){                                // Verifico si el Vehiculo esta en la lista de Vehiculos del Peaje
		            if (vehiculo.getCobrado() && (vehiculo.getCabina() == null)){           // Mejor caso: Ya se cobro al vehiculo y se desvinculo de su Cabina
		                this.vehiculos.remove(vehiculo);                     // Se remueve.
		                return true;
		            }else{
		                if (vehiculo.getCobrado() && (vehiculo.getCabina() != null)){       // Caso borde: ya se cobro pero no se desvinculo de su Cabina
		                    Vehiculo vehiculoEnCabina = vehiculo.getCabina().getEnCabina(); // Se obtiene el auto que erroneamente sigue en la Cabina.
		                    if (vehiculoEnCabina.getMatricula().compareTo(vehiculo.getMatricula()) == 0){  // Si el Vehiculo de parametro es el mismo al Vehiculo que aun sigue en la Cabina.
		                        vehiculo.getCabina().setEnCabina(null);                                     // Limpia el Vehiculo que seguia en "enCabina" en la clase Cabina.
		                    }
		                    vehiculo.setCabina(null);                                                       // 
		                    this.vehiculos.remove(vehiculo);                                 // Borra el Vehiculo de la lista general de Vehiculos del Peaje.
		                    return true;
		                }
		            }
		        }
		    }
		    return false;
		}
		*/

	/*public boolean agregarVehiculo(Vehiculo vehiculo){
	   if(vehiculo != null){
	       this.vehiculos.add(vehiculo);
	       if (this.vehiculos.contains(vehiculo)){
	           return true;
	       }
	   }
	   return false;
	}
	*/

		/*
	/**
	 * gestionDeVehiculos: Recorre los vehículos que esten en intereacción con el Peaje, buscando alguno que no tenga una cabina Asignada. A los que no tenga se le asigna 
	 * teniendo como criterio la Cabina que tiene la cola de vehículos más corta. A su vez, si el auto tiene una cabina asignada y está como cobrado, lo elimina de la 
	 * colección vehiculos.
	 * @return true si se le pudo asignar una cabina a todos los vehículos, false si hubo algún vehículo que no se le pudo asignar una cabina.
	 */

	/*public void gestionDeVehiculos(){
		if(!this.vehiculos.isEmpty()){
	                Vector<Vehiculo>  aux = new Vector<Vehiculo> ();
	        for (Vehiculo vehiculo : this.vehiculos){
	            // borrarlo si ya fue cobrado
	            if (vehiculo.getCobrado()){
	                aux.add(vehiculo);
	                //this.eliminarVehiculoMatricula(vehiculo.getMatricula());               
	            }else{
	                if (vehiculo.getCabina() == null){
	                    if (this.asignarCabina(vehiculo)){
	                        System.out.println("El vehículo " + vehiculo.getMatricula() + " fue asignado a la cabina "+ vehiculo.getCabina().getID() + ".");
	                    }else{
	                        System.out.println("Al vehículo " + vehiculo.getMatricula() + " no se le pudo asignar una cabina.");
	                    }
	                }
	            }
	        }
	        if(!aux.isEmpty()){
	            this.vehiculos.removeAll(aux);
	//                this.impirmirVehiculos();
	            
	        }
		}
	}
	*/	
}