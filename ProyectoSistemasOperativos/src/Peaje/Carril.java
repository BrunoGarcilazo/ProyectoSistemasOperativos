package Peaje;

import java.util.Queue;

public class Carril extends Thread {

    private Queue<Vehiculo> vehiculos;
	private Cabina cabina; //Cabina única asignada al carril.


    public Carril(){
		this.vehiculos = null; // ver como instanciar una cola
    }
   
	public Queue<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	// semaforo que cuando auto quiere entrar a carril verifique, desp devuelvo permiso para otro puedra entrar.
} 
