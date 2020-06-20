package Peaje;

import javax.rmi.ssl.SslRMIClientSocketFactory;



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
        this.cantidadVehiculosTemp = this.cantidadVehiculosTemp + 1;
    }

    @Override
    public void run(){
        while(true){
            if(this.getCantidadTemp() != 0){
                if(haciaMontevideo){
                    System.out.println("Sensor Este: " + this.cantidadVehiculosTemp);
                }else{
                    System.out.println("Sensor Oeste: " + this.cantidadVehiculosTemp);
                }      
                       
                this.cantidadVehiculosTemp = this.cantidadVehiculosTemp - 1 ;
            }
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
}