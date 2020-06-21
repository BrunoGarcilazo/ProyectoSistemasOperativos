package Peaje;
import java.util.Vector;


public class Monitor {

	private boolean haciaMontevideo; // True = Monitor ubicado al Este del Peaje. False = Monitor ubicado al Oeste del Peaje.
	private Vector<Carril> carriles;
	

	public Monitor(boolean sentido) {
		this.haciaMontevideo = sentido;
	}

	public Vector<Carril> getCarriles(){
		return this.carriles;
	}
	
	public void setCarriles(Vector<Carril> carriles){
		this.carriles = carriles;
	}

	public boolean getSentido() {
		return this.haciaMontevideo;
	}

	public String imprimirMonitor(){
		String salida = "|";
		String tick =  "✔";
		for(Carril c : carriles){
			Integer cantidad = c.getEsperaDeAutos().size();
			if(c.getCabina().getSentido()){		
				salida += " " + tick + " " + cantidad.toString() + " |";
			}else{
				salida += " " + "X" + " " + cantidad.toString() + " |";
			}
		}
		return salida;
	}
	public void imprimir() {
		if (!carriles.isEmpty()){
			for (Carril carril : carriles) {
				if (carril.getCabina() != null) {
					if (this.haciaMontevideo == carril.getCabina().getSentido()) {
						if (carril.getCabina().getHabilitada()) {
							Carril car = buscarCarril(carril.getNumeroCarril());
							System.out.println("Cabina numero: " + carril.getCabina().getID() + " HABILITADA " + "Con "
									+ car.getEsperaDeAutos().size() + " autos");
						} else {
							System.out.println("Cabina numero: " + carril.getCabina().getID() + "  NO HABILITADA");
						}
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
	
	

}
