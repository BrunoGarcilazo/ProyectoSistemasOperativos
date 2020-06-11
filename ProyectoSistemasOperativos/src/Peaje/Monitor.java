package Peaje;
import java.util.Vector;


public class Monitor {

	private boolean haciaMontevideo; // True = Monitor ubicado al Este del Peaje. False = Monitor ubicado al Oeste del Peaje.
	private Vector<Carril> carriles;
	private Vector<Cabina> cabinas;

	public Monitor(boolean haciaMontevideo) {
		this.haciaMontevideo = haciaMontevideo;
	}

	public Vector<Carril> getCarriles() {
		return this.carriles;
	}

	public Vector<Cabina> getCabinas() {
		return this.cabinas;
	}
	
	public boolean getSentido() {
		return this.haciaMontevideo;
	}

	
	public void imprimir() {

		if (!cabinas.isEmpty()) {
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
		    }
		}
	}

	public Carril buscarCarril(int idCabina) {
		if (!this.carriles.isEmpty()) {
			for (Carril car : carriles) {
				if (car.getNumeroCarril() == idCabina) {
					return car;
				}
			}
		}
		return null;
	}

	// ver metodo para agregar elementos a las listas carriles y cabinas.

}
