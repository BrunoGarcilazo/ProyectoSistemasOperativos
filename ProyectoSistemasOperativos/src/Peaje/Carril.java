package Peaje;
import java.util.Vector;


public class Carril{

    private Vector<Vehiculo> esperaDeAutos; // autos en espera para entrar a cabina
	private Cabina cabina; //Cabina única asignada al carril.
	private int numeroCarril;
	private boolean disponible;

	public Carril(int numeroCarril, Cabina cabina) {
		this.numeroCarril = numeroCarril;
		this.esperaDeAutos = new Vector<Vehiculo>();
	}
   
   /**
	 * 
	 * @return Cola de espera de autos para entrar a la Cabina/Punto de Control
	 */
	public Vector<Vehiculo> getEsperaDeAutos() {
		return this.esperaDeAutos;
	}
	
	public int getNumeroCarril() {
		return this.numeroCarril;
	}

	public boolean meterEnCabina(Vehiculo vehiculo) {
		if (this.cabina.getEnCabina() == null) {
			this.cabina.setEnCabina(vehiculo);
			return true;
		} else {
			return false;
		}
	}

	public void imprimirCola(){
         for(Vehiculo v : esperaDeAutos){
             System.out.println("Matricula: "+ v.getMatricula());
         }    
    }
	public void autoYaPaso() { // este metodo elimina el auto de la lista de espera, de la cabina una vez que ya le cobro.
		if (this.cabina.getEnCabina().getCobrado()) {
			this.getEsperaDeAutos().remove(this.cabina.getEnCabina());
			this.cabina.setEnCabina(null); // Se settea null la cabina del auto.
		}
	}

} 
