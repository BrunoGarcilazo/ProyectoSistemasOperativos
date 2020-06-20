package Peaje;
import java.util.Vector;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Collections;

public class Peaje extends Thread {

	private String ubicacion;
	private Vector<Cabina> cabinas; // LinkedHashMap: Pares, orden de insercion
	private Vector<Carril> carriles;
	public Monitor monitorEste;
	public Monitor monitorOeste;
	public Sensor haciaMontevideo;
	public Sensor haciaElEste;
	private Logger logger;

	public Peaje(String ubicacion) {
		this.ubicacion = ubicacion;
		this.cabinas = new Vector<>();
		this.carriles = new Vector<>();
		this.haciaMontevideo = new Sensor(true,this);
		this.haciaElEste = new Sensor(true,this);
		this.monitorEste = new Monitor(true);
		this.monitorOeste = new Monitor(false);
		this.logger = new Logger(true,false,false);

		crearCabinasYCarriles();
	}

	public Sensor getSensorHaciaMontevideo(){
		return this.haciaMontevideo;
	}

	public Sensor getSensorHaciaEste(){
		return this.haciaElEste;
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
	 * Crea 5 cabinas hacia el Este y sus Carriles
	 * Crea 5 cabinas hacia el Oeste y sus Carriles
	 * Acondiciona los Monitores
	 */
	private void crearCabinasYCarriles(){

		for (int i = 2; i <= 5; i++) {
			Cabina cabinaN1 = new Cabina(false, true, 1); // la 1 siempre prendida
			Cabina cabina = new Cabina(false, false, i); // inicializo las cabinas apagadas

			Carril carrilN1 = new Carril(1, cabinaN1,true);
			Carril carril = new Carril(i, cabina,true);

			cabinaN1.setCarril(carrilN1);
			cabina.setCarril(carril);

			this.cabinas.add(cabinaN1); // agrego la 1er cabina
			this.cabinas.add(cabina);  // agrego las demas cabinas

			this.carriles.add(carrilN1);	//agrego el 1er carril
			this.carriles.add(carril);	// agrego los demas carriles

			// Crea 5 Cabinas con su Carril, con direccion al Este. Deshabilitadas x defecto(menos la 1 y la 10, son obligatorias)
		}

		for (int i = 6; i <= 9; i++) {
			
			Cabina cabina_ = new Cabina(true, false, i); // inicializo las cabinas apagadas
			Cabina cabinaN10 = new Cabina(true, true, 10); // la 10 siempre prendida

			Carril carril_ = new Carril(i, cabina_,true);
			Carril carrilN10 = new Carril(1, cabinaN10,true);
			
			cabina_.setCarril(carril_);
			cabinaN10.setCarril(carrilN10);
			
			this.cabinas.add(cabina_);  // agrego las demas cabinas
			this.cabinas.add(cabinaN10); // agrego la cabina N10

			this.carriles.add(carril_);	// agrego los demas carriles
			this.carriles.add(carrilN10);	//agrego el carril N10

			// Crea 5 Cabinas con su Carril, con direccion al Oeste. Deshabilitadas x defecto(menos la 1 y la 10, son obligatorias).            
		}
		
		//Acondiciono Monitor Oeste (haciaMontevideo) 1,2,3,...10
		this.monitorOeste.setCarriles(this.carriles);

		//Acondiciono Monitor Este (!haciaMontevideo) 10,9,8...1
		Vector<Carril> aux = this.carriles;
		Collections.reverse(aux);
		this.monitorEste.setCarriles(aux);

		

	}
	
	/**
	 * Metodo que se ejecuta periodicamente.
	 * Verifica la cantidad de vehiculos de cada lado del Peaje y
	 * planifica la distribucion de Cabinas disponibles para cada sentido.
	 * 
	 * Ej: 90 vehiculos provenientes del Este y 10 del Oeste,
	 * 	   el peaje abrira 9 Cabinas hacia Montevideo y 1 hacia el Este
	 * La cabina 1 y 10 no cambian de sentido ni se deshabilitan nunca.
	 */

	private void controladorDeCabinas(){

		int cantidadTotalVehiculos = this.haciaElEste.getCantidadTemp() + this.haciaMontevideo.getCantidadTemp();
		int haciaMontevideoRatio = (this.haciaMontevideo.getCantidadTemp()*100)/cantidadTotalVehiculos; // porcentaje de autos que van hacia mdeo
		int haciaEsteRatio = 100 - haciaMontevideoRatio; 
		
		//Apago todas las cabinas que no estan siendo utilizadas menos la 1 y la 10 que nunca se apagan.		
		for(int i=2; i<= 9; i++){
			if(this.carriles.get(i).getEsperaDeAutos().size() == 0 && this.carriles.get(i).getCabina().getEnCabina() == null){
				this.carriles.get(i).getCabina().setHabilitada(false);
			}
			
		}

		if(cantidadTotalVehiculos > 2 && cantidadTotalVehiculos < 15){			
			System.out.println("Se habilitan 2 cabinas nuevas");
			// Otorgo permiso para abrir 2 cabinas nuevas	
			if(haciaMontevideoRatio < 60 && haciaMontevideoRatio > 40){
				// Abro una Cabina para cada lado. Los Vehiculos vienen relativamente equitativos de ambos lados. 50 ± 10
				this.invertirSentidoCarril(2, false); // hacia el este
				this.invertirSentidoCarril(9, true); // hacia el oeste
				
			}else if(haciaMontevideoRatio > 60){
				this.invertirSentidoCarril(9,true);
				this.invertirSentidoCarril(8,true);
			}else{
				this.invertirSentidoCarril(2, false);
				this.invertirSentidoCarril(3, false);
			}
		}else if(cantidadTotalVehiculos >= 15 && cantidadTotalVehiculos <= 30){
			// Otorgo permiso para abrir 4 Cabinas 	
			System.out.println("Se habilitan 4 cabinas nuevas");
			if(haciaMontevideoRatio < 65 && haciaMontevideoRatio > 35){
				// Abro una Cabina para cada lado. Los Vehiculos vienen relativamente equitativos de ambos lados. 50 ± 15
				this.invertirSentidoCarril(2, false); // hacia el este
				this.invertirSentidoCarril(3, false);
				this.invertirSentidoCarril(9, true); // hacia el oeste
				this.invertirSentidoCarril(8, true); 
							
			}else if(haciaMontevideoRatio > 60){
				this.invertirSentidoCarril(9,true);
				this.invertirSentidoCarril(8,true);
				this.invertirSentidoCarril(7,true);
				this.invertirSentidoCarril(6,true);
			}else{
				this.invertirSentidoCarril(2, false);
				this.invertirSentidoCarril(3, false);
				this.invertirSentidoCarril(4, false);
				this.invertirSentidoCarril(5, false);
			}
		}else{
			// Caso de mucho trafico, habilito todas las Cabinas/Carriles
			System.out.println("Se habilitan todas las cabinas");
			int cabinasHaciaMontevideo = (haciaMontevideoRatio/10) - 1;
			int cabinasHaciaEste = (haciaEsteRatio/10) - 1;

			for(int i = 2 ; i <= (1 + cabinasHaciaEste); i++){
				this.invertirSentidoCarril(i, false);
			}
					
			for(int i = 9 ; i >= (9 - cabinasHaciaMontevideo) ; i--){
				this.invertirSentidoCarril(i, true);
			}

		}
	}
	public void apagarCabina(int id){
		Cabina cabina = null;
		for(Cabina c : this.cabinas){
			if(c.getID() == id){
				cabina = c;
			}
		}
		if(cabina != null){
			cabina.getCarril().setHabilitado(false);
			while (cabina.getCarril().getEsperaDeAutos().size() != 0){
				// Espero a que se vacie el carril, que los vehiculos que ya estaban en la lista de espera pasen.
			}
			cabina.setHabilitada(false);
		}
	}
	/**
	 *  Realiza el Thread.start() de 
	 *  todas las Cabinas.
	 */
	public void startCabinas() {
		for (Cabina c : this.cabinas){
			c.start();
		}
	}
	/**
	 * Metodo que invierte el Sentido de un Carril
	 * 1ro. Deshabilita entrada para nuevos Vehiculos
	 * 2do. Deshabilita Cabina
	 * 3ro. Espera a que no haya mas nadie en el Carril ni en la Cabina
	 * 4ro. Invierte el Sentido
	 * 5to. Vuelve a habilitar todo para los vehiculos entrantes en el nuevo sentido
	 */
	public void invertirSentidoCarril(int id,boolean sentidoDeseado){
		Carril carril = null;
		Cabina cabina = null;
		boolean sentido;
		for (Carril c : this.carriles) {
			if (c.getNumeroCarril() == id) {
				carril = c;
			}
		}
		if (carril != null){	
			cabina = carril.getCabina();
			if (cabina.getHabilitada()) { // quiero invertir el sentido.
				sentido = carril.getCabina().getSentido();
				if(sentido!= sentidoDeseado){
					carril.setHabilitado(false);
					carril.getCabina().setHabilitada(false);
					while (carril.getEsperaDeAutos().size() != 0) {
						// Espero a que se vacie el carril, que los vehiculos que ya estaban en la lista de espera pasen.
					}		
					carril.getCabina().setSentido(sentidoDeseado);
					carril.getCabina().setHabilitada(true);
					carril.setHabilitado(true);
				}	
			}else{ // habilito la cabina en el sentido deseado.
					carril.getCabina().setSentido(sentidoDeseado);
					carril.getCabina().setHabilitada(true);
					carril.setHabilitado(true);
			}
		}
	}

	public void vehiculoPrioritarioSeAcerca(Vehiculo vehiculo) {

		if(vehiculo != null && vehiculo.getTipoVehiculo()==1){

			Carril carrilElegido = null;							
			carrilElegido = this.obtenerCarrilPrioritario(vehiculo);
			if (carrilElegido != null){				
				carrilElegido.setHabilitado(false); // para que nadie pueda entrar al carril mientras q pasa el vehiculo prioritario.
				if(carrilElegido.getEsperaDeAutos().size() == 0){
					if(!carrilElegido.getCabina().getHabilitada()){
						carrilElegido.getEsperaDeAutos().add(vehiculo); // unico vehiculo en lista de espera
						carrilElegido.getCabina().setHabilitada(true); // habilito la cabina para que le cobre, se deshabilita desp con el metodo controladorDeCabinas. 
					}	
				}
				else{
					int id = carrilElegido.getNumeroCarril();
					boolean seMovioTrafico = false;
					for(Carril c : carriles){
					// anterior al carril elegido
						if((c.getNumeroCarril() + 1) == carrilElegido.getNumeroCarril() && c.getCabina().getSentido() == carrilElegido.getCabina().getSentido() && !seMovioTrafico){
							// darle todos los autos del carrilElegido al carril "c"
							c.setHabilitado(false); // no dejo que nadie mas entre a c
							c.getEsperaDeAutos().addAll(carrilElegido.getEsperaDeAutos()); // Mando los Vehiculos del carrilElegido al carril vecino con mi mismo sentido
							carrilElegido.setEsperaDeAutos(new Vector<Vehiculo>()); // vacio la lista de espera del carril x donde va a pasar el vehiculo prioritario
							carrilElegido.setHabilitado(false); // deshabilito el carril para que nadie se ponga adelante 
							carrilElegido.getEsperaDeAutos().add(vehiculo); // unico vehiculo en lista de espera
							c.setHabilitado(true); 
							carrilElegido.setHabilitado(true); // habilito el carril para que siga con su forma normal
							seMovioTrafico = true;
						}
						
						if((c.getNumeroCarril() -1) == carrilElegido.getNumeroCarril() && c.getCabina().getSentido() == carrilElegido.getCabina().getSentido() && !seMovioTrafico){
							// darle todos los autos del carrilElegido al carril "c"
							c.setHabilitado(false); // no dejo que nadie mas entre a c
							c.getEsperaDeAutos().addAll(carrilElegido.getEsperaDeAutos()); // Mando los Vehiculos del carrilElegido al carril vecino con mi mismo sentido
							carrilElegido.setEsperaDeAutos(new Vector<Vehiculo>()); // vacio la lista de espera del carril x donde va a pasar el vehiculo prioritario
							carrilElegido.setHabilitado(false); // deshabilito el carril para que nadie se ponga adelante 
							carrilElegido.getEsperaDeAutos().add(vehiculo); // unico vehiculo en lista de espera
							c.setHabilitado(true); 
							carrilElegido.setHabilitado(true); // habilito el carril para que siga con su forma normal
							seMovioTrafico = true;
						}
							
					}

				 //mover a los autos xq estan en la lista de espera.
				 //mover hacia los costados.
				}
			}
		}
	}

	/**
	 *  Metodo que devuelve el carril por donde deberia ir el vehiculo prioritario, esta es la que tiene menor espera (puede tener una lista de 0)
	 *  
	 */
	public Carril obtenerCarrilPrioritario(Vehiculo vehiculoPrioritario){
		Carril carrilElegido= null;
		
		for(Carril c : this.carriles){
			if(c.getCabina().getSentido() == vehiculoPrioritario.getSentido()){
				if(carrilElegido == null){
					carrilElegido = c;
				}
				if(c.getEsperaDeAutos().size() <= carrilElegido.getEsperaDeAutos().size()){
					carrilElegido = c;
				}
			}
		}
		return carrilElegido;
	}

	@Override
	public void run() {
		this.haciaElEste.start();
		this.haciaMontevideo.start();
		crearCabinasYCarriles(); // Crea las Cabinas.
		startCabinas(); // Comienzan a "trabajar" todas las Cabinas.

		while (true){
		
			//System.out.println("Monitor hacia el Este");
			//this.monitorEste.imprimir();

			//System.out.println("Monitor hacia el Oeste");
			//this.monitorOeste.imprimir();
			try{
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
					e.printStackTrace();
			}
			controladorDeCabinas();
		}
	}


}