package Peaje;

public class CreadorDeVehiculos extends Thread {
    
    Peaje pando;
    private Integer numero = 0;
	
	public CreadorDeVehiculos(Peaje peaje) {
        this.pando = peaje; 
        //peaje.invertirSentidoCarril(2);
        peaje.start();
	}

	// Caso 1:
	// - Todos los vehiculos menos prioritarios en gran cantidad en ambos sentidos

	public void caso1(){
        for(int i=1; i <= 10; i++){
            Integer key = i;
            Vehiculo automovil  = new Vehiculo("A"+key.toString(), 2 ,"Ford Fiesta" , "Blanco" , true,pando,99000000+i,500,4000000+i);
            Vehiculo furgon  = new Vehiculo("F"+key.toString(), 3 ,"Hyundai H100" , "Rojo" , true,pando,94000000+i,600,5000000+i);
           
            automovil.start();
            furgon.start();
            
        }
        for(int i=11; i <= 21; i++){
            Integer key = i;
            Vehiculo automovil  = new Vehiculo("A"+key.toString(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500,4000000+i);
            Vehiculo furgon  = new Vehiculo("F"+key.toString(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600,5000000+i);
            
            automovil.start();
            furgon.start();
            
        }
    }
    
	// Caso 2: Cerrar las cabinas.
	// - Todos los vehiculos menos prioritarios poca cantidad en ambos sentidos
    
    public void caso2(){
		for(int i=24; i <= 27; i++){
            Integer key = i;
            Vehiculo automovil  = new Vehiculo("A"+key.toString(), 2 ,"Ford Fiesta" , "Blanco" , true,pando,99000000+i,500,4000000+i);
            Vehiculo furgon  = new Vehiculo("F"+key.toString(), 3 ,"Hyundai H100" , "Rojo" , true,pando,94000000+i,600,5000000+i);
            
            automovil.start();
            furgon.start();
            
        }
		for(int i=28; i <= 31; i++){
            Integer key = i;
            Vehiculo automovil  = new Vehiculo("A"+key.toString(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500,4000000+i);
            Vehiculo furgon  = new Vehiculo("F"+key.toString(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600,5000000+i);
            
            automovil.start();
            furgon.start();
            
        }
    }

    // Caso 3:
	// - Todos los tipos de vehiculos en gran cantidad, solo hacia el Este
	public void caso3(){
        for(int i=32; i <= 47; i++){
            Integer key = i;
            Vehiculo automovil  = new Vehiculo("A"+key.toString(), 2 ,"Ford Fiesta" , "Blanco" , false,pando,99000000+i,500,4000000+i);
            Vehiculo furgon  = new Vehiculo("F"+key.toString(), 3 ,"Hyundai H100" , "Rojo" , false,pando,94000000+i,600,5000000+i);
            Vehiculo camion  = new Vehiculo("C"+key.toString(), 5 ,"Scania R620" , "Azul" , false,pando,96000000+i,1000,6000000+i);
			Vehiculo omnibus = new Vehiculo("O" + key.toString(), 4, "BYD Electrico", "Negro", false, pando,95000000 + i, 700, 7000000 + i);
            
            automovil.start();
            furgon.start();
            camion.start();
            omnibus.start();
        }
		Vehiculo ambulancia = new Vehiculo("Amb" + "1", 1, "Suat", "Blanco", false, pando, 949999999, 0, 0000001);
		Vehiculo bombero = new Vehiculo("Bomb" + "2", 1, "Bombero", "Rojo", false, pando, 1099999, 0, 0000002);
        Vehiculo policia = new Vehiculo("Poli" + "3", 1, "Policia", "Azul", false, pando,3099999, 0, 0000003);
        ambulancia.start();
        bombero.start();
        policia.start();
	}
   
	// Caso 4:
	// - Todos los vehiculos, alguno sin saldo.
	public void caso4() {
       
       Vehiculo automovil  = new Vehiculo("A90", 2 ,"Corsa" , "Violeta" , false,pando,9010,100,01);
       Vehiculo automovil1  = new Vehiculo("A91", 2 ,"Audi" , "Gris" , true,pando,9019,0,02);
       Vehiculo automovil2  = new Vehiculo("A92", 2 ,"BMW" , "Negro" , false,pando,9020,0,03);
            
       automovil.start();
	   automovil1.start();
       automovil2.start();
            
        }
	

    // Caso 5:
	// - Todos los vehiculos, alguno con información irregular.
    public void caso5() {
       Vehiculo automovil  = new Vehiculo("", 2 ,"Peugeot" , "Violeta" , false,pando,9010,2000,06);
       Vehiculo automovil1  = new Vehiculo("", 2 ,"Citroen" , "Naranja" , true,pando,9019,9000,07);
	   Vehiculo camion1  = new Vehiculo("", 5 ,"Renault" , "Negro" , true,pando,9090,2000,8);
            
       automovil.start();
	   automovil1.start();
       camion1.start();
            
        }
	
    // Caso 6 :
	// - Envio vehiculos prioritarios luego de un tiempo.
	public void caso6() {

		Vehiculo ambulancia = new Vehiculo("Amb" + "30", 1, "Semm", "Blanco", false, pando, 1049999999, 0, 000000104);
		Vehiculo bombero = new Vehiculo("Bomb" + "50", 1, "Bombero", "Rojo", true, pando, 10799999, 0, 000000102);
		Vehiculo bombero1 = new Vehiculo("Bomb" + "50", 1, "Bombero", "Rojo", false, pando, 107999980, 0, 000000006);
		Vehiculo policia = new Vehiculo("Poli" + "20", 1, "Policia", "Azul", true, pando, 10399999, 0, 000000103);
		ambulancia.start();
		bombero.start();
		bombero1.start();
		policia.start();
	}
	
	//Caso 7:
    // - Envio camiones y omnibus
    public void caso7() {
        
        for(int i=50; i <= 52; i++){
            Integer key = i;
            Vehiculo camion  = new Vehiculo("C"+key.toString(), 5 ,"Scania R620" , "Azul" , false,pando,96000000+i,1000,6000000+i);
			Vehiculo omnibus = new Vehiculo("O" + key.toString(), 4, "BYD Electrico", "Negro", false, pando,95000000 + i, 700, 7000000 + i);
            camion.start();
		    omnibus.start();

            }

        for(int i=53; i <= 55; i++){
            Integer key = i;
            Vehiculo camion  = new Vehiculo("C"+key.toString(), 5 ,"Scania R620" , "Azul" , true,pando,96000000+i,1000,6000000+i);
			Vehiculo omnibus = new Vehiculo("O" + key.toString(), 4, "BYD Electrico", "Negro", true, pando,95000000 + i, 700, 7000000 + i);
            camion.start();
		    omnibus.start();

           }

		
	}
	

    @Override
	public void run() {
    // inicia el peaje uniformemente.
        caso3();
        caso7();
      
			try{
			   Thread.sleep(10000);
			} catch (InterruptedException e) {
			e.printStackTrace();
            }
        caso6();
        caso1();
        caso4();
        caso5();
		// Agrego vehiculos sin saldo en ambos sentidos
		//caso6();
        
        
    }
}      
