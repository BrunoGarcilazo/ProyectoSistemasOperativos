package Peaje;


import java.util.LinkedHashMap;

public class Peaje extends Thread {
    
	private String ubicacion;
	private LinkedHashMap<Integer, Cabina> cabinas; // LinkedHashMap: Pares, orden de insercion
	private LinkedHashMap<Integer, Carril> carriles;
	private LinkedHashMap<String, Vehiculo> vehiculos;

	public Peaje(String ubicacion) {
		this.ubicacion = ubicacion;
		this.cabinas = new LinkedHashMap<Integer, Cabina>();
		this.carriles = new LinkedHashMap<Integer, Carril>();
        this.vehiculos = new LinkedHashMap<String, Vehiculo>();
    }

    public LinkedHashMap<Integer, Carril> getCarriles(){
        return this.carriles;
    }

	public LinkedHashMap<Integer, Cabina> getCabinas() {
		return this.cabinas;
	}

    public LinkedHashMap<String, Vehiculo> getVehiculos() {
        return this.vehiculos;
	}

    public boolean agregarVehiculo(Vehiculo vehiculo){
        if(vehiculo != null){
            this.vehiculos.put(vehiculo.getMatricula(),vehiculo);
            if (this.vehiculos.containsValue(vehiculo)){
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo){
        if(vehiculo != null){
            if (this.vehiculos.containsValue(vehiculo)){                                // Verifico si el Vehiculo esta en la lista de Vehiculos del Peaje
                if (vehiculo.getCobrado() && (vehiculo.getCabina() == null)){           // Mejor caso: Ya se cobro al vehiculo y se desvinculo de su Cabina
                    this.vehiculos.remove(vehiculo.getMatricula());                     // Se remueve.
                    return true;
                }else{
                    if (vehiculo.getCobrado() && (vehiculo.getCabina() != null)){       // Caso borde: ya se cobro pero no se desvinculo de su Cabina
                        Vehiculo vehiculoEnCabina = vehiculo.getCabina().getEnCabina(); // Se obtiene el auto que erroneamente sigue en la Cabina.
                        if (vehiculoEnCabina.getMatricula().compareTo(vehiculo.getMatricula())  == 0){  // Si el Vehiculo de parametro es el mismo al Vehiculo que aun sigue en la Cabina.
                            vehiculo.getCabina().setEnCabina(null);                                     // Limpia el Vehiculo que seguia en "enCabina" en la clase Cabina.
                        }
                        vehiculo.setCabina(null);                                                       // 
                        this.vehiculos.remove(vehiculo.getMatricula());                                 // Borra el Vehiculo de la lista general de Vehiculos del Peaje.
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
	 * gestionDeAutos: Recorre los vehículos que esten en intereacción con el Peaje, buscando alguno que no tenga una cabina Asignada. A los que no tenga se le asigna 
     * teniendo como criterio la Cabina que tiene la cola de vehículos más corta. A su vez, si el auto tiene una cabina asignada y está como cobrado, lo elimina de la 
     * colección vehiculos.
	 * @return true si se le pudo asignar una cabina a todos los vehículos, false si hubo algún vehículo que no se le pudo asignar una cabina.
	 */
	public void gestionDeAutos() {
		if(this.vehiculos != null){
            for (Vehiculo vehiculo : this.vehiculos.values()){
                // borrarlo si ya fue cobrado
                if (vehiculo.getCobrado()){
                    if (this.eliminarVehiculo(vehiculo)){
                        System.out.println("El vehículo " + vehiculo.getMatricula() + " fue eliminado exitosamente.");
                    }else{
                        System.out.println("Al vehículo " + vehiculo.getMatricula() + " no se le pudo eliminar.");
                    }                    
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
		}
    }
    
	/**
	 * listaDeCabinas: Encuentra las cabinas que estan habilitadas y tienen el mismo sentido que un vehiculo y las agrega a una linkedHashMap
	 * @return lista de cabinas con un mismo sentido.
	 */
	public LinkedHashMap<Integer, Cabina> listaDeCabinas(Vehiculo vehiculo) {

		LinkedHashMap<Integer, Cabina> cabinasConUnMismoSentido = new LinkedHashMap<Integer, Cabina>();

		for (Cabina cabina : this.cabinas.values()){
			if (cabina.getCabinaHabilitada()) {
				if (vehiculo.getSentido() == cabina.getSentido()) {
					cabinasConUnMismoSentido.put(1, cabina);
				}
			}
		}
		return cabinasConUnMismoSentido;
	}


    public boolean asignarCabina(Vehiculo vehiculo){
        boolean salida = false; 
        LinkedHashMap<Integer, Cabina> cabinasConUnMismoSentido = this.listaDeCabinas(vehiculo);
        if (cabinasConUnMismoSentido != null) {
            Cabina cabinaColaMasCorta = null;
            for (Cabina cabina : cabinasConUnMismoSentido.values()){
                if (cabinaColaMasCorta == null){
                    cabinaColaMasCorta = cabina;
                }else{
                    if (cabinaColaMasCorta.getEsperaDeAutos().size() > cabina.getEsperaDeAutos().size()){
                        cabinaColaMasCorta = cabina;
                    }
                }                        
            }
			vehiculo.setCabina(cabinaColaMasCorta);
			if (cabinaColaMasCorta != null) {
                cabinaColaMasCorta.getEsperaDeAutos().add(vehiculo);
				salida = true;
                System.out.println("Se agrego el auto a la espera");			
            }
           System.out.println("no funciono"); //nunca entro al for         
        }
        return salida;
    }
    
	/**
	 * Metodo que se ejecuta al iniciar el Peaje
	 * Crea 5 cabinas hacia el Este
	 * Crea 5 cabinas hacia el Oeste
	 */
    public void crearCabinas(){

        for (int i = 1; i<=5 ; i++) {
            Carril carril_1 = new Carril();
            this.cabinas.put(i,new Cabina(carril_1,false,true,i));
            this.carriles.put(i,carril_1);
            // Crea 5 Cabinas con su Carril, con direccion al Este. Habilitadas por defecto.
        }

        for (int i = 6; i <= 10; i++){
            Carril carril_2 = new Carril();
            this.cabinas.put(i,new Cabina(carril_2,true,true,i));
            this.carriles.put(i, carril_2);
            // Crea 5 Cabinas con su Carril, con direccion al Oeste. Habilitadas por defecto.            
        }        
    }
    /**
     *  Realiza el Thread.start() de 
     *  todas las Cabinas.
     */
    public void startCabinas(){
        for(Cabina c : this.cabinas.values()){
            c.start();
        }
    }

    @Override
    public void run(){
        crearCabinas(); // Crea las Cabinas.
        startCabinas(); // Comienzan a "trabajar" todas las Cabinas.
                
        while(true){
            this.gestionDeAutos();           
        }                
    }


    
	// metodo habilitar y cerrar cabinas
	// metodo Cambiar de sentido.

}