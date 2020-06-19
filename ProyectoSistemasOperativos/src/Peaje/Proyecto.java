package Peaje;


public class Proyecto {

    public static void main(String[] args) throws Exception {
	
        Peaje pando = new Peaje("Ruta Interbalnearia KM.33");
        CreadorDeVehiculos creador = new CreadorDeVehiculos(pando);
        creador.start();





    //        Logger logger = new Logger();
    //        logger.start();

		 //pando.start(); 
/*
        Vehiculo b1  = new Vehiculo("A1", 5 ,"Ford Fiesta" , "Blanco" , true,pando,99123456,500,111);
	Vehiculo b2  = new Vehiculo("A2", 2, "Ford Fiesta", "Blanco", true,pando,99123457,500,112);
        Vehiculo b3  = new Vehiculo("A3", 2 ,"Ford Fiesta" , "Blanco" , true,pando,99123457,500,113);
        Vehiculo b4  = new Vehiculo("A4", 4 ,"Ford Fiesta" , "Blanco" , true,pando,99123458,500,114);
        Vehiculo b5  = new Vehiculo("A5", 2 ,"Ford Fiesta" , "Blanco" , true,pando,99123459,500,115);
        Vehiculo b6  = new Vehiculo("A6", 2 ,"Ford Fiesta" , "Blanco" , true,pando,99123460,500,116);
        Vehiculo b7  = new Vehiculo("A7", 5 ,"Ford Fiesta" , "Blanco" , true,pando,99123461,500,117);
	Vehiculo b8  = new Vehiculo("A8", 2, "Ford Fiesta", "Blanco", true,pando,99123462,500,118);
	Vehiculo b9  = new Vehiculo("A9", 5, "Ford Fiesta", "Blanco", true,pando,99123463,500,119);
        Vehiculo b10 = new Vehiculo("A10", 2 ,"Ford Fiesta" , "Blanco" , true,pando,99123464,500,120);
        			
        Vehiculo g1  = new Vehiculo("B1", 4, "Hyundai HB20", "Negro", false,pando,99123465,500,321);
        Vehiculo g2  = new Vehiculo("B2", 2, "Hyundai HB20", "Negro", false,pando,99123465,500,322);
        Vehiculo g3  = new Vehiculo("B3", 2, "Hyundai HB20", "Negro", false,pando,99123466,500,324);
        Vehiculo g4  = new Vehiculo("B4", 4, "Hyundai HB20", "Negro", false,pando,99123467,500,325);
        Vehiculo g5  = new Vehiculo("B5", 2, "Hyundai HB20", "Negro", false,pando,99123467,500,326);
        Vehiculo g6  = new Vehiculo("B6", 2, "Hyundai HB20", "Negro", false,pando,99123468,500,327);
        Vehiculo g7  = new Vehiculo("B7", 2, "Hyundai HB20", "Negro", false,pando,99123469,500,328);
        Vehiculo g8  = new Vehiculo("B8", 4, "Hyundai HB20", "Negro", false,pando,99123470,500,329);
        Vehiculo g9  = new Vehiculo("B9", 2, "Hyundai HB20", "Negro", false,pando,99123471,500,330);
	Vehiculo g10 = new Vehiculo("B10", 2, "Hyundai HB20", "Negro", false,pando,99123472,500,331);
        
        

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
        
        
    */    
}
}
