package Peaje;
import java.util.Vector;


public class Carril{

    private Vector<Vehiculo> esperaDeAutos; // autos en espera para entrar a cabina
	private Cabina cabina; //Cabina única asignada al carril.
	private int numeroCarril;
	private Semaforo disponible;
	private boolean habilitado; 
	private Semaforo semListaEspera;


	public Carril(int numeroCarril, Cabina cabina, boolean habilitado) {
		this.numeroCarril = numeroCarril;
		this.esperaDeAutos = new Vector<Vehiculo>();
		this.habilitado = habilitado;
		this.cabina = cabina;
		this.disponible = new Semaforo();
		this.semListaEspera = new Semaforo();
	}
   
   /**
	 * 
	 * @return Cola de espera de autos para entrar a la Cabina/Punto de Control
	 */
	public Vector<Vehiculo> getEsperaDeAutos() {
		return this.esperaDeAutos;
	}

	public boolean getHabilitado() {
		return this.habilitado;
	}
	
	public Semaforo getDisponible(){
		return this.disponible;
	}

	public int getNumeroCarril() {
		return this.numeroCarril;
	}
	public Cabina getCabina() {
		return this.cabina;
	}
	public Semaforo getSemListaEspera(){
		return this.semListaEspera;
	}
	public boolean entrarAlCarril(Vehiculo v){
		if(v != null){
			this.esperaDeAutos.add(v);
			return true;
		}else{
			return false;
		}
		
	}
	
	public void imprimirCola(){
         for(Vehiculo v : esperaDeAutos){
             System.out.println("Matricula: "+ v.getMatricula());
         }    
	}


	/**
	 * Elimina de la lista de ingreso a la Cabina todo Vehiculo que ya haya sido cobrado.
	 * Idealmente, el Vehiculo que se elimine sera el primero de la lista de espera, ya que este
	 * sera el que ingrese a la Cabina para seguir su trayecto.
	 */
	public void autoYaPaso() { // este metodo elimina el auto de la lista de espera, de la cabina una vez que ya le cobro.
		for(Vehiculo c : this.esperaDeAutos){
			if(c.getCobrado()){
				this.esperaDeAutos.remove(c);
				
			}
		}		
	}

} 
