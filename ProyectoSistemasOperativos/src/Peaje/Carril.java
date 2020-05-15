package Peaje;

import java.util.Queue;

public class Carril extends Thread {

    private Queue<Vehiculo> vehiculos;


    public Carril(){
        this.vehiculos = null; // ver como instanciar una cola
    }


    public Queue<Vehiculo> getVehiculos(){
        return this.vehiculos;
    }
}