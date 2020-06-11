package Peaje;


public class Proyecto {

    public static void main(String[] args) throws Exception {
	
//        Logger logger = new Logger();
//        logger.start();
        
        Peaje pando = new Peaje("Ruta Interbalnearia KM.33");
       
       
        Vehiculo b1  = new Vehiculo("A1", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
	Vehiculo b2  = new Vehiculo("A2", 2, "Ford Fiesta", "Blanco", true,pando);
        Vehiculo b3  = new Vehiculo("A3", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
        Vehiculo b4  = new Vehiculo("A4", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
        Vehiculo b5  = new Vehiculo("A5", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
        Vehiculo b6  = new Vehiculo("A6", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
        Vehiculo b7  = new Vehiculo("A7", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
	Vehiculo b8  = new Vehiculo("A8", 2, "Ford Fiesta", "Blanco", true,pando);
	Vehiculo b9  = new Vehiculo("A9", 2, "Ford Fiesta", "Blanco", true,pando);
        Vehiculo b10 = new Vehiculo("A10", 2 ,"Ford Fiesta" , "Blanco" , true,pando);
        			
        Vehiculo g1  = new Vehiculo("B1", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g2  = new Vehiculo("B2", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g3  = new Vehiculo("B3", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g4  = new Vehiculo("B4", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g5  = new Vehiculo("B5", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g6  = new Vehiculo("B6", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g7  = new Vehiculo("B7", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g8  = new Vehiculo("B8", 2, "Hyundai HB20", "Negro", false,pando);
        Vehiculo g9  = new Vehiculo("B9", 2, "Hyundai HB20", "Negro", false,pando);
	Vehiculo g10 = new Vehiculo("B10", 2, "Hyundai HB20", "Negro", false,pando);
        
        pando.start(); 

        try{
            b1.start();		
            b2.start();	
            b3.start();	
            b4.start();	
            b5.start();	
            b6.start();	
            b7.start();	
            b8.start();	
            b9.start();	
            b10.start();

            g1.start();
            g2.start();
            g3.start();
            g4.start();
            g5.start();
            g6.start();
            g7.start();
            g8.start();
            g9.start();
            g10.start();

            b1.join();		
            b2.join();	
            b3.join();	
            b4.join();	
            b5.join();	
            b6.join();	
            b7.join();	
            b8.join();	
            b9.join();	
            b10.join();

            g1.join();
            g2.join();
            g3.join();
            g4.join();
            g5.join();
            g6.join();
            g7.join();
            g8.join();
            g9.join();
            g10.join();
            
        } catch(InterruptedException e){
            e.printStackTrace();
        }						
        
}
}
