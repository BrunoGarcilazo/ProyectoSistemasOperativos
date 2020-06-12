package Peaje;

public class Sensor {

    private Peaje peaje;
    private boolean haciaMontevideo; // verificar a que sentido va el auto

    public Sensor(boolean haciaMontevideo, Peaje peaje){
        this.peaje = peaje;
        this.haciaMontevideo = haciaMontevideo;
    }

    public boolean vehiculoPrioritarioSeAcerca(Vehiculo vehiculo){
        boolean prioritario = vehiculo.es_prioritario();
        if (prioritario == true){
            peaje.vehiculoPrioritarioSeAcerca(vehiculo);
            return true;
        } else{
            return false;
        } 
    }
    
}