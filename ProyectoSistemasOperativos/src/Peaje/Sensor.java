package Peaje;

public class Sensor extends Thread{

    private Peaje peaje;
    private boolean haciaMontevideo; // verificar a que sentido va el auto
    private int cantidadVehiculosTemp; // Cantidad de Vehiculos que pasan por el Sensor (esta cantidad es MUY variable a lo largo del tiempo!)


    public Sensor(boolean haciaMontevideo, Peaje peaje){
        this.peaje = peaje;
		this.haciaMontevideo = haciaMontevideo;
		
    }

    public boolean vehiculoPrioritarioSeAcerca(Vehiculo vehiculo){
        boolean prioritario = vehiculo.es_prioritario();
        if (prioritario == true){
        //    peaje.vehiculoPrioritarioSeAcerca(vehiculo);
            return true;
        } else{
            return false;
        } 
    }

    public int getCantidadTemp(){
        return this.cantidadVehiculosTemp;
    }
    
    public void vehiculoDetectado(){
        this.cantidadVehiculosTemp++;
    }

    @Override
    public void run(){
        while(true){
            if(this.cantidadVehiculosTemp != 0){
                this.cantidadVehiculosTemp--;
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
}